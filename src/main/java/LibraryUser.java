package main.java;

import java.io.*;
import java.util.ArrayList;

public class LibraryUser {
    private final String name;
    private final String userId;
    private final ArrayList<AbstractBook> borrowedBooks = new ArrayList<>();
    private final ArrayList<AbstractBook> historicalBorrowedBooks = new ArrayList<>();

    public ArrayList<AbstractBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public LibraryUser(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public void borrowBook(AbstractBook book) {
        if (borrowedBooks.contains(book)) {
            System.out.println("Java.Book is already borrowed by the user.");
            return;
        }
        if (book.getAvailable()) {
            book.borrow();
            borrowedBooks.add(book);
            historicalBorrowedBooks.add(book);
            System.out.println("Java.Book borrowed: " + book.getTitle());
        } else {
            System.out.println("Java.Book is not available to borrow.");
        }
    }


    public void returnBook(AbstractBook book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
            System.out.println("Java.Book returned: " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed by the user.");
        }
    }


    public void displayBorrowedBooks() {
        System.out.println("\n-------- Currently Borrowed Books --------");
        for (AbstractBook book : borrowedBooks) {
            book.displayInfo();
            System.out.println();
        }
    }

    public void displayOnlyAudiobooks() {
        System.out.println("Currently borrowed audiobooks:");
        for (AbstractBook book : borrowedBooks) {
            if (book instanceof AudioBook) {
                System.out.println(book.getTitle());
            }
        }
    }

    public void displayOnlyEbooks() {
        System.out.println("Currently borrowed ebooks:");
        for (AbstractBook book : borrowedBooks) {
            if (book instanceof EBook) {
                System.out.println(book.getTitle());
            }
        }
    }

    // Save borrowed books to a file
    public void saveBorrowedBooksToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("-------- User Data --------");
            writer.newLine();
            writer.write("User: " + name + ", ID: " + userId);
            writer.newLine();
            writer.write("-------- Currently Borrowed Books --------");
            writer.newLine();
            for (AbstractBook book : borrowedBooks) {
                writer.write(book.getTitle());
                writer.newLine();
            }
            writer.write("-------- Historical Borrowed Books --------");
            writer.newLine();
            for (AbstractBook book : historicalBorrowedBooks) {
                writer.write(book.getTitle());
                writer.newLine();
            }
            System.out.println("\n-------- System Info --------");
            System.out.println("Borrowed books saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
