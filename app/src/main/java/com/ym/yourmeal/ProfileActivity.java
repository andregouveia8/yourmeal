package com.ym.yourmeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView name, number;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private ImageView photo;
    private FirebaseAuth mAuth;
    ProgressBar progressBarCarne, progressBarPeixe, progressBarVegan;


    public static ArrayList<String> keysUsers = com.ym.yourmeal.imp.UserManager.getInstance().getKeys();
    ArrayList<User> users = com.ym.yourmeal.imp.UserManager.getInstance().getUsers();

    boolean check;
    public static ArrayList<Reservation> reserves = ReservationManager.getInstance().getReservations();
    String userEmail = LoginActivity.userLogado;

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.reservation_menu:
                    check = MealActivity.checkReservations();
                    if (check) {
                        Intent intentReservation = new Intent(getApplicationContext(), ReservationActivity.class);
                        startActivity(intentReservation);
                        finish();
                    } else {
                        Intent intentReservation = new Intent(getApplicationContext(), NoReservationActivity.class);
                        startActivity(intentReservation);
                        finish();
                    }
                    break;
                case R.id.meal_menu:
                    Intent intentMeal = new Intent(getApplicationContext(), MealActivity.class);
                    startActivity(intentMeal);
                    finish();
                    break;
                case R.id.profile_menu:

                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_perfil);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        final Button resetEstatisticas = findViewById(R.id.btnReiniciarEstatisticas);
        resetEstatisticas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                for (int x = 0; x < users.size(); x++) {
                    if (userEmail.equals(users.get(x).getEmail())) {

                        String keyUser = keysUsers.get(x);

                        db.getReference("users").child(keyUser).child("beef").setValue(0);
                        db.getReference("users").child(keyUser).child("fish").setValue(0);
                        db.getReference("users").child(keyUser).child("vegetarian").setValue(0);

                        finish();
                        Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(i);
                    }
                }
            }
        });


        name = (TextView) findViewById(R.id.txtNomePerfil);
        number = (TextView) findViewById(R.id.txtUsername);
        photo = (ImageView) findViewById(R.id.imgPerfil);


        progressBarCarne = findViewById(R.id.progressBarCarne);
        progressBarPeixe = findViewById(R.id.progressBarPeixe);
        progressBarVegan = findViewById(R.id.progressBarVegan);

        //ENCHER OS PROGRESSBAR NO PERFIL
        for (int x = 0; x < users.size(); x++) {
            if (userEmail.equals(users.get(x).getEmail())) {

                int valorCarne = Integer.parseInt(users.get(x).getBeef());
                int valorFish = Integer.parseInt(users.get(x).getFish());
                int valorVegan = Integer.parseInt(users.get(x).getVegetarian());

                int beef = 0;
                int fish = 0;
                int vegan = 0;

                int soma = valorCarne + valorFish + valorVegan;


                if (valorCarne <= 0 && valorFish <= 0 && valorVegan <= 0) {
                    beef = 0;
                    fish = 0;
                    vegan = 0;
                } else if (valorCarne <= 0) {
                    beef = 0;
                    fish = valorFish * 100 / soma;
                    vegan = valorVegan * 100 / soma;
                } else if (valorFish <= 0) {
                    beef = valorCarne * 100 / soma;
                    fish = 0;
                    vegan = valorVegan * 100 / soma;

                } else if (valorVegan <= 0) {
                    beef = valorCarne * 100 / soma;
                    fish = valorFish * 100 / soma;
                    vegan = 0;
                } else {
                    beef = valorCarne * 100 / soma;
                    fish = valorFish * 100 / soma;
                    vegan = valorVegan * 100 / soma;

                }


                progressBarCarne.setProgress(beef);
                progressBarPeixe.setProgress(fish);
                progressBarVegan.setProgress(vegan);

                String email = users.get(x).getEmail();
                String[] parts = email.split(Pattern.quote("@"));
                name.setText(users.get(x).getName());
                number.setText(parts[0]);

                Glide.with(this).load(users.get(x).getImg()).into(photo);


            }
        }


        //LOGOUT
        mAuth = FirebaseAuth.getInstance();
        Button btnSair = (Button) findViewById(R.id.btnSairPerfil);
        btnSair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.signOut();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }

}
