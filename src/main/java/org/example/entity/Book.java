package org.example.entity;

public class Book {
    String id;
    String title;
    String author;
    int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    String availability_status;
    String genre;

    public Book(){
    }

    public Book(String id, String title, String author, String genre, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvailability_status() {
        return availability_status;
    }

    public void setAvailability_status(String availability_status) {
        this.availability_status = availability_status;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "-------------------------------" + "\n"+
                "id : " + id + "\n" +
                "title :" + title + "\n" +
                "author : " + author + "\n" +
                "quantity : " + quantity +"\n"+
                "availability_status : " + availability_status + "\n" +
                "genre : " + genre + "\n";
    }
}
