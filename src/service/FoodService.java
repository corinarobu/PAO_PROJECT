package service;

import model.Food;
import persistence.FoodRepository;

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
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter allergens (comma separated list): ");
        String allergensInput = scanner.nextLine();
        List<String> allergens = Arrays.asList(allergensInput.split(","));
        System.out.println("Is vegetarian? (true/false): ");
        boolean vegetarian = scanner.nextBoolean();
        System.out.println("Calories: ");
        int calories = scanner.nextInt();
        scanner.nextLine();

        Food newFood = new Food(name, price, allergens, vegetarian, calories);

        foodRepository.add(newFood);
        System.out.println("Food added successfully.");
    }

    public boolean checkFoodExists(int foodId) {
        return foodRepository.checkFoodExists(foodId);
    }


    public void showAllFood() {
        System.out.println("List of foods:");
        foodRepository.getAllFood().forEach(System.out::println);
    }

    public List<Food> suggestFoodByPrice(double maxPrice) {
        List<Food> suggestedFood = new ArrayList<>();
        List<Food> allFood = foodRepository.getAllFood();

        for (Food food : allFood) {
            if (food.getPrice() <= maxPrice) {
                suggestedFood.add(food);
            }
        }

        return suggestedFood;
    }

}
