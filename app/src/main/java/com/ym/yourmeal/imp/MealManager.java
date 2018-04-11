package com.ym.yourmeal.imp;

import com.ym.yourmeal.inter.iMeal;
import com.ym.yourmeal.models.Meal;
import java.util.ArrayList;

public class MealManager implements iMeal {

    public ArrayList<Meal> meals = new ArrayList <Meal>();


    static MealManager mm = null;

    public static MealManager getInstance() {
        if (mm == null) {
            mm = new MealManager();
        }
        return mm;
    }

    @Override
    public void setMeals(Meal meal){
        meals.add(meal);
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }


}
