package view;

import service.UserService;
import java.util.Scanner;

import exceptions.InvalidDataException;

public class ConsoleApp {

    private Scanner scanner = new Scanner(System.in);
    private UserService service = new UserService();

    public static void main(String args[]) {
        ConsoleApp app = new ConsoleApp();
        while (true) {
            app.showMenu();
            int option = app.readOption();
            app.execute(option);
        }
    }

    private void showMenu() {
        System.out.println("   _____                                               _____                 \n" +
                "  /  _  \\__  _  __ ____   __________   _____   ____   /  _  \\ ______ ______  \n" +
                " /  /_\\  \\ \\/ \\/ // __ \\ /  ___/  _ \\ /     \\_/ __ \\ /  /_\\  \\\\____ \\\\____ \\ \n" +
                "/    |    \\     /\\  ___/ \\___ (  <_> )  Y Y  \\  ___//    |    \\  |_> >  |_> >\n" +
                "\\____|__  /\\/\\_/  \\___  >____  >____/|__|_|  /\\___  >____|__  /   __/|   __/ \n" +
                "        \\/            \\/     \\/            \\/     \\/        \\/|__|   |__|    ");
        System.out.println("What do you want to do?");
        System.out.println("1. Place an order");
        System.out.println("2. Update profile");
        System.out.println("3. Add a review");
        System.out.println("4. Add products to the menu");
        System.out.println("5. Process order");
        System.out.println("6. Search for restaurants and products");
        System.out.println("7. Generate sales report");
        System.out.println("8. Delete client");
        System.out.println("9. Take order");
        System.out.println("10. Menu suggestion");
        System.out.println("11. Exit");
    }

    private int readOption() {
        try {
            int option = readInt();
            if (option >= 1 && option <= 11) {
                return option;
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and 11.");
            }
        } catch (InvalidDataException invalid) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        return readOption();
    }


    private void execute(int option) {
        switch (option) {
            case 1:
                //placeOrder();
                break;
            case 2:
                System.out.println("ai ales 2");
                break;
            case 3:
                //addReview();
                break;
            case 4:
                //addProducts();
                break;
            case 5:
                //processOrder();
                break;
            case 6:
                //search();
                break;
            case 7:
                //generateSalesRaport();
                break;
            case 8:
                //deleteClient();
                break;
            case 9:
                //takeOrder();
                break;
            case 10:
                //menuSuggestion();
                break;
            case 11:
                System.exit(0);
        }
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
