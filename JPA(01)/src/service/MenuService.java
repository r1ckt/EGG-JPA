package service;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class MenuService {

    private AuthorService authorService;
    private BookService bookService;
    private PublisherService publisherService;
    private Scanner scan;

    public MenuService() {
        this.authorService = new AuthorService();
        this.bookService = new BookService();
        this.publisherService = new PublisherService();
        this.scan = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
    }

    public void menuLibreria() throws Exception {

        int option;
        int option2;
        do {
            System.out.println("1-Cargar un libro");
            System.out.println("2-Cargar un autor");
            System.out.println("3-Cargar una editorial");
            System.out.println("4-Dar de alta/baja libro ");
            System.out.println("5-Dar de alta/baja un autor");
            System.out.println("6-Dar de alta/baja una editorial ");
            System.out.println("7-Editar libro ");
            System.out.println("8-Editar un autor");
            System.out.println("9-Editar una editorial");
            System.out.println("10-Buscar autor por nombre");
            System.out.println("11-Buscar libro");
            System.out.println("12-Mostrar todos los autores");
            System.out.println("13-Mostrar todas las editoriales");
            System.out.println("14-Mostrar todos los libros");

            option = scan.nextInt();

            switch (option) {
                case 1:
                    bookService.createBook();
                    System.out.println();
                    break;
                case 2:
                    authorService.createAuthor();
                    System.out.println();
                    break;
                case 3:
                    publisherService.createPublisher();
                    System.out.println();
                    break;
                case 4:
                    bookService.showBooks();
                    System.out.println("1- Dar de baja un libro");
                    System.out.println("2- Dar de alta un libro");
                    option2 = scan.nextInt();
                    switch (option2) {
                        case 1:
                            System.out.println("Ingrese el ISBN del libro que desea dar de baja");
                            Long bookIsbn = scan.nextLong();
                            bookService.disable(bookIsbn);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Ingrese el ISBN del libro que desea dar de alta");
                            bookIsbn = scan.nextLong();
                            bookService.activate(bookIsbn);
                            System.out.println();
                            break;
                    }
                    break;
                case 5:
                    authorService.showAuthors();
                    System.out.println("1- Dar de baja un autor");
                    System.out.println("2- Dar de alta un autor");
                    option2 = scan.nextInt();
                    switch (option2) {
                        case 1:
                            System.out.println("Ingrese el nombre del autor que desea dar de baja");
                            String bookAuthor = scan.next();
                            authorService.disable(bookAuthor);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Ingrese el nombre del autor que desea dar de alta");
                            bookAuthor = scan.next();
                            authorService.activate(bookAuthor);
                            System.out.println();
                            break;
                    }
                    break;
                case 6:
                    publisherService.showPublishers();
                    System.out.println("1- Dar de baja una editorial");
                    System.out.println("2- Dar de alta una editorialr");
                    option2 = scan.nextInt();
                    switch (option2) {
                        case 1:
                            System.out.println("Ingrese el nombre de la editorial que desea dar de baja");
                            String publisherName = scan.next();
                            publisherService.disable(publisherName);
                            break;
                        case 2:
                            System.out.println("Ingrese el nombre de la editorial que desea dar de alta");
                            publisherName = scan.next();
                            publisherService.activate(publisherName);
                            System.out.println();
                            break;
                    }
                    break;
                case 7:
                    bookService.showBooks();
                    System.out.println("1- Editar nombre del libro");
                    System.out.println("2- Editar año del libro");
                    option2 = scan.nextInt();
                    switch (option2) {
                        case 1:
                            System.out.println("Ingrese el nombre del libro para modificarlo");
                            String bookName = scan.next();
                            
                            System.out.println("Ingrese el nuevo nombre");
                            String newBookName = scan.next();
                            
                            bookService.modifyBookName(bookName, newBookName);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Ingrese el nombre del libro para modificarlo");
                            bookName = scan.next();
                            
                            System.out.println("Ingrese el año del libro para modificarlo");
                            Integer newBookYear = scan.nextInt();
                            
                            bookService.modifyBookYear(bookName, newBookYear);
                            System.out.println();
                            break;
                    }
                    break;
                case 8:
                    authorService.showAuthors();
                    
                    System.out.println("Ingrese el nombre del autor que desea editar");
                    String authorName = scan.next();
                    
                    System.out.println("Ingrese el nuevo nombre");
                    String newAuthorName = scan.next();
                    
                    authorService.modifyAuthorName(authorName, newAuthorName);
                    System.out.println();
                    break;
                case 9:
                    publisherService.showPublishers();
                    System.out.println("Ingrese el nombre de la editorial que desea editar");
                    String publisherName = scan.next();
                    
                    System.out.println("Ingrese el nuevo nombre");
                    String newPublisherName = scan.next();
                    
                    authorService.modifyAuthorName(publisherName, newPublisherName);
                    System.out.println();
                    break;
                case 10:
                    System.out.println("Ingrese el nombre del autor");
                    authorName = scan.next();
                    authorService.getAuthorByName(authorName);
                    System.out.println();

                    break;
                case 11:
                    System.out.println("Ingrese el nombre del libro");
                    String bookName = scan.next();
                    bookService.getBookByName(bookName);
                    System.out.println();
                    break;
                case 12:
                    authorService.showAuthors();
                    System.out.println();
                    break;
                case 13:
                    publisherService.showPublishers();
                    System.out.println();
                    break;
                case 14:
                    bookService.showBooks();
                    System.out.println();
                    break;
                case 15:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (option!=15);

    }

}
