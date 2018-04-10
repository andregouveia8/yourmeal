package com.ym.yourmeal.models;

/**
 * Created by Andre Gouveia on 10/04/2018.
 */

public class Menu {

    public Meal beef;
    public Meal fish;
    public Meal vegan;


    public Menu(Meal beef, Meal fish, Meal vegan) {
        this.beef = beef;
        this.fish = fish;
        this.vegan = vegan;
    }

    public void setBeef(Meal beef) {
        this.beef = beef;
    }

    public void setFish(Meal fish) {
        this.fish = fish;
    }

    public void setVegan(Meal vegan) {
        this.vegan = vegan;
    }

    public Meal getBeef() {
        return beef;
    }

    public Meal getFish() {
        return fish;
    }

    public Meal getVegan() {
        return vegan;
    }
}

