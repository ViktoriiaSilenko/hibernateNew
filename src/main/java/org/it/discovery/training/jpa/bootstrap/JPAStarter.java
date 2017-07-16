package org.it.discovery.training.jpa.bootstrap;

import org.it.discovery.training.hibernate.model.Address;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.PublisherRepository;
import org.it.discovery.training.hibernate.repository.jpa.JPAPersonRepository;
import org.it.discovery.training.hibernate.repository.jpa.JPAPublisherRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAStarter {

    public static void main(String[] args) {
        try(JPAPublisherRepository repository = new JPAPublisherRepository()) {
            Publisher publisher = new Publisher();
            publisher.setName("Packt");
            Address address = new Address();
            address.setCity("London");
            address.setStreet("Some street");
            publisher.setAddress(address);
            repository.save(publisher);

            System.out.println(repository.findById(publisher.getId()));
            repository.delete(publisher.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try(JPAPersonRepository repository = new JPAPersonRepository()) {
            Person person = new Person();
            person.setName("Gavin King");
            repository.save(person);

            Book book = new Book();
            book.setName("Hibernate");
            person.addBook(book);

            Person person2 = new Person();
            person2.setName("Josh Long");
            repository.save(person2);

            Book book2 = new Book();
            book2.setName("Spring");
            person2.addBook(book2);

            Book book3 = new Book();
            book3.setName("Spring Data");
            person2.addBook(book3);

            Person person3 = new Person();
            person3.setName("Sam Newman");
            repository.save(person3);

            System.out.println(repository.findPersonWithoutBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
