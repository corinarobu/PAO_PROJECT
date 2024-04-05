package model;

public class Drink {

    static private int idCounter = 0;
    private int id;
    private String name;
    private double price;
    private boolean alcoholic;
    private int calories;

    public Drink(String name, double price, boolean alcoholic, int calories) {
        ++idCounter;
        this.id = idCounter;
        this.name = name;
        this.price = price;
        this.alcoholic = alcoholic;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", alcoholic=" + alcoholic +
                ", calories=" + calories +
                '}';
    }
}
