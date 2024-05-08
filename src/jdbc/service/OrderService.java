package jdbc.service;

import jdbc.model.Drink;
import jdbc.model.Food;
import jdbc.model.Order;
import jdbc.persistence.OrderRepository;

import java.util.Scanner;

public class OrderService {
    private OrderRepository orderRepository;
    private Order processedOrder;


    private FoodService foodService;
    private DrinkService drinkService;

    public OrderService(OrderRepository orderRepository, FoodService foodService, DrinkService drinkService) {
        this.orderRepository = orderRepository;
        this.foodService = foodService;
        this.drinkService = drinkService;
    }

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.foodService = new FoodService(); // Inițializați foodService aici
        this.drinkService = new DrinkService(); // Inițializați drinkService aici
    }



    public void addOrder(String paymentMethod, String desiredArrivalTime, int foodId, int drinkId) {
        Food foodItem = foodService.getFoodById(foodId);
        Drink drinkItem = drinkService.getDrinkById(drinkId);
        Order newOrder = new Order(paymentMethod, desiredArrivalTime, foodItem, drinkItem);
        orderRepository.add(newOrder);
    }

    public void placeOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter food id: ");
        int foodId = scanner.nextInt();

        System.out.print("Enter drink id: ");
        int drinkId = scanner.nextInt();

        scanner.nextLine(); // Consumă newline după nextInt()

        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter desired arrival time: ");
        String desiredArrivalTime = scanner.nextLine();

        addOrder(paymentMethod, desiredArrivalTime, foodId, drinkId);
        System.out.println("Order placed successfully.");
    }


    public void processOrder() {
        Order orderToProcess = orderRepository.getNextOrder();
        if (orderToProcess != null) {
            processedOrder = orderToProcess;
            orderRepository.delete(orderToProcess); // Șterge comanda procesată din repository
            System.out.println("Order processed successfully.");
        } else {
            System.out.println("No orders to process.");
        }
    }


    public void displayPendingOrders() {
        Order pendingOrder = orderRepository.getNextOrder();
        if (pendingOrder != null) {
            System.out.println("Pending Order:");
            displayOrderDetails(pendingOrder);
        } else {
            System.out.println("No pending orders.");
        }
    }


    private void displayOrderDetails(Order order) {
        System.out.println("Order ID: " + order.getId());
        System.out.println("Food item: " + order.getFoodItem());
        System.out.println("Drink item: " + order.getDrinkItem());
        System.out.println("Payment method: " + order.getPaymentMethod());
        System.out.println("Desired arrival time: " + order.getDesiredArrivalTime());
        System.out.println();
    }

    public void displayProcessedOrders() {
        if (processedOrder != null) {
        System.out.println("Processed Order:");
        displayOrderDetails(processedOrder);
    } else {
        System.out.println("No processed orders yet.");
    }

    }
}
