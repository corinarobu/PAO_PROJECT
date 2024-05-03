package jdbc.model;

public class Order {
    static private int idCounter = 0;
    private int id;
    private String paymentMethod;
    private String desiredArrivalTime;
    private Food foodItem;
    private Drink drinkItem;

    public Order(String paymentMethod, String desiredArrivalTime, Food foodItem, Drink drinkItem) {
        ++idCounter;
        this.id = idCounter;
        this.paymentMethod = paymentMethod;
        this.desiredArrivalTime = desiredArrivalTime;
        this.foodItem = foodItem;
        this.drinkItem = drinkItem;
    }

    public int getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDesiredArrivalTime() {
        return desiredArrivalTime;
    }

    public Food getFoodItem() {
        return foodItem;
    }

    public Drink getDrinkItem() {
        return drinkItem;
    }
}
