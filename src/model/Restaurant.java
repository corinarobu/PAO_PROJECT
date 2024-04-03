package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private int establishmentYear;
    private String cuisineType;
    private String openingHours;

    public Restaurant(String name, String address, int establishmentYear, String cuisineType, String openingHours) {
        this.name = name;
        this.address = address;
        this.establishmentYear = establishmentYear;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getEstablishmentYear() {
        return establishmentYear;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getOpeningHours() {
        return openingHours;
    }

}
