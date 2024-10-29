package edu.usal.domain;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String isbn;
    private int pageCount;
    private String genre;
    private String edition;
    private Author author;
    private List<Publisher> publishers;
    private int availableCopies;

    public Book() {}

    public Book(String title, String isbn, int pageCount, String genre, String edition, Author author, List<Publisher> publishers, int availableCopies) {
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.genre = genre;
        this.edition = edition;
        this.author = author;
        this.publishers = publishers;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublisher(Publisher publisher) {
        this.publishers.add(publisher);
    }

    public int getId() {
        return id;
    }
}
