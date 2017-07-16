package org.it.discovery.training.hibernate.model;

import javax.persistence.*;
import java.util.List;

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

	@OneToMany
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", boss=" + boss.getName() +
				'}';
	}
}
