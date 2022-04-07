
package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO <T>{

    protected final EntityManager em = Persistence
            .createEntityManagerFactory("LIBRERIAPU")
            .createEntityManager();

    public void insert(T t) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("error al agregar");
        }
    }

    public void update(T t) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("error al modificar autor");
        }

    }

    public void delete(T t) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("ERROR al modificar autor");
        }

    }


}
