package main.java;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Read files and create objects
        ArrayList<AbstractBook> books = new ArrayList<>(readBookFile("src/main/resources/book.txt"));
        ArrayList<AbstractBook> ebooks = new ArrayList<>(readEBookFile("src/main/resources/ebook.txt"));
        ArrayList<AbstractBook> audioBooks = new ArrayList<>(readAudioBookFile("src/main/resources/audiobook.txt"));
        LibraryUser user = readLibraryUserFile("src/main/resources/libraryuser.txt");

        if (user == null) {
            System.out.println("Error: Failed to load library user.");
            return;
        }

        // Display all books
        System.out.println("Available books in the library:");
        System.out.println("\n-------- Traditional Books --------");
        for (AbstractBook book : books) {
            book.displayInfo();
            System.out.println();
        }
        System.out.println("\n-------- Electronic Books --------");
        for(AbstractBook book : ebooks) {
            book.displayInfo();
            System.out.println();
        }
        System.out.println("\n-------- Audio Books --------");
        for(AbstractBook book :audioBooks) {
            book.displayInfo();
            System.out.println();
        }

        // Borrow books
        System.out.println("\n-------- Testing - Borrowing 3 Books --------");
        if (!books.isEmpty()) {
            user.borrowBook(books.get(1)); // Borrow the first book
        }
        if(!ebooks.isEmpty()) {
            user.borrowBook(ebooks.get(1));
        }
        if(!audioBooks.isEmpty()) {
            user.borrowBook(audioBooks.get(2));
        }

        // Display borrowed books
        user.displayBorrowedBooks();

        // Save borrowed books to a file
        user.saveBorrowedBooksToFile("src/main/resources/user_borrowed_books.txt");

        // Return books
        System.out.println("\n-------- Testing - Returning a Java.Book --------");
        if (!books.isEmpty()) {
            user.returnBook(books.get(1)); // Return the first borrowed book
        }

        // Display updated borrowed books
        user.displayBorrowedBooks();

        // Save updated borrowed books to the same file
        user.saveBorrowedBooksToFile("src/main/resources/user_borrowed_books.txt");
    }

    public static ArrayList<AbstractBook> readBookFile(String filename) {
        ArrayList<AbstractBook> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String title = data[0];
                    String author = data[1];
                    String isbn = data[2];
                    books.add(new Book(title, author, isbn));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<AbstractBook> readEBookFile(String filename) {
        ArrayList<AbstractBook> ebooks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String title = data[0];
                    String author = data[1];
                    String isbn = data[2];
                    double fileSizeMB = Double.parseDouble(data[3]);
                    ebooks.add(new EBook(title, author, isbn, fileSizeMB));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ebooks;
    }

    public static ArrayList<AbstractBook> readAudioBookFile(String filename) {
        ArrayList<AbstractBook> audiobooks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String title = data[0];
                    String author = data[1];
                    String isbn = data[2];
                    double fileSizeMB = Double.parseDouble(data[3]);
                    double duration = Double.parseDouble(data[4]);
                    audiobooks.add(new AudioBook(title, author, isbn, fileSizeMB, duration));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return audiobooks;
    }

    public static LibraryUser readLibraryUserFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String name = data[0];
                    String userId = data[1];
                    return new LibraryUser(name, userId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
