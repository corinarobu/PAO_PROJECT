package model;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;
    private double price;
    private List<String> allergens;
    private boolean vegetarian;
    private int calories;

    public Food(String name, double price, List<String> allergens, boolean vegetarian, int calories) {
        this.name = name;
        this.price = price;
        this.allergens = allergens;
        this.vegetarian = vegetarian;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }


}
