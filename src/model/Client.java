package model;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Client extends User {
    private String address;
    private List<String> allergicFood;

    public Client(String username, String email, String password, String phoneNumber, int age, String address) {
        super(username, email, password, phoneNumber, age);
        this.address = address;
        this.allergicFood = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getAllergicFood() {
        return allergicFood;
    }

    public void setAllergicFood(List<String> allergicFood) {
        this.allergicFood = allergicFood;
    }

    public void addAllergicFood(String food) {
        allergicFood.add(food);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id = " + getId() +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + address + '\'' +
                ", allergicFood=" + allergicFood +
                '}';
    }
}
