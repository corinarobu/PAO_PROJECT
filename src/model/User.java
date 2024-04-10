package model;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    static protected int idCounter = 0;
    protected final int id;
    protected final String username;
    protected final String email;
    protected String password;
    protected int age;
    protected String phoneNumber;

    public User(String username, String email, String password, String phoneNumber, int age) {
        ++idCounter;
        this.id = idCounter;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public User(User other) {
        this.id = other.id;
        this.username = other.username;
        this.email = other.email;
        this.password = other.password;
        this.phoneNumber = other.phoneNumber;
        this.age = other.age;
    }



    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public abstract String toString();

    public void setAge(int age) {
     this.age = age;
    }

    public void setUserDetails(String password, String phoneNumber, int age) {
        this.password = password;
        this.phoneNumber =phoneNumber;
        this.age = age;
    }
}
