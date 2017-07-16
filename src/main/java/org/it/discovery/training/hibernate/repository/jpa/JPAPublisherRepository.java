package org.it.discovery.training.hibernate.repository.jpa;

import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

public class JPAPublisherRepository extends BaseJPARepository
        implements PublisherRepository {
    @Override
    public void save(Publisher publisher) {
        execute(em -> em.persist(publisher));
    }

    @Override
    public void saveAll(List<Publisher> publishers) {
        execute(em -> publishers.forEach(em::persist));
    }

    @Override
    public void delete(int publisherId) {
        execute(em ->
                Optional.ofNullable(em.find(Publisher.class, publisherId))
                        .ifPresent(p -> em.remove(p))
        );
    }

    @Override
    public Publisher findById(int publisher) {
        return query(em -> em.find(Publisher.class, publisher));
    }
}
