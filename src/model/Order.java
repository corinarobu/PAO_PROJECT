package model;

import java.util.List;
public class Order {
    static private int idCounter = 0;
    private int id;
    private List<String> foodList;
    private List<String> drinkList;
    private String paymentMethod;
    private String desiredArrivalTime;

    public Order(List<String> foodList, List<String> drinkList, String paymentMethod, String desiredArrivalTime) {
        ++idCounter;
        this.id = idCounter;
        this.foodList = foodList;
        this.drinkList = drinkList;
        this.paymentMethod = paymentMethod;
        this.desiredArrivalTime = desiredArrivalTime;
    }

    public int getId() {
        return id;
    }
    public List<String> getFoodList() {
        return foodList;
    }

    public List<String> getDrinkList() {
        return drinkList;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDesiredArrivalTime() {
        return desiredArrivalTime;
    }

}
