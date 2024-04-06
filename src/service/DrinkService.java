package service;

import model.Drink;
import persistence.DrinkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrinkService {
    private Scanner scanner = new Scanner(System.in);
    private DrinkRepository drinkRepository = new DrinkRepository();

    public void addDrink() {
        System.out.println("Enter drink details:");
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Is alcoholic? (true/false): ");
        boolean alcoholic = scanner.nextBoolean();
        System.out.println("Calories: ");
        int calories = scanner.nextInt();
        scanner.nextLine();

        Drink newDrink = new Drink(name, price, alcoholic, calories);

        drinkRepository.add(newDrink);
        System.out.println("Drink added successfully.");
    }

    public boolean checkDrinkExists(int drinkId) {
        return drinkRepository.checkDrinkExists(String.valueOf(drinkId));
    }

    public void showAllDrinks() {
        System.out.println("List of drinks:");
        drinkRepository.getAllDrinks().forEach(System.out::println);
    }

    public List<Drink> suggestDrinksByPrice(double maxPrice) {
        List<Drink> suggestedDrinks = new ArrayList<>();
        List<Drink> allDrinks = drinkRepository.getAllDrinks();

        for (Drink drink : allDrinks) {
            if (drink.getPrice() <= maxPrice) {
                suggestedDrinks.add(drink);
            }
        }

        return suggestedDrinks;
    }

}
