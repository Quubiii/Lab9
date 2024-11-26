package test.java;

import main.java.AudioBook;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AudioBookTest extends AbstractBookTest {

    private final AudioBook audiobook = new AudioBook("AudioBookTitle", "AudioBookAuthor", "5678Audio1234", 15.5, 120);

    @Override
    @Test
    void displayInfoTest() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            audiobook.displayInfo(); // Use the AudioBook instance
            String output = outputStream.toString().trim();
            String expectedOutput = "Java.AudioBook - Title: " + audiobook.getTitle()
                    + "\nAuthor: " + audiobook.getAuthor()
                    + "\nISBN: " + audiobook.getIsbn()
                    + "\nAvailable: " + audiobook.getAvailable()
                    + "\nFile Size (MB): " + audiobook.getFileSizeMB()
                    + "\nDuration (min): " + audiobook.getDuration();
            assertEquals(expectedOutput, output);
        } finally {
            System.setOut(originalOut);
        }
    }
}
