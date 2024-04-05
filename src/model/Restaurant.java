package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    static protected int idCounter = 0;
    protected final int id;
    private String name;
    private String address;
    private int establishmentYear;
    private String cuisineType;
    private String openingHours;

    public Restaurant(String name, String address, int establishmentYear, String cuisineType, String openingHours) {
        ++idCounter;
        this.id = idCounter;
        this.name = name;
        this.address = address;
        this.establishmentYear = establishmentYear;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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
