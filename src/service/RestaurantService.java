package service;

import model.Restaurant;
import persistence.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantService {
    private RestaurantRepository restaurantRepository;
    private Scanner scanner;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.scanner = new Scanner(System.in);
    }

    public void addRestaurant() {
        System.out.println("Introduceți detaliile restaurantului:");
        System.out.print("Nume: ");
        String name = scanner.nextLine();
        System.out.print("Adresă: ");
        String address = scanner.nextLine();
        System.out.print("Anul înființării: ");
        int establishmentYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Tipul de bucătărie: ");
        String cuisineType = scanner.nextLine();
        System.out.print("Program de funcționare: ");
        String openingHours = scanner.nextLine();

        Restaurant newRestaurant = new Restaurant(name, address, establishmentYear, cuisineType, openingHours);
        restaurantRepository.add(newRestaurant);
        System.out.println("Restaurant adăugat cu succes.");
    }

    public void searchRestaurantsByCuisineType(String cuisineType) {
        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();

        List<Restaurant> matchingRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getCuisineType().equalsIgnoreCase(cuisineType)) {
                matchingRestaurants.add(restaurant);
            }
        }

        if (!matchingRestaurants.isEmpty()) {
            System.out.println("Restaurantele din categoria \"" + cuisineType + "\":");
            for (Restaurant restaurant : matchingRestaurants) {
                System.out.println(restaurant.getName() + " - " + restaurant.getAddress());
            }
        } else {
            System.out.println("Nu există restaurante în categoria \"" + cuisineType + "\".");
        }
    }
}
