package test.java;

import main.java.Book;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest extends AbstractBookTest {

    private final Book book = new Book("BookTitle", "BookAuthor", "1234Book5678");

    @Override
    @Test
    void displayInfoTest() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            book.displayInfo(); // Use the Book instance
            String output = outputStream.toString().trim();
            String expectedOutput = "Title: " + book.getTitle()
                    + "\nAuthor: " + book.getAuthor()
                    + "\nISBN: " + book.getIsbn()
                    + "\nAvailable: " + book.getAvailable();
            assertEquals(expectedOutput, output);
        } finally {
            System.setOut(originalOut);
        }
    }
}
