package test.java;

import main.java.Book;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;

public class AbstractBookTest {
    Book book = new Book("TestTitle", "TestAuthor", "1234Test4321");

    @Test
    void setterGetterTest() {
        //Testing if book was created properly
        assertEquals("TestTitle", book.getTitle());
        assertEquals("TestAuthor", book.getAuthor());
        assertEquals("1234Test4321", book.getIsbn());
        assertTrue(book.getAvailable());

        //Changing some data for test
        book.setTitle("TitleTest");
        book.setAuthor("AuthorTest");
        book.setIsbn("4321Test1234");

        //Checking if values were corrected successfully
        assertNotEquals("TestTitle", book.getTitle());
        assertEquals("TitleTest", book.getTitle());
        assertEquals("AuthorTest", book.getAuthor());
        assertEquals("4321Test1234", book.getIsbn());
    }

    @Test
    void isAvailableTest() {
       assertTrue(book.getAvailable());
       book.borrow();
       assertFalse(book.getAvailable());
    }

    @Test
    void borrowTest() {
        assertTrue(book.getAvailable());
        book.borrow();
        assertFalse(book.getAvailable());

        //Borrow again to ensure the state doesn't change
        book.borrow();
        assertFalse(book.getAvailable());
    }

    @Test
    void returnTest() {
        book.borrow();
        assertFalse(book.getAvailable());
        book.returnBook();
        assertTrue(book.getAvailable());

        //Return book when not borrowed and ensure the state doesn't change
        book.returnBook();
        assertTrue(book.getAvailable());
    }

    @Test
    void displayInfoTest() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            book.displayInfo();
            String output = outputStream.toString().trim();
            assertEquals("Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable: " + book.getAvailable(), output);
        } finally {
            System.setOut(originalOut);
        }
    }
}
