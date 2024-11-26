package test.java;

import main.java.Book;
import main.java.AbstractBook;
import main.java.EBook;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EBookTest extends AbstractBookTest {

    private final EBook ebook = new EBook("EBookTitle", "EBookAuthor", "9876EBook5432", 4.6);

    @Override
    @Test
    void displayInfoTest() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            ebook.displayInfo(); // Use the EBook instance
            String output = outputStream.toString().trim();
            String expectedOutput = "Java.EBook - Title: " + ebook.getTitle()
                    + "\nAuthor: " + ebook.getAuthor()
                    + "\nISBN: " + ebook.getIsbn()
                    + "\nFile Size: " + ebook.getFileSizeMB() + "MB"
                    + "\nAvailable: " + ebook.getAvailable();
            assertEquals(expectedOutput, output);
        } finally {
            System.setOut(originalOut);
        }
    }
}
