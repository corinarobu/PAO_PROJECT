package jdbc.model;

import java.util.List;

public class Food {

    static private int idCounter = 0;
    private int id;
    private String name;
    private int price;
    private int vegetarian;
    private int calories;

    public Food(String name, int price, int vegetarian, int calories) {
        idCounter++;
        this.id = idCounter;
        this.name = name;
        this.price = price;
        this.vegetarian = vegetarian;
        this.calories = calories;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public int isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                '}';
    }


}
