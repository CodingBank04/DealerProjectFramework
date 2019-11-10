package models;

public class Car {
    public int year;
    public int stock;
    public String make;
    public String model;
    public String color;
    public String image;

    public Car(int year, int stock, String make, String model, String color, String image) {
        this.year = year;
        this.stock = stock;
        this.make = make;
        this.model = model;
        this.color = color;
        this.image = image;
    }
}
