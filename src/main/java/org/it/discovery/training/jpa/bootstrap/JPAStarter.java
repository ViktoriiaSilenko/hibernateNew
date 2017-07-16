package org.it.discovery.training.jpa.bootstrap;

import org.it.discovery.training.hibernate.model.Address;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.PublisherRepository;
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
    }

}
