package persistence;

import entity.Author;
import java.util.List;

public class AuthorDAO extends DAO<Author> {

    public List<Author> getAuthorsByName(String name) throws Exception {
        try {
            List<Author> authors = em.createQuery("SELECT a FROM Author a WHERE a.name LIKE :name", Author.class)
                    .setParameter("name", name)
                    .getResultList();
            
        return authors;   
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("No se pudo encontrar el autor");
        }
    }
    
    public List<Author> getAllAuthors() throws Exception{
        try {
            List<Author> authors = em.createQuery("SELECT a FROM Author a").getResultList();
            return authors;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("No se pudo recuperar la lista de autores");
        }
    }
    
    
}