package com.ym.yourmeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import com.ym.yourmeal.models.Menu;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.ym.yourmeal.imp.MealManager;
import com.ym.yourmeal.imp.MenuManager;
import com.ym.yourmeal.models.Meal;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    private  SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;
    public static  Meal beefMenu;
    public Meal fishMenu;
    public Meal vegetarianMenu;
    String nomeCarne;
    String nomePeixe;
    String nomeVegan;
    String userLogado;





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


        ArrayList<Menu> menu = MenuManager.getInstance().getMenus();
        ArrayList<Meal> meals = MealManager.getInstance().getMeals();

        String beef = menu.get(0).getBeef();
        String fish = menu.get(0).getFish();
        String vegetarian = menu.get(0).getVegetarian();


        //CREATE OBJ OF DAYMENU
        for(int x = 0; x < meals.size();x++) {

            if(beef.equals(meals.get(x).getName())){
                String calorias = meals.get(x).getCals();
                String hidratos = meals.get(x).getCarbs();
                String description = meals.get(x).getDescription();
                String tipo = meals.get(x).getDish();
                String imagem = meals.get(x).getImg();
                String lipidos = meals.get(x).getLip();
                String nome = meals.get(x).getName();
                String proteinas = meals.get(x).getProt();

                beefMenu = new Meal(calorias,hidratos,description,tipo,imagem,lipidos,nome,proteinas);
            }
            if(meals.get(x).getName().equals(fish)){

                String calorias = meals.get(x).getCals();
                String hidratos = meals.get(x).getCarbs();
                String description = meals.get(x).getDescription();
                String tipo = meals.get(x).getDish();
                String imagem = meals.get(x).getImg();
                String lipidos = meals.get(x).getLip();
                String nome = meals.get(x).getName();
                String proteinas = meals.get(x).getProt();

                fishMenu = new Meal(calorias,hidratos,description,tipo,imagem,lipidos,nome,proteinas);
            }

            if(meals.get(x).getName().equals(vegetarian)){

                String calorias = meals.get(x).getCals();
                String hidratos = meals.get(x).getCarbs();
                String description = meals.get(x).getDescription();
                String tipo = meals.get(x).getDish();
                String imagem = meals.get(x).getImg();
                String lipidos = meals.get(x).getLip();
                String nome = meals.get(x).getName();
                String proteinas = meals.get(x).getProt();

                vegetarianMenu = new Meal(calorias,hidratos,description,tipo,imagem,lipidos,nome,proteinas);
            }
        }

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

       /* SharedPreferences sharedPref = getSharedPreferences("yourmeal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Intent i = getIntent();
        userLogado = i.getStringExtra("email");

        Log.d("Meat", "User em MA: " + userLogado);


        editor.putString("MeatName", beefMenu.getName());
        editor.putString("FishName", fishMenu.getName());
        editor.putString("VeganName", vegetarianMenu.getName());
        editor.putString("MeatPhoto", beefMenu.getImg());
        editor.putString("FishPhoto", fishMenu.getImg());
        editor.putString("VeganPhoto", vegetarianMenu.getImg());
        editor.putString("UserLogado",userLogado);
        editor.commit();*/




    }



    public void setupViewPager(ViewPager upViewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MeatFragment(), "CARNE");
        adapter.addFragment(new FishFragment(), "PEIXE");
        adapter.addFragment(new VeganFragment(), "VEGAN");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPref = getSharedPreferences("yourmeal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        userLogado= LoginActivity.userLogado;

        editor.putString("MeatName", beefMenu.getName());
        editor.putString("FishName", fishMenu.getName());
        editor.putString("VeganName", vegetarianMenu.getName());
        editor.putString("MeatPhoto", beefMenu.getImg());
        editor.putString("FishPhoto", fishMenu.getImg());
        editor.putString("VeganPhoto", vegetarianMenu.getImg());
        editor.putString("UserLogado",userLogado);
        editor.commit();


    }
}
