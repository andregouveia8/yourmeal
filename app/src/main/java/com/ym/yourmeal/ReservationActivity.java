package com.ym.yourmeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ym.yourmeal.imp.MealManager;
import com.ym.yourmeal.imp.MenuManager;
import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.imp.UserManager;
import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {

    private  TextView mTextMessage;
    String user;
    Meal mealReserva;
    ImageView photo;

    String prato;
    //Referencia à base de dados
    TextView txtReservaNome, txtReservaData;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference resRef = db.getReference("reservations");
    public static ArrayList<Reservation> reserves = ReservationManager.getInstance().getReservations();
    public static ArrayList<String> keys = ReservationManager.getInstance().getKeys();
    public static ArrayList<String> keysUsers = UserManager.getInstance().getKeys();
    public static ArrayList<Meal> meals = MealManager.getInstance().getMeals();
    public static ArrayList<User> users = UserManager.getInstance().getUsers();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.reservation_menu:

                    break;
                case R.id.meal_menu:
                    Intent intentMeal = new Intent(getApplicationContext(),MealActivity.class);
                    startActivity(intentMeal);
                    break;
                case R.id.profile_menu:
                    Intent intentProfile = new Intent(getApplicationContext(),ProfileActivity.class);
                    startActivity(intentProfile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        user = LoginActivity.userLogado;

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_reservas);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        checkReservation();



        for (int i = 0; i< reserves.size(); i++){
            String email = reserves.get(i).getEmail();
            if (email.equals(user)){
                String prato = reserves.get(i).getName();
                for (int c = 0; c< meals.size(); c++){
                    if(prato.equals(meals.get(c).name)){
                        mealReserva = new Meal(meals.get(c).cals, meals.get(c).carbs, meals.get(c).description, meals.get(c).dish, meals.get(c).img, meals.get(c).lip, meals.get(c).name, meals.get(c).prot);
                    }
                }
            }
        }

        txtReservaNome =  findViewById(R.id.txtReservaNome);
        txtReservaData =  findViewById(R.id.dataReserva);
        photo = findViewById(R.id.imgReservation);
        txtReservaNome.setText(mealReserva.getName());
        Glide.with(this).load(mealReserva.getImg()).into(photo);






        final Button btnTrocarReseva = findViewById(R.id.btnTrocarReserva);
        btnTrocarReseva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                for (int i = 0; i< reserves.size(); i++){
                    String email = reserves.get(i).getEmail();
                    if (email.equals(user)){
                        String key = keys.get(i);
                        prato = reserves.get(i).dish;
                        resRef.child(key).removeValue();
                        Log.d("tag", "for reservas "+prato);



                    }

                }

                for (int x = 0; x < users.size(); x++){
                    if(user.equals(users.get(x).getEmail())){
                        String keyUser = keysUsers.get(x);
                        Log.d("tag", "for users "+prato);


                        if(prato.equals("carne")){
                            int beef = Integer.parseInt(users.get(x).getBeef().toString());
                            beef = beef - 1;
                            db.getReference("users").child(keyUser).child("beef").setValue(beef);
                        } else

                        if(prato.equals("peixe")){
                            int fish = Integer.parseInt(users.get(x).getFish().toString());
                            fish= fish- 1;
                            db.getReference("users").child(keyUser).child("fish").setValue(fish);
                        }else{
                            Log.d("tag", "entrei");
                            int vegan = Integer.parseInt(users.get(x).getVegetarian().toString());
                            vegan = vegan - 1;
                            db.getReference("users").child(keyUser).child("vegetarian").setValue(vegan);
                        }


                    }
                }


                Intent i = new Intent(getApplicationContext(), MealActivity.class);
                startActivity(i);

            }
        });








        final Button btnCancelarReserva = findViewById(R.id.btnCancelarReserva);
        btnCancelarReserva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent x = new Intent(getApplicationContext(),CancelReservationPopup.class);
                startActivity(x);
                }


        });


    }
    public static void checkReservation(){
        String user = LoginActivity.userLogado;

        for (int i = 0; i< reserves.size(); i++){
            String email = reserves.get(i).getEmail();
            if (email.equals(user)){
                return;
            }
        }


    }




}
