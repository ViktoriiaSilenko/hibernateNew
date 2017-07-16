package org.it.discovery.training.hibernate.repository.jpa;

import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.PublisherRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class JPAPublisherRepository implements PublisherRepository, AutoCloseable {
    private final EntityManagerFactory emf;

    public JPAPublisherRepository() {
        this.emf = Persistence.createEntityManagerFactory("library");
    }

    private <T> T query(Function<EntityManager, T> function) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            return function.apply(em);

        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException(ex);
        } finally {
            if (em != null) {
                em.getTransaction().commit();
            }
        }
    }

    private void execute(Consumer<EntityManager> consumer) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            consumer.accept(em);

        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException(ex);
        } finally {
            if (em != null) {
                em.getTransaction().commit();
            }
        }
    }

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

    @Override
    public void close() throws Exception {
        emf.close();
    }
}
