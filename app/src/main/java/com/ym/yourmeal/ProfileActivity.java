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

import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;

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

        /*for (int x = 0; x < users.size();x++){
            if(userLogged.equals(users.get(x).getName())){

            }
        }*/
        //TextView user;
       // user = (TextView) findViewById(R.id.txtNomePerfil);


        final Button btnEditar = findViewById(R.id.btnEditarPerfil);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ChangeProfileActivity.class);
                startActivity(i);
            }
        });




    }




}
