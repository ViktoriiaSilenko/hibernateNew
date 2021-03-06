package org.it.discovery.training.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Person who can write books, for example
 * @author admin
 *
 */
@Entity
@Table(name = "PERSON")
public class Person extends BaseEntity {
	private String name;
	
	/**
	 * Books that person has written
	 */
	private List<Book> books;

	private Person boss;

	@OneToOne(fetch = FetchType.EAGER)
	public Person getBoss() {
		return boss;
	}

	public void setBoss(Person boss) {
		this.boss = boss;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		if(books == null) {
			books = new ArrayList<>();
		}
		books.add(book);
		book.setAuthor(this);
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", boss=" + Optional.ofNullable(boss).map(Person::getName).orElse("No boss") +
				'}';
	}
}
