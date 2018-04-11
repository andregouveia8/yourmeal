package com.ym.yourmeal.models;


public class Menu {

    public String beef;
    public String fish;
    public String vegetarian;

    public Menu(String beef, String fish, String vegetarian) {
        this.beef = beef;
        this.fish = fish;
        this.vegetarian = vegetarian;
    }


    public void setBeef(String beef) {
        this.beef = beef;
    }

    public void setFish(String fish) {
        this.fish = fish;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getBeef() {

        return beef;
    }

    public String getFish() {
        return fish;
    }

    public String getVegetarian() {
        return vegetarian;
    }
}

