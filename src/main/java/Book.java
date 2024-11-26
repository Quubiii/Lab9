package main.java;

public class Book extends AbstractBook {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        super(title, author, isbn);
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + getTitle() + "\nAuthor: " + getAuthor() + "\nISBN: " + getIsbn() + "\nAvailable: " + getAvailable());
    }
}
