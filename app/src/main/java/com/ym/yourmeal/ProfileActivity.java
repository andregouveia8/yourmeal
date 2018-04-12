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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView name, number;
private ImageView photo;
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



        //LOGOUT
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

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<User> users = com.ym.yourmeal.imp.UserManager.getInstance().getUsers();
        android.util.Log.d("user",String.valueOf(users.size()) );

        SharedPreferences sharedPref = getSharedPreferences("yourmeal", Context.MODE_PRIVATE);
        String userEmail= sharedPref.getString("UserLogado","");

        for(int x = 0; x < users.size(); x++){
            if(userEmail.equals(users.get(x).getEmail())){
                name = (TextView) findViewById(R.id.txtNomePerfil);
                number = (TextView) findViewById(R.id.txtUsername);
                photo = (ImageView) findViewById(R.id.imgPerfil);

                String email = users.get(x).getEmail();

                String[] parts = email.split(Pattern.quote("@"));

                name.setText(users.get(x).getName());
                number.setText(parts[0]);
                //Picasso.get().load(users.get(x).getImg()).into(photo);

            }
        }

    }
}
