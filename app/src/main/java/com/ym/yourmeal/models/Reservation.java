package com.ym.yourmeal.models;

/**
 * Created by Andre Gouveia on 10/04/2018.
 */

public class Reservation {

    public String dish;
    public String email;
    public String name;


    public Reservation(String dish, String email, String name) {
        this.dish = dish;
        this.email = email;
        this.name = name;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDish() {

        return dish;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
