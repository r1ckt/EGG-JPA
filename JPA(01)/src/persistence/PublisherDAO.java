package persistence;

import entity.Publisher;
import java.util.List;

public class PublisherDAO extends DAO<Publisher>{
    
    
    public List<Publisher> getPublisherByName(String name) throws Exception {
        try {
            List<Publisher> publishers = em.createQuery("SELECT a FROM Publisher a WHERE a.name LIKE :name", Publisher.class)
                    .setParameter("name", name)
                    .getResultList();
            
        return publishers;   
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("No se pudo encontrar el autor");
        }
    }
    
    public List<Publisher> getPublishers() throws Exception{
        try {
            List<Publisher> publishers = em.createQuery("SELECT a FROM Publisher a").getResultList();
            return publishers;
        } catch (Exception e) {
            throw new Exception("No se pudo recuperar la lista de editoriales");
        }
    }
}
