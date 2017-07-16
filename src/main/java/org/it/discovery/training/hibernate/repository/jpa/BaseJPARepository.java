package org.it.discovery.training.hibernate.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseJPARepository implements AutoCloseable{
    private final EntityManagerFactory emf;

    public BaseJPARepository() {
        this.emf = Persistence.createEntityManagerFactory("library");
    }

    protected  <T> T query(Function<EntityManager, T> function) {
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

    protected void execute(Consumer<EntityManager> consumer) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            consumer.accept(em);

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if(em != null) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void close() throws Exception {
        emf.close();
    }
}
