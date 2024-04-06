package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Drink;

public class DrinkRepository implements GenericRepository<Drink> {
    private Map<Integer, Drink> drinkMap = new HashMap<>();

    @Override
    public void add(Drink entity) {
        drinkMap.put(entity.getId(), entity);
    }

    @Override
    public Drink get(int id) {
        return drinkMap.get(id);
    }

    @Override
    public void update(Drink entity) {
        drinkMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Drink entity) {
        drinkMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return drinkMap.size();
    }

    public List<Drink> getAllDrinks() {
        return new ArrayList<>(drinkMap.values());
    }

    public boolean checkDrinkExists(String drinkItem) {
        for (Drink drink : drinkMap.values()) {
            if (drink.getName().equals(drinkItem)) {
                return true;
            }
        }
        return false;
    }


    public void showAllDrinks() {
        List<Drink> drinks = getAllDrinks();
        if (!drinks.isEmpty()) {
            System.out.println("Available Drinks:");
            for (Drink drink : drinks) {
                System.out.println("Name: " + drink.getName());
                System.out.println("Price: " + drink.getPrice());
                System.out.println("Alcoholic: " + (drink.isAlcoholic() ? "Yes" : "No"));
                System.out.println("Calories: " + drink.getCalories());
                System.out.println();
            }
        } else {
            System.out.println("No drinks available.");
        }
    }

}
