package service;

import entity.Author;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import persistence.AuthorDAO;

public class AuthorService {

    private AuthorDAO authorDAO;
    private Scanner scan;

    public AuthorService() {
        this.authorDAO = new AuthorDAO();
        this.scan = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
    }

    public Author createAuthor() throws Exception {
        Author author = new Author();

        System.out.println("Ingresar el nombre del Autor:");
        try {
            String AuthorName = scan.next();
            if (AuthorName == null || AuthorName.trim().isEmpty()) {
                System.out.println("El nombre del autor es obligatorio");
            }

            author.setName(AuthorName);
            author.setRegister(Boolean.TRUE);

            authorDAO.insert(author);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al guardar el autor");
        }

        return author;
    }

    public void getAuthor() throws Exception {

        System.out.println("Ingresar el nombre del autor que desea buscar");
        try {
            String authorName = scan.next();

            List<Author> authors = authorDAO.getAuthorsByName(authorName);

            if (authors.isEmpty()) {
                System.out.println("La lista de autores está vacia");
            } else {
                for (Author author : authors) {
                    if (authorName.equals(author.getName())) {
                        System.out.printf("%-10s%-10s", "Id", "Nombre");
                        System.out.println(author.getId() + " " + author.getName());
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo encontrar autor");
        }
    }

    public void showAuthors() throws Exception {
        try {
            List<Author> authors = authorDAO.getAllAuthors();

            if (authors.isEmpty()) {
                System.out.println("La lista de autores está vacia");
            } else {
                System.out.printf("%-10s%-20s%-20s\n", "Id", "Nombre", "Active");
                for (Author author : authors) {

                    System.out.printf("%-10s%-20s%-20s\n", author.getId(), author.getName(), author.getRegister());

                }
            }

        } catch (Exception e) {
            throw new Exception("No se pudo mostrar la lista de autores");
        }
    }

    public void disable(String name) throws Exception {
        try {

            List<Author> authors = authorDAO.getAuthorsByName(name);

            if (authors.isEmpty()) {
                System.out.println("No existen autores con ese nombre");
            } else {
                for (Author author : authors) {
                    if (author.getName().equalsIgnoreCase(name)) {
                        author.setRegister(Boolean.FALSE);
                        authorDAO.update(author);
                        System.out.println("El autor se dio de baja con exito!");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo dar de de baja el autor");
        }
    }

    public void activate(String name) throws Exception {
        System.out.println("Ingresar autor del libro");
        try {
            List<Author> authors = authorDAO.getAuthorsByName(name);

            if (authors.isEmpty()) {
                System.out.println("No existen autores con ese nombre");
            } else {
                for (Author author : authors) {
                    if (author.getName().equalsIgnoreCase(name)) {
                        author.setRegister(Boolean.TRUE);
                        authorDAO.update(author);
                        System.out.println("El autor se dio de alta con exito!");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo dar de alta el autor");
        }

    }
    
    public void modifyAuthorName(String name, String newName) throws Exception{
        try {
            List<Author> authors = authorDAO.getAuthorsByName(name);
            if(authors.isEmpty()){
                System.out.println("La lista de autores está vacia");
            } else {
                for (Author author : authors) {
                    if(author.getName().equalsIgnoreCase(name)){
                        author.setName(newName);
                        authorDAO.update(author);
                        System.out.println("El nombre del autor se modifico con exito!");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo modificar el nombre del autor");
        }
    }
    
    public void getAuthorByName(String name) throws Exception{
        try {
            List<Author> authors = authorDAO.getAuthorsByName(name);
            
            if(authors.isEmpty()){
                System.out.println("La lista de autores esta vacia");
            } else if(name.trim().isEmpty()){
                System.out.println("El nombre ingresado es invalido");
            } else {
                System.out.printf("%-10s%-20s%-10s\n", "Id", "Nombre", "Active");
                for (Author author : authors) {
                    System.out.printf("%-10s%-20s%-10s\n", author.getId(), author.getName(), author.getRegister());
                }
            }
        } catch (Exception e) {
            throw new Exception("No se encontro un autor con ese nombre");
        }
    }

}
