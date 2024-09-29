package com.ism.core.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public abstract class RepositoryJPA<T> implements Repository<T> {

    protected EntityManager em;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("MYSQLDETTES");
    protected Class<T> entityClass;
    protected  String tableName;

    public RepositoryJPA() {
        this(null);
    }

    public RepositoryJPA(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.em = emf.createEntityManager(); 
    }
    
    @Override
    public boolean insert(T object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<T> selectAll() {
        TypedQuery<T> query = em.createQuery("SELECT u FROM " + entityClass.getSimpleName() + " u", entityClass);
        return query.getResultList();
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}