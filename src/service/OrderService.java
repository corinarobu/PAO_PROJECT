package service;

import model.Drink;
import model.Food;
import model.Order;
import persistence.DrinkRepository;
import persistence.FoodRepository;
import persistence.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private OrderRepository orderRepository;
    private List<Order> processedOrdersList; // Lista pentru comenzi procesate

    private FoodService foodService = new FoodService();


    private DrinkService drinkService = new DrinkService();


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.processedOrdersList = new ArrayList<>(); // Inițializare listă de comenzi procesate
    }

    public void addOrder(List<String> foodList, List<String> drinkList, String paymentMethod, String desiredArrivalTime) {
        Order newOrder = new Order(foodList, drinkList, paymentMethod, desiredArrivalTime);
        orderRepository.add(newOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void placeOrder() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Introduceți numărul de alimente
            System.out.print("Enter the number of food items: ");
            int numFoodItems = scanner.nextInt();
            scanner.nextLine(); // Consumăm newline left-over

            // Introduceți numele alimentelor
            List<String> foodList = new ArrayList<>();
            for (int i = 0; i < numFoodItems; i++) {
                System.out.print("Enter food item " + (i + 1) + ": ");
                String foodItem = scanner.nextLine();
                foodList.add(foodItem);
            }

            // Introduceți numărul de băuturi
            System.out.print("Enter the number of drink items: ");
            int numDrinkItems = scanner.nextInt();
            scanner.nextLine(); // Consumăm newline left-over

            // Introduceți numele băuturilor
            List<String> drinkList = new ArrayList<>();
            for (int i = 0; i < numDrinkItems; i++) {
                System.out.print("Enter drink item " + (i + 1) + ": ");
                String drinkItem = scanner.nextLine();
                drinkList.add(drinkItem);
            }

            // Introduceți metoda de plată
            System.out.print("Enter payment method: ");
            String paymentMethod = scanner.nextLine();

            // Introduceți ora de sosire dorită
            System.out.print("Enter desired arrival time: ");
            String desiredArrivalTime = scanner.nextLine();

            // Adăugăm comanda în repository
            addOrder(foodList, drinkList, paymentMethod, desiredArrivalTime);
            System.out.println("Order placed successfully.");
            break; // Ieșim din buclă după ce comanda este plasată cu succes
        }
    }






    public void processOrder() {
        List<Order> ordersList = orderRepository.getAllOrders();
        if (!ordersList.isEmpty()) {
            Order orderToProcess = ordersList.remove(0);
            processedOrdersList.add(orderToProcess);
            orderRepository.delete(orderToProcess); // Eliminăm comanda din lista de comenzi pendinte
            System.out.println("Order processed successfully.");
        } else {
            System.out.println("No orders to process.");
        }
    }


    public void displayPendingOrders() {
        List<Order> pendingOrders = orderRepository.getAllOrders();
        if (!pendingOrders.isEmpty()) {
            System.out.println("Pending Orders:");
            for (Order order : pendingOrders) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Food items:");
                for (String food : order.getFoodList()) {
                    System.out.println("- " + food);
                }
                System.out.println("Drink items:");
                for (String drink : order.getDrinkList()) {
                    System.out.println("- " + drink);
                }
                System.out.println("Payment method: " + order.getPaymentMethod());
                System.out.println("Desired arrival time: " + order.getDesiredArrivalTime());
                System.out.println();
            }
        } else {
            System.out.println("No pending orders.");
        }
    }

    public void displayProcessedOrders() {
        if (!processedOrdersList.isEmpty()) {
            System.out.println("Processed Orders:");
            for (Order order : processedOrdersList) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Food items:");
                for (String food : order.getFoodList()) {
                    System.out.println("- " + food);
                }
                System.out.println("Drink items:");
                for (String drink : order.getDrinkList()) {
                    System.out.println("- " + drink);
                }
                System.out.println("Payment method: " + order.getPaymentMethod());
                System.out.println("Desired arrival time: " + order.getDesiredArrivalTime());
                System.out.println();
            }
        } else {
            System.out.println("No processed orders yet.");
        }
    }


}
