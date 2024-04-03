package model;

public class Drink {
    private String name;
    private double price;
    private boolean alcoholic;
    private int calories;

    public Drink(String name, double price, boolean alcoholic, int calories) {
        this.name = name;
        this.price = price;
        this.alcoholic = alcoholic;
        this.calories = calories;
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
