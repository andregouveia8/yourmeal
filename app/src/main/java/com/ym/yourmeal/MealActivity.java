package com.ym.yourmeal;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    private  SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.containerMeal);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsMeal);
        tabLayout.setupWithViewPager(viewPager);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);



        //ArrayList<Menu> menu = com.ym.yourmeal.imp.MealManager.getInstance().getMeals();















        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.reservation_menu:
                                Intent intentReservation = new Intent(getApplicationContext(),NoReservationActivity.class);
                                startActivity(intentReservation);
                                break;
                            case R.id.meal_menu:
                                break;
                            case R.id.profile_menu:
                                Intent intentProfile = new Intent(getApplicationContext(),ProfileActivity.class);
                                startActivity(intentProfile);
                                break;
                        }
                        return true;
                    }
                });






    }

    public void setupViewPager(ViewPager upViewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MeatFragment(), "CARNE");
        adapter.addFragment(new FishFragment(), "PEIXE");
        adapter.addFragment(new VeganFragment(), "VEGAN");

        viewPager.setAdapter(adapter);
    }
}
