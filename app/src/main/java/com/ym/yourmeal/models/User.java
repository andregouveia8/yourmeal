package com.ym.yourmeal.models;

public class User {

    public String email;
    public String name;
    public String type;
    public String id;
    public String fish;
    public String beef;
    public String vegetarian;
    public String img;

    public User(String email, String name, String type, String id, String fish, String beef, String vegetarian, String img) {
        this.email = email;
        this.name = name;
        this.type = type;
        this.id = id;
        this.fish = fish;
        this.beef = beef;
        this.vegetarian = vegetarian;
        this.img = img;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFish(String fish) {
        this.fish = fish;
    }

    public void setBeef(String beef) {
        this.beef = beef;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getFish() {
        return fish;
    }

    public String getBeef() {
        return beef;
    }

    public String getVegetarian() {
        return vegetarian;
    }

    public String getImg() {
        return img;
    }
}
