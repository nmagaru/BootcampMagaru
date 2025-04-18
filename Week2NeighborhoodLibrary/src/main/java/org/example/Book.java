package org.example;

import java.time.LocalDate;

public class Book {
    //properties
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;
    //STRETCH: add due date, initialize to null
    private LocalDate dueDate;


    //constructors
    public Book(int id, String isbn, String title, boolean isCheckedOut, String checkedOutTo) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;
        this.dueDate = null;
    }


    //getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    //methods
    public void checkOut(String name) {
        isCheckedOut = true;
        checkedOutTo = name;
    }

    public void checkIn() {
        isCheckedOut= false;
        checkedOutTo = "";
    }


    //toString override
    //STRETCH: update toString to include due date
    @Override
    public String toString() {
        return
                "id: " + id +
                ", isbn: " + isbn +
                ", title: " + title +
                ", due date: " + dueDate;
    }
}
