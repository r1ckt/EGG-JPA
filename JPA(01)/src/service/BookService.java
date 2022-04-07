package service;

import entity.Author;
import entity.Book;
import entity.Publisher;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import persistence.AuthorDAO;
import persistence.BookDAO;

public class BookService {

    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private Scanner scan;

    public BookService() {
        this.bookDAO = new BookDAO();
        this.authorDAO = new AuthorDAO();
        this.scan = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    }

    public void createBook() throws Exception {
        Book book = new Book();
        Author author = new Author();
        Publisher publisher = new Publisher();

        try {
            System.out.println("Ingrese el titulo del libro");
            String title = scan.next();

            System.out.println("Ingrese el autor del libro");
            String authorName = scan.next();

            System.out.println("Ingrese el año de publicación del libro");
            Integer publishedYear = scan.nextInt();

            System.out.println("Ingrese el nombre de la editorial del libro");
            String bookPublisher = scan.next();

            System.out.println("Ingrese el ISBN del libro");
            Long isbn = scan.nextLong();

            System.out.println("Ingrese el total de ejemplares del libro");
            Integer copies = scan.nextInt();

            System.out.println("Ingrese los ejemplares prestados");
            Integer borrowed = scan.nextInt();

            Integer available = copies - borrowed;

            author.setName(authorName);
            author.setRegister(Boolean.TRUE);
            publisher.setName(bookPublisher);
            publisher.setRegister(Boolean.TRUE);
            book.setPublisher(publisher);
            book.setTitle(title);
            book.setAuthor(author);
            book.setBookYear(publishedYear);
            book.setIsbn(isbn);
            book.setCopies(copies);
            book.setBorrowedCopies(borrowed);
            book.setRemainedCopies(available);
            book.setRegister(Boolean.TRUE);

            bookDAO.insert(book);
            System.out.println("Se guardo el libro en la base de datos");
        } catch (Exception e) {
            throw new Exception("No se pudo crear el libro");
        }
    }

    public void showBooks() {
        try {
            List<Book> books = bookDAO.getBooks();

            if (books.isEmpty()) {
                System.out.println("La lista de libros esta vacia");
            } else {
                System.out.printf("%-20s%-35s%-20s%-20s%-20s%-20s%-20s%-20s%-20s \n",
                        "ISBN", "Titulo", "Autor", "Editorial", "Year", "Copies", "Borrowed copies", "Remained copies", "Active");
                for (Book book : books) {
                    System.out.printf("%-20s%-35s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                            book.getIsbn(), book.getTitle(), book.getAuthor().getName(),
                            book.getPublisher().getName(), book.getBookYear(), book.getCopies(),
                            book.getBorrowedCopies(), book.getRemainedCopies(), book.getRegister());

                }
            }
        } catch (Exception e) {
        }
    }

    public void disable(Long code) throws Exception {
        try {
            Book book = bookDAO.getByIsbn(code);

            if (book == null) {
                System.out.println("No existe libro con ese ISBN");
            } else {
                book.setRegister(Boolean.FALSE);
                bookDAO.update(book);
                System.out.println("El libro se dio de baja con exito");
            }
        } catch (Exception e) {
            throw new Exception("No se pudo dar de de baja el libro");
        }

    }

    public void activate(Long code) throws Exception {
        try {
            Book book = bookDAO.getByIsbn(code);

            if (book == null) {
                System.out.println("No existe ese libro");
            } else {
                book.setRegister(Boolean.TRUE);
                bookDAO.update(book);
                System.out.println("El libro se dio de alta con exito");
            }
        } catch (Exception e) {
            throw new Exception("No se pudo dar de de baja el libro");
        }

    }

    public void modifyBookName(String name, String newName) throws Exception {
        try {
            List<Book> books = bookDAO.getBooks();
            if (books.isEmpty()) {
                System.out.println("La lista de libros está vacia");
            } else {
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(name)) {
                        book.setTitle(newName);
                        bookDAO.update(book);
                        System.out.println("El nombre del libro fue modificado con exito!");
                    }

                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo modificar nombre el libro");
        }
    }

    public void modifyBookYear(String name, Integer year) throws Exception {
        try {
            List<Book> books = bookDAO.getBooks();
            if (books.isEmpty()) {
                System.out.println("La lista de libros está vacia");
            } else {
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(name)) {
                        book.setBookYear(year);
                        bookDAO.update(book);
                        System.out.println("El año de lanzamiento del libro fue modificado con exito!");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo modificar nombre el libro");
        }
    }

    public void getBookByName(String name) {
        try {
            Book book = bookDAO.getByTitle(name);

            if (book == null || name.trim().isEmpty()) {
                System.out.println("El libro no existe");
            } else {
                System.out.printf("%-20s%-35s%-20s%-20s%-20s%-20s%-20s%-20s%-20s \n",
                        "ISBN", "Titulo", "Autor", "Editorial", "Year", "Copies", "Borrowed copies", "Remained copies", "Active");

                System.out.printf("%-20s%-35s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                        book.getIsbn(), book.getTitle(), book.getAuthor().getName(),
                        book.getPublisher().getName(), book.getBookYear(), book.getCopies(),
                        book.getBorrowedCopies(), book.getRemainedCopies(), book.getRegister());

            }
        } catch (Exception e) {
        }
    }

}
