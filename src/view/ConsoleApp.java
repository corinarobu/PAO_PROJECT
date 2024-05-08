package view;

import jdbc.model.Drink;
import jdbc.model.Food;
import jdbc.persistence.ClientRepository;
import jdbc.persistence.EmployeeRepository;
import jdbc.persistence.OrderRepository;
import jdbc.persistence.RestaurantRepository;
import jdbc.service.*;

import java.util.List;
import java.util.Scanner;

import jdbc.exceptions.InvalidDataException;

public class ConsoleApp {
    private Scanner scanner = new Scanner(System.in);
    private FoodService serviceFood = new FoodService();
    private DrinkService serviceDrink = new DrinkService();


    private RestaurantRepository restaurantRepository = new RestaurantRepository();
    private RestaurantService serviceRestaurant = new RestaurantService(restaurantRepository);


    private ClientRepository clientRepository = new ClientRepository();
//        ClientRepository clientRepository = ClientRepository.getInstance();
    private EmployeeRepository employeeRepository = new EmployeeRepository();
//     EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
    private UserService serviceUser = new UserService(clientRepository, employeeRepository);


    private OrderRepository orderRepository = new OrderRepository();
    private OrderService orderService = new OrderService(orderRepository);

    public ConsoleApp() throws InvalidDataException {
    }


    public static void main(String args[]) throws InvalidDataException {
        ConsoleApp app = new ConsoleApp();
        while (true) {
            app.showMenu();
            int option = app.readOption();
            app.execute(option);
        }
    }

    private void showMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add food");
        System.out.println("2. Show foods");
        System.out.println("3. Add drink");
        System.out.println("4. Show drinks");
        System.out.println("5. Add user");
        System.out.println("6. Show users");
        System.out.println("7. Place an order");
        System.out.println("8. View placed orders");
        System.out.println("9. Process an order");
        System.out.println("10. View processed orders");
        System.out.println("11. Update profile");
        System.out.println("12. Add a review");
        System.out.println("13. Search for restaurants");
        System.out.println("14. Delete client");
        System.out.println("15. Add restaurant");
        System.out.println("16. Suggest food and drink by price");
        System.out.println("17. Exit");
    }

    private void execute(int option) {
        switch (option) {
            case 1:
                serviceFood.addFood();
                break;
            case 2:
                serviceFood.showAllFood();
                break;
            case 3:
                serviceDrink.addDrink();
                break;
            case 4:
                serviceDrink.showAllDrinks();
                break;
            case 5:
                serviceUser.addUser();
                break;
            case 6:
                serviceUser.showUsers();
                break;
            case 7:
                orderService.placeOrder();
                break;
            case 8:
                orderService.displayPendingOrders();
                break;
            case 9:
                orderService.processOrder();
                break;
            case 10:
                orderService.displayProcessedOrders();
                break;
            case 11:
                serviceUser.updateUserProfile();
                break;
            case 12:
                serviceUser.leaveReview();
                System.out.print("Doriți să afișați toate review-urile? (da/nu): ");
                String showReviewsOption = scanner.nextLine().trim().toLowerCase();
                if (showReviewsOption.equals("da")) {
                    serviceUser.showAllReviews();
                }
                break;
            case 13:
                System.out.print("Introduceți tipul de bucătărie pentru căutare: ");
                String cuisineType = scanner.nextLine();
                serviceRestaurant.searchRestaurantsByCuisineType(cuisineType);
                break;

            case 14:
                serviceUser.deleteUser();
                break;
            case 15:
                serviceRestaurant.addRestaurant();
                break;
            case 16:
                System.out.print("Introduceți prețul maxim dorit: ");
                double maxPrice = Double.parseDouble(scanner.nextLine());

                System.out.println("Sugestii meniu pentru prețul de " + maxPrice + ":");

                List<Food> suggestedFood = serviceFood.suggestFoodByPrice(maxPrice);
                List<Drink> suggestedDrinks = serviceDrink.suggestDrinksByPrice(maxPrice);

                if (suggestedFood.isEmpty() && suggestedDrinks.isEmpty()) {
                    System.out.println("Nu există alimente sau băuturi disponibile în această gamă de prețuri.");
                } else {
                    System.out.println("Alimente:");
                    for (Food food : suggestedFood) {
                        System.out.println(food.getName() + " - " + food.getPrice());
                    }

                    System.out.println("Băuturi:");
                    for (Drink drink : suggestedDrinks) {
                        System.out.println(drink.getName() + " - " + drink.getPrice());
                    }
                }
                break;

            case 17:
                System.exit(0);
        }
    }

    private int readOption() {
        try {
            int option = readInt();
            if (option >= 1 && option <= 17) {
                return option;
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and 17.");
            }
        } catch (InvalidDataException invalid) {

            System.out.println("Invalid input. Please enter a valid number.");
        }
        return readOption();
    }


    private int readInt() throws InvalidDataException {
        String line = scanner.nextLine();
        if (line.matches("^\\d+$")) {
            return Integer.parseInt(line);
        } else {
            throw new InvalidDataException("Invalid number");
        }
    }
}
