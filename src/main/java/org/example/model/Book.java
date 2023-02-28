package org.example.model;

public class Book {
    private String author;
    private String title;
    private String ISBN;


    public Book(String author, String title, String ISBN) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.author)
                .append(" \"")
                .append(this.title)
                .append("\", ISBN: ")
                .append(this.ISBN)
                .toString();
    }
}
