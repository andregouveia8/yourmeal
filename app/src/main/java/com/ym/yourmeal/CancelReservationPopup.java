package com.ym.yourmeal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ym.yourmeal.imp.MealManager;
import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.imp.UserManager;
import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class CancelReservationPopup extends AppCompatActivity {


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference resRef = db.getReference("reservations");
    public static ArrayList<Reservation> reserves = ReservationManager.getInstance().getReservations();
    public static ArrayList<String> keys = ReservationManager.getInstance().getKeys();
    public static ArrayList<Meal> meals = MealManager.getInstance().getMeals();
    public static ArrayList<String> keysUsers = UserManager.getInstance().getKeys();
    ArrayList<User> users = UserManager.getInstance().getUsers();
    String prato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation_popup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (heigth * 0.6));

        final Button btnCloseCancelPopup = findViewById(R.id.btnCloseCancelPopup);
        btnCloseCancelPopup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final Button btnNaoCancelPopup = findViewById(R.id.btnNaoCancel);
        btnNaoCancelPopup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final Button btnSimCancelPopup = findViewById(R.id.btnSimCancel);
        btnSimCancelPopup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user = LoginActivity.userLogado;

//REMOVE RESERVA DO USER
                for (int i = 0; i< reserves.size(); i++){
                    String email = reserves.get(i).getEmail();
                    if (email.equals(user)){
                        String key = keys.get(i);
                        prato = reserves.get(i).dish;
                        reserves.remove(i);
                        keys.remove(i);
                        resRef.child(key).removeValue();
                        Log.d("tag", "for reservas "+prato);

                    }

                }

                for (int x = 0; x < users.size(); x++){
                    if(user.equals(users.get(x).getEmail())){
                        String keyUser = keysUsers.get(x);
                        Log.d("tag", "for users "+prato);

                        //ATUALIZA ESTATISITICAS USER
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
                Intent i = new Intent(getApplicationContext(), NoReservationActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
