package main.java;

public abstract class AbstractBook implements LibraryItem {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable = true;

    public AbstractBook(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }


    //Setters and Getters
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean getAvailable() {
        return isAvailable;
    }
    public void displayInfo() {
        System.out.println("Title: " + getTitle() + ", Author: " + getAuthor() + ", ISBN: " + getIsbn() + ", Available: " + getAvailable());
    }

    public void isAvailable() {
        if(isAvailable) {
            System.out.println("Java.Book is available");
        } else {
            System.out.println("Java.Book is not available");
        }
    }

    public void borrow() {
        if(isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("The book is already borrowed.");
        }
    }

    public void returnBook() {
        if(!isAvailable) {
            isAvailable = true;
        } else {
            System.out.println("Cannot return book that is not borrowed.");
        }
    }

}
