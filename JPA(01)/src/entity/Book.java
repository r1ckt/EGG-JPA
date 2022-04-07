package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Book implements Serializable {

    @Id
    private Long isbn;
    
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "book_year", length = 4)
    private Integer bookYear;
    @Column(name = "copies", nullable = false)
    private Integer copies;
    @Column(name = "borrowed_copies", nullable = false)
    private Integer borrowedCopies;
    @Column(name = "remained_copies", nullable = false)
    private Integer remainedCopies;
    @Column(name = "active", nullable = false)
    private Boolean register;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Author author;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Publisher publisher;

    public Book() {
    }

    public Book(Long isbn, String title, Integer bookYear, Integer copies, Integer borrowedCopies, Integer remainedCopies, Boolean register, Author author, Publisher publisher) {
        this.isbn = isbn;
        this.title = title;
        this.bookYear = bookYear;
        this.copies = copies;
        this.borrowedCopies = borrowedCopies;
        this.remainedCopies = remainedCopies;
        this.register = register;
        this.author = author;
        this.publisher = publisher;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBookYear() {
        return bookYear;
    }

    public void setBookYear(Integer bookYear) {
        this.bookYear = bookYear;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getBorrowedCopies() {
        return borrowedCopies;
    }

    public void setBorrowedCopies(Integer borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    public Integer getRemainedCopies() {
        return remainedCopies;
    }

    public void setRemainedCopies(Integer remainedCopies) {
        this.remainedCopies = remainedCopies;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return String.format("(BOOK) ISBN: %s, AUTHOR: %s, TITLE: %s, YEAR: %s, PUBLISHER: %s,"
                + " COPIES: %s, BORROWED COPIES: %s, REMAINED COPIES: %s, REGISTERED: %s)",
                isbn, author, title, bookYear, publisher, copies, borrowedCopies, remainedCopies, register);
    }

}
