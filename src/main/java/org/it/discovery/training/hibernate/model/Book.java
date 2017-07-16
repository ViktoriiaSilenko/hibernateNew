package org.it.discovery.training.hibernate.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Book in a library
 *
 * @author morenets
 */
@Entity
@Table(name = "BOOK")
@NamedQueries({
        @NamedQuery(name = Book.QUERY_FIND_ALL, query = "from Book"),
        @NamedQuery(name = Book.QUERY_FIND_ALL_BOOK_INFO,
                query = "select new org.it.discovery.training.hibernate.model.tuple.BookInfo(id,name) from Book"),
        @NamedQuery(name = Book.QUERY_FIND_ALL_WITH_NAME, query = "from Book where name=:name"),
        @NamedQuery(name = Book.QUERY_FIND_WITH_HITS,
                query = "select distinct b from Book b join b.hits"),
        @NamedQuery(name = Book.QUERY_FIND_WITHOUT_HITS,
                query = "select distinct b from Book b left outer join b.hits hits where hits is null"),
        @NamedQuery(name = Book.QUERY_GET_STATISTICS,
                query = "select new org.it.discovery.training.hibernate.model.tuple.Statistics("
                        + " count(b.id), (select count(id) from Publisher)) FROM Book b")

})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book extends BaseEntity {
    public static final String QUERY_FIND_ALL = "Book.findAll";

    public static final String QUERY_FIND_ALL_BOOK_INFO = "Book.findAllBookInfo";

    public static final String QUERY_FIND_ALL_WITH_NAME = "Book.findAllWithName";

    public static final String QUERY_FIND_WITH_HITS = "Book.findWithHits";

    public static final String QUERY_FIND_WITHOUT_HITS = "Book.findWithoutHits";

    public static final String QUERY_GET_STATISTICS = "Book.getStatistics";

    private String name;

    private Person author;

    private Publisher publisher;

    /**
     * Publishing year
     */
    private int year;

    /**
     * Total number of pages
     */
    private int pages;

    private List<Hit> hits;

    private int hitCount;

    @Formula("(select count(hit.id) from HIT hit WHERE hit.BOOK_ID=id)")
    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @ManyToOne
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @BatchSize(size = 20)
    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public void addHit(Hit hit) {
        if (this.hits == null) {
            this.hits = new ArrayList<>();
        }
        this.hits.add(hit);
        hit.setBook(this);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", hitCount='" + hitCount + '\'' +
                ", publisher=" + publisher +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }
}
