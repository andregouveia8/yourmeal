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

public class FishFragment extends Fragment {
    TextView txtPeixeNome, txtDataPeixe;
    ImageView imgPeixe;
    String day = MainActivity.diadasemana;
    Meal fish;
    String dia;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList<Menu> menus;
    DatabaseReference myRef = db.getReference("reservations");
    Button btnFish;

    String userLogado;
    boolean check;
    public static ArrayList<Reservation> reserves = ReservationManager.getInstance().getReservations();
    public static ArrayList<String> keysUsers = UserManager.getInstance().getKeys();
    ArrayList<User> users = UserManager.getInstance().getUsers();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fish_fragment,container,false);

        fish = MealActivity.fishMenu;
        dia = MainActivity.diadasemana;

        check = MealActivity.checkReservations();

        imgPeixe = (ImageView) view.findViewById(R.id.imgPeixeMeal);

        Glide.with(this).load(fish.getImg()).into(imgPeixe);

        txtPeixeNome = (TextView) view.findViewById(R.id.txtPeixeNome);
        txtPeixeNome.setText(fish.getName());

        txtDataPeixe = (TextView) view.findViewById(R.id.dataPeixe);

        txtDataPeixe.setText(day);

        btnFish = (Button)view.findViewById(R.id.buttonReservarPeixe);

        //BTN ENABLE
        if (check){
            btnFish.setVisibility(View.GONE);
        }else if(!check){
            btnFish.setVisibility(View.VISIBLE);
        }



        final Button btnInfoPeixe = view.findViewById(R.id.buttonInfoPeixe);
        btnInfoPeixe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity().getApplicationContext(),PopupInfo.class);
                i.putExtra("prato", "peixe");
                startActivity(i);

            }
        });

        final Button btnReservarPeixe = view.findViewById(R.id.buttonReservarPeixe);
        btnReservarPeixe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String user = LoginActivity.userLogado;
                menus = MenuManager.getInstance().getMenus();

                //ADICIONAR RESERVA
                String key = myRef.push().getKey();

                Reservation reservation= new Reservation ("peixe", user, menus.get(0).fish);
                myRef.child(key).setValue(reservation);

                //ATUALIZA ESTATISTICAS USER
                for (int x = 0; x < users.size(); x++){
                    if(user.equals(users.get(x).getEmail())){
                        int fish = Integer.parseInt(users.get(x).getFish().toString());

                        fish = fish + 1;

                        String keyUser = keysUsers.get(x);

                        db.getReference("users").child(keyUser).child("fish").setValue(fish);

                    }
                }


                Intent i = new Intent(getContext().getApplicationContext(),PopupCheckReservation.class);
                startActivity(i);

            }
        });

        return view;
    }
}
