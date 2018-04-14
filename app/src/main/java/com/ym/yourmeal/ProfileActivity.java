package com.ym.yourmeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView name, number;
    private ImageView photo;
    private FirebaseAuth mAuth;
    ProgressBar progressBarCarne, progressBarPeixe, progressBarVegan;

    boolean check;
    public static ArrayList<Reservation> reserves = ReservationManager.getInstance().getReservations();
    String userEmail= LoginActivity.userLogado;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.reservation_menu:
                    for (int i = 0; i< reserves.size(); i++){
                        String email = reserves.get(i).getEmail();
                        if (email.equals(userEmail)){
                            check = true;
                        }else{
                            check = false;
                        }
                    }
                    if(check){
                        Intent intentReservation = new Intent(getApplicationContext(),ReservationActivity.class);
                        startActivity(intentReservation);
                        finish();
                    }else{
                        Intent intentReservation = new Intent(getApplicationContext(),NoReservationActivity.class);
                        startActivity(intentReservation);
                        finish();
                    }
                    break;
                case R.id.meal_menu:
                    Intent intentMeal = new Intent(getApplicationContext(),MealActivity.class);
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

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




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
        final Button btnEditar = findViewById(R.id.btnEditarPerfil);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ChangeProfileActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<User> users = com.ym.yourmeal.imp.UserManager.getInstance().getUsers();




        name = (TextView) findViewById(R.id.txtNomePerfil);
        number = (TextView) findViewById(R.id.txtUsername);
        photo = (ImageView) findViewById(R.id.imgPerfil);


        progressBarCarne = findViewById(R.id.progressBarCarne);
        progressBarPeixe = findViewById(R.id.progressBarPeixe);
        progressBarVegan = findViewById(R.id.progressBarVegan);

        for(int x = 0; x < users.size(); x++){
            if(userEmail.equals(users.get(x).getEmail())){

                int valorCarne = Integer.parseInt(users.get(x).getBeef());
                int valorFish = Integer.parseInt(users.get(x).getFish());
                int valorVegan = Integer.parseInt(users.get(x).getVegetarian());

                int soma = valorCarne + valorFish + valorVegan;

                int beef = valorCarne * 100 / soma;
                int fish = valorFish * 100 / soma;
                int vegan = valorVegan * 100 / soma;

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

    }
}
