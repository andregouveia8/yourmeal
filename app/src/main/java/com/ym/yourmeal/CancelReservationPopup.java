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

                for (int i = 0; i < reserves.size(); i++) {
                    String email = reserves.get(i).getEmail();
                    if (email.equals(user)) {
                        String key = keys.get(i);


                        resRef.child(key).removeValue();
                        prato = reserves.get(i).getDish();
                        Log.d("tag", prato);

                        for(int  x =0; x<users.size();x++){
                            String emailuser = users.get(x).email;
                            String keyUser = keysUsers.get(x);
                            if(emailuser.equals(user)){
                                String carne = "carne";
                                String peixe = "peixe";
                                String vegan = "vegetarian";

                                if(carne.equals(prato)){
                                    int carneUser = Integer.parseInt(users.get(x).getBeef().toString());
                                    carneUser = carneUser - 1;
                                    db.getReference("users").child(keyUser).child("beef").setValue(carneUser);
                                }

                                if(peixe.equals(prato)){
                                    int peixeUser = Integer.parseInt(users.get(x).getBeef().toString());
                                    peixeUser = peixeUser- 1;
                                    db.getReference("users").child(keyUser).child("fish").setValue(peixeUser);
                                }

                                if(vegan.equals(prato)){
                                    int veganUser= Integer.parseInt(users.get(x).getBeef().toString());
                                    veganUser = veganUser - 1;
                                    db.getReference("users").child(keyUser).child("vegetarian").setValue(veganUser);
                                }
                            }
                        }

                        //TODO VER ESTATISTICAS ERRO AO ATUALIZAR??
                    }

                }
                Intent i = new Intent(getApplicationContext(), NoReservationActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
