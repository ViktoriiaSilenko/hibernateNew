package org.it.discovery.training.hibernate.repository.jpa;

import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.repository.PersonRepository;

import java.util.List;

public class JPAPersonRepository extends BaseJPARepository implements PersonRepository{

    @Override
    public List<Person> findPersonWithoutBooks() {
        return query(em -> em.createQuery("select p from Person p " +
                "left outer join p.books books where books.id is null", Person.class).
                getResultList());
    }

    @Override
    public Person findMostEfficientAuthor() {
        return null;
    }

    @Override
    public void save(Person person) {
        execute(em -> em.persist(person));

    }

    @Override
    public List<Person> findPersonsByBook(String name) {
        return null;
    }

    @Override
    public List<Person> findPersonsOfPublisher(int publisherId) {
        return null;
    }

    @Override
    public List<Person> findTopAuthors(int limit) {
        return null;
    }
}
