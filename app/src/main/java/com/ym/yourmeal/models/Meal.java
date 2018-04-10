package com.ym.yourmeal.models;

public class Meal {

    public String cals;
    public String carbs;
    public String description;
    public String dish;
    public String img;
    public String lip;
    public String name;
    public String prot;

    public Meal(String cals, String carbs, String description, String dish, String img, String lip, String name, String prot) {
        this.cals = cals;
        this.carbs = carbs;
        this.description = description;
        this.dish = dish;
        this.img = img;
        this.lip = lip;
        this.name = name;
        this.prot = prot;
    }

    public void setCals(String cals) {
        this.cals = cals;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLip(String lip) {
        this.lip = lip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProt(String prot) {
        this.prot = prot;
    }

    public String getCals() {
        return cals;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getDescription() {
        return description;
    }

    public String getDish() {
        return dish;
    }

    public String getImg() {
        return img;
    }

    public String getLip() {
        return lip;
    }

    public String getName() {
        return name;
    }

    public String getProt() {
        return prot;
    }
}
