
package persistence;

import entity.Book;
import java.util.List;

public class BookDAO extends DAO<Book> {
    
    public List<Book> getBooks() throws Exception{
        try {
            List<Book> books = em.createQuery("SELECT a FROM Book a").getResultList();
            return books;
        } catch (Exception e) {
            throw new Exception("No se pudo mostrar la lista de libros");
        }
    }

    public Book getByIsbn(Long isbn) throws Exception {
        try {
            Book book = em.createQuery("SELECT f from Book f WHERE f.isbn = :isbn", Book.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();

            return book;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("No se encontro el libro con ese ISBN");
        }
    }
    
    public Book getByTitle(String title) throws Exception {
        try {
            Book book = em.createQuery("SELECT f from Book f WHERE f.title = :title", Book.class)
                    .setParameter("title", title)
                    .getSingleResult();
            return book;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("No se encontro libro con ese título");
        }
    }
    
    public List<Book> getByAuthorName(String author) throws Exception{
        try {
            List<Book> books = em.createQuery("SELECT f from Book f WHERE f.author = :author", Book.class)
                    .setParameter("author", author)
                    .getResultList();
            return books;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("No se encontraron libros pertenecientes a " + author);
        }
    }
    
    public List<Book> getByPublisherName(String publisher) throws Exception {
        try {
            List<Book> books = em.createQuery("SELECT f from Book f WHERE f.publisher = :publisher ", Book.class)
                    .setParameter("publisher", publisher)
                    .getResultList();
            
            return books;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("No se encontraron libros de la editorial " + publisher);
        }
    }
}
