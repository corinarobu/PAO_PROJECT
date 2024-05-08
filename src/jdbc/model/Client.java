package jdbc.model;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Client extends App_User {
    private int client_id;
    private String address;
    private List<Review> reviews;

    public Client(int user_id, String username, String email, String password, String phoneNumber, int age, int client_id, String address) {
        super(user_id,username, email, password, phoneNumber, age);
        this.client_id = client_id;
        this.address = address;
        this.reviews = new ArrayList<>();
    }


    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void setClinet_id(int client_id) {
        this.client_id = client_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id = " + getId() +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + address + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                '}';
    }

    public void setClient_id(int clientSequence) {
        this.client_id = clientSequence;
    }
}
