package jdbc.service;

import jdbc.model.Food;
import jdbc.persistence.FoodRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FoodService {
    private Scanner scanner = new Scanner(System.in);
    private FoodRepository foodRepository = new FoodRepository();

    public void addFood() {
        System.out.println("Enter food details:");
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Price: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Is vegetarian? (0/1): ");
        int vegetarian = scanner.nextInt();
        System.out.println("Calories: ");
        int calories = scanner.nextInt();
        scanner.nextLine();

        Food newFood = new Food(name, price, vegetarian, calories);

        foodRepository.add(newFood);
        System.out.println("Food added successfully.");
    }


    public void showAllFood() {
        System.out.println("List of foods:");
        foodRepository.getAll().forEach(System.out::println);
    }

    public List<Food> suggestFoodByPrice(double maxPrice) {
        List<Food> suggestedFood = new ArrayList<>();
        List<Food> allFood = foodRepository.getAll();

        for (Food food : allFood) {
            if (food.getPrice() <= maxPrice) {
                suggestedFood.add(food);
            }
        }

        return suggestedFood;
    }


    public Food getFoodById(int foodId) {
        try {
            // Folosim metoda din FoodRepository pentru a obține alimentul
            return foodRepository.get(foodId);
        } catch (RuntimeException ex) {
            // Dacă nu putem găsi alimentul, aruncăm o excepție cu un mesaj corespunzător
            throw new RuntimeException("Food not found for id: " + foodId);
        }
    }


}
