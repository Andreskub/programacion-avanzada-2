package edu.usal.domain;

import java.time.LocalDate;

public class Reservation {

    private int id;
    private Book book;
    private Client client;
    private LocalDate reservationDate;
    private boolean isApproved;
    private LocalDate returnDueDate;

    public Reservation() {}

    public Reservation(int id, Book book, Client client, LocalDate reservationDate, boolean isApproved, LocalDate returnDueDate) {
        this.id = id;
        this.book = book;
        this.client = client;
        this.reservationDate = reservationDate;
        this.isApproved = isApproved;
        this.returnDueDate = returnDueDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public LocalDate getReturnDueDate() {
        return returnDueDate;
    }

    public void setReturnDueDate(LocalDate returnDueDate) {
        this.returnDueDate = returnDueDate;
    }

}
