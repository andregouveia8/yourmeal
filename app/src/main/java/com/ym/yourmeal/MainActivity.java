package com.ym.yourmeal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ym.yourmeal.imp.MealManager;
import com.ym.yourmeal.imp.MenuManager;
import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.imp.UserManager;
import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.Menu;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    public static String diadasemana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        img = findViewById(R.id.imgLoading);

        Date currentTime = Calendar.getInstance().getTime();
        String [] parts = currentTime.toString().split(" ");
        String dia = parts[0];


        if(dia.equals("Mon")){
            diadasemana = dia;
        }
        if(dia.equals("Tue")){
            diadasemana = dia;
        }
        if(dia.equals("Wed")){
            diadasemana = dia;
        }
        if(dia.equals("Thu")){
            diadasemana = "ASFSDGFGS";

            Log.d("ee","Entrei no dia " + diadasemana);
        }
        if(dia.equals("Fri")){
            diadasemana = dia;
        }
        if(dia.equals("Sat")){
            diadasemana = dia;
        }
        if(dia.equals("Sun")){
            diadasemana = dia;
        }

        //READ DATABASE
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        //READ USERS DATABASE
        DatabaseReference usersRef = db.getReference("users");

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Percorrer dados da bse de dados Firebase
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    String email = postSnapshot.child("email").getValue().toString();
                    String name = postSnapshot.child("name").getValue().toString();
                    String type = postSnapshot.child("type").getValue().toString();
                    String id = postSnapshot.child("id").getValue().toString();
                    String beef = postSnapshot.child("beef").getValue().toString();
                    String fish = postSnapshot.child("fish").getValue().toString();
                    String vegetarian = postSnapshot.child("vegetarian").getValue().toString();
                    String img = postSnapshot.child("img").getValue().toString();

                    //Criar objeto
                    User user = new User(email, name, type, id, fish, beef, vegetarian, img );

                    //Enviar o objeto para funçao siteMeals para adicionar ao array
                    UserManager.getInstance().setUsers(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //READ MEALS TO DATABASE
        DatabaseReference mealsRef = db.getReference("meals");
        mealsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){

                    String cals = postSnapshot.child("cals").getValue().toString();
                    String carbs = postSnapshot.child("carbs").getValue().toString();
                    String description = postSnapshot.child("description").getValue().toString();
                    String dish = postSnapshot.child("dish").getValue().toString();
                    String img = postSnapshot.child("img").getValue().toString();
                    String lip = postSnapshot.child("lip").getValue().toString();
                    String name = postSnapshot.child("name").getValue().toString();
                    String prot = postSnapshot.child("prot").getValue().toString();

                    //Criar objeto meal
                    Meal meal = new Meal(cals, carbs, description, dish, img, lip, name, prot);

                    //Enviar o objeto para funçao siteMeals para adicionar ao array
                    MealManager.getInstance().setMeals(meal);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //READ MENU TO DATABASE
        DatabaseReference menuRef = db.getReference("menu");
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                    String beef = dataSnapshot.child("beef").getValue().toString();
                    String fish = dataSnapshot.child("fish").getValue().toString();
                    String vegetarian = dataSnapshot.child("vegetarian").getValue().toString();


                    Menu menu = new Menu(beef,fish,vegetarian);
                    MenuManager.getInstance().setMenu(menu);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference reservationsRef = db.getReference("reservations");
        reservationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    String email = postSnapshot.child("email").getValue().toString();
                    String name = postSnapshot.child("name").getValue().toString();
                    String dish = postSnapshot.child("dish").getValue().toString();


                    //Criar objeto meal
                    Reservation reservation = new Reservation(dish, email, name);

                    //Log.d("tag","" + reservation.dish);

                    //Enviar o objeto para funçao siteMeals para adicionar ao array
                    ReservationManager.getInstance().setReservations(reservation);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();


        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);

                    Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}
