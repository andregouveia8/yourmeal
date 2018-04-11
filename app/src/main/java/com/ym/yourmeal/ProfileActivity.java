package com.ym.yourmeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private FirebaseAuth mAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.reservation_menu:
                    Intent intentReservation = new Intent(getApplicationContext(),ReservationActivity.class);
                    startActivity(intentReservation);
                    break;
                case R.id.meal_menu:
                    Intent intentMeal = new Intent(getApplicationContext(),MealActivity.class);
                    startActivity(intentMeal);
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

        ArrayList<User> users = new ArrayList<User>();
mAuth = FirebaseAuth.getInstance();
       Button btnSair = (Button) findViewById(R.id.btnSairPerfil);
        btnSair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Fazer o logout
                mAuth.signOut();
                // Chamar primeira atividade (login)
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




}
