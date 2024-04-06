package view;

import persistence.ClientRepository;
import persistence.EmployeeRepository;
import persistence.OrderRepository;
import service.DrinkService;
import service.FoodService;
import service.OrderService;
import service.UserService;

import java.util.Scanner;

import exceptions.InvalidDataException;

public class ConsoleApp {
    private Scanner scanner = new Scanner(System.in);
    private FoodService serviceFood = new FoodService();
    private DrinkService serviceDrink = new DrinkService();


    private ClientRepository clientRepository = new ClientRepository();
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private UserService serviceUser = new UserService(clientRepository, employeeRepository);


    private OrderRepository orderRepository = new OrderRepository();
    private OrderService orderService = new OrderService(orderRepository);



    public static void main(String args[]) {
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
        System.out.println("13. Search for restaurants and products");
        System.out.println("14. Delete client");
        System.out.println("15. Exit");
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
                // Update profile
                break;
            case 12:
                // Add a review
                break;
            case 13:
                // Search for restaurants and products
                break;
            case 14:
                // Delete client
                break;
            case 15:
                System.exit(0);
        }
    }

    private int readOption() {
        try {
            int option = readInt();
            if (option >= 1 && option <= 15) {
                return option;
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and 15.");
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
