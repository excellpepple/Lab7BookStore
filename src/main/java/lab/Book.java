package lab;

/**
 * Name: 
 * Username: 
 */

public class Book {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.setTitle(title);
        this.setPrice(price);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = new String(title);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
