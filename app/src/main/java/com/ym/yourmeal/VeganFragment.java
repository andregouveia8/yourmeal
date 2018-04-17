package com.ym.yourmeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ym.yourmeal.imp.MenuManager;
import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.imp.UserManager;
import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.Menu;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class VeganFragment extends Fragment {

    TextView txtVeganNome, txtDataVegan;
    ImageView imgVegan;
    Meal vegan;
    String day = MainActivity.diadasemana;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList<Menu> menus;
    DatabaseReference myRef = db.getReference("reservations");
    String userLogado;
    boolean check;
    Button btnVegan;
    public static ArrayList<Reservation> reserves = ReservationManager.getInstance().getReservations();


    public static ArrayList<String> keysUsers = UserManager.getInstance().getKeys();
    ArrayList<User> users = UserManager.getInstance().getUsers();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vegan_fragment,container,false);
        vegan = MealActivity.veganMenu;

        imgVegan = (ImageView) view.findViewById(R.id.imgVeganMeal);
        txtVeganNome = (TextView) view.findViewById(R.id.txtVeganNome);

        txtVeganNome.setText(vegan.getName());
        Glide.with(this).load(vegan.getImg()).into(imgVegan);

        check = MealActivity.checkReservations();

        btnVegan = (Button)view.findViewById(R.id.buttonReservarVegan);

        if (check){
            btnVegan.setVisibility(View.GONE);
        }
        else if(!check){
            btnVegan.setVisibility(View.VISIBLE);
        }

        txtDataVegan = (TextView) view.findViewById(R.id.dataVegan);

        txtDataVegan.setText(day);

        final Button buttonInfoVegan = view.findViewById(R.id.buttonInfoVegan);
        buttonInfoVegan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity().getApplicationContext(),PopupInfo.class);
                i.putExtra("prato", "vegan");
                startActivity(i);

            }
        });

        final Button btnReservarVegan = view.findViewById(R.id.buttonReservarVegan);
        btnReservarVegan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String user = LoginActivity.userLogado;
                menus = MenuManager.getInstance().getMenus();

                String key = myRef.push().getKey();

                Reservation reservation= new Reservation ("vegatarian", user, menus.get(0).vegetarian);
                myRef.child(key).setValue(reservation);

                for (int x = 0; x < users.size(); x++){
                    if(user.equals(users.get(x).getEmail())){
                        int vegan = Integer.parseInt(users.get(x).getVegetarian().toString());

                        vegan = vegan + 1;

                        String keyUser = keysUsers.get(x);

                        db.getReference("users").child(keyUser).child("vegetarian").setValue(vegan);

                    }
                }

                Intent i = new Intent(getContext().getApplicationContext(),PopupCheckReservation.class);
                startActivity(i);

            }
        });

        return view;
    }
}
