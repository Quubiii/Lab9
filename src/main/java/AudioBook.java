package main.java;

public class AudioBook extends AbstractBook {
    private double fileSizeMB;
    private double duration;

    public AudioBook(String title, String author, String isbn, double fileSizeMB, double duration) {
        super(title, author, isbn);
        this.fileSizeMB = fileSizeMB;
        this.duration = duration;
    }

    //Setters and Getters for additional attributes
    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public void setFileSizeMB(double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public void displayInfo() {
        System.out.println("Java.AudioBook - Title: " + getTitle() + "\nAuthor: " + getAuthor() + "\nISBN: " + getIsbn() + "\nAvailable: " + getAvailable() + "\nFile Size (MB): " + getFileSizeMB() + "\nDuration (min): " + getDuration());
    }
}