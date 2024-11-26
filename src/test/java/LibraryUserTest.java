package test.java;

import main.java.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryUserTest {

    @Test
    void borrowBookTest() {
        // Create a new LibraryUser instance for this test
        LibraryUser user = new LibraryUser("John Doe", "12345");
        Book book = new Book("Test Book", "Test Author", "ISBN123");
        assertTrue(book.getAvailable());

        // Borrow the book
        user.borrowBook(book);

        // Check the book's availability and that it is in the borrowedBooks list
        assertFalse(book.getAvailable());
        assertEquals(1, user.getBorrowedBooks().size());

        // Attempt to borrow the same book again
        user.borrowBook(book);

        // Ensure the book is only borrowed once
        assertEquals(1, user.getBorrowedBooks().size());
        assertFalse(book.getAvailable());
    }

    @Test
    void returnBookTest() {
        // Create a new LibraryUser instance for this test
        LibraryUser user = new LibraryUser("John Doe", "12345");
        Book book = new Book("Test Book", "Test Author", "ISBN123");

        // Borrow the book
        user.borrowBook(book);

        // Return the book
        user.returnBook(book);

        // Ensure the book is now available and removed from the borrowedBooks list
        assertTrue(book.getAvailable());
        assertEquals(0, user.getBorrowedBooks().size());

        // Attempt to return the same book again
        user.returnBook(book);

        // Ensure no duplicate return operation is allowed
        assertTrue(book.getAvailable());
        assertEquals(0, user.getBorrowedBooks().size());
    }

    @Test
    void displayBorrowedBooksTest() {
        // Create a new LibraryUser instance for this test
        LibraryUser user = new LibraryUser("John Doe", "12345");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Create and borrow books
            Book book1 = new Book("Book 1", "Author 1", "ISBN1");
            EBook ebook = new EBook("EBook 1", "Author E", "ISBN2", 5.0);
            user.borrowBook(book1);
            user.borrowBook(ebook);

            // Display borrowed books
            user.displayBorrowedBooks();

            // Capture the output
            String output = outputStream.toString().trim();
            assertTrue(output.contains("-------- Currently Borrowed Books --------"));
            assertTrue(output.contains("Book 1"));
            assertTrue(output.contains("EBook 1"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void displayOnlyAudiobooksTest() {
        // Create a new LibraryUser instance for this test
        LibraryUser user = new LibraryUser("John Doe", "12345");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Create and borrow books
            AudioBook audiobook = new AudioBook("AudioBook 1", "Author A", "ISBN3", 10.0, 120.0);
            Book regularBook = new Book("Regular Book", "Author R", "ISBN4");
            EBook ebook = new EBook("EBook 1", "Author E", "ISBN2", 5.0);

            // Borrow books of different types
            user.borrowBook(audiobook);
            user.borrowBook(regularBook);
            user.borrowBook(ebook);

            // Call displayOnlyAudiobooks
            user.displayOnlyAudiobooks();

            // Capture the output
            String output = outputStream.toString().trim();

            // Check that the output only includes the audiobook and excludes others
            assertTrue(output.contains("Currently borrowed audiobooks:"));
            assertTrue(output.contains("AudioBook 1")); // Audiobook should appear
            assertFalse(output.contains("Regular Book")); // Regular book should not appear
            assertFalse(output.contains("EBook 1")); // Ebook should not appear
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void displayOnlyEbooksTest() {
        // Create a new LibraryUser instance for this test
        LibraryUser user = new LibraryUser("John Doe", "12345");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Create and borrow books
            EBook ebook = new EBook("EBook 1", "Author E", "ISBN2", 5.0);
            Book regularBook = new Book("Regular Book", "Author R", "ISBN4");
            AudioBook audiobook = new AudioBook("AudioBook 1", "Author A", "ISBN3", 10.0, 120.0);

            // Borrow books of different types
            user.borrowBook(ebook);
            user.borrowBook(regularBook);
            user.borrowBook(audiobook);

            // Call displayOnlyEbooks
            user.displayOnlyEbooks();

            // Capture the output
            String output = outputStream.toString().trim();

            // Check that the output only includes the ebook and excludes others
            assertTrue(output.contains("Currently borrowed ebooks:"));
            assertTrue(output.contains("EBook 1")); // Ebook should appear
            assertFalse(output.contains("Regular Book")); // Regular book should not appear
            assertFalse(output.contains("AudioBook 1")); // Audiobook should not appear
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void saveBorrowedBooksToFileTest() throws IOException {
        // Create a new LibraryUser instance for this test
        LibraryUser user = new LibraryUser("John Doe", "12345");
        // Create and borrow books
        Book book = new Book("Test Book", "Test Author", "ISBN123");
        user.borrowBook(book);

        // Use a temporary file for testing
        File tempFile = Files.createTempFile("borrowed_books", ".txt").toFile();

        try {
            // Save to the temporary file
            user.saveBorrowedBooksToFile(tempFile.getAbsolutePath());

            // Verify file contents
            try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
                String content = reader.lines().reduce("", (acc, line) -> acc + "\n" + line).trim();
                assertTrue(content.contains("-------- User Data --------"));
                assertTrue(content.contains("User: John Doe, ID: 12345"));
                assertTrue(content.contains("-------- Currently Borrowed Books --------"));
                assertTrue(content.contains("Test Book"));
            }
        } finally {
            // Clean up the temporary file
            tempFile.delete();
        }
    }
}
