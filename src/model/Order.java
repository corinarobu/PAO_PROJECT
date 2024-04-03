package model;

public class Order {
    private List<String> foodList;
    private List<String> drinkList;
    private String paymentMethod;
    private String desiredArrivalTime;

    public Order(List<String> foodList, List<String> drinkList, String paymentMethod, String desiredArrivalTime) {
        this.foodList = foodList;
        this.drinkList = drinkList;
        this.paymentMethod = paymentMethod;
        this.desiredArrivalTime = desiredArrivalTime;
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
