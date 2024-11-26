package main.java;

public class EBook extends AbstractBook {
    private double fileSizeMB;

    public EBook(String title, String author, String isbn, double fileSizeMB) {
        super(title, author, isbn);
        this.fileSizeMB = fileSizeMB;
    }

    //Setter and Getter for additional attribute
    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public void setFileSizeMB(double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public void displayInfo() {
        System.out.println("Java.EBook - Title: " + getTitle() + "\nAuthor: " + getAuthor() + "\nISBN: " + getIsbn() + "\nFile Size: " + getFileSizeMB() + "MB\nAvailable: " + getAvailable());
    }
}
