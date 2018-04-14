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
import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.Menu;
import com.ym.yourmeal.models.Reservation;

import java.util.ArrayList;

public class FishFragment extends Fragment {
    TextView txtPeixeNome, dataCarne;
    ImageView imgPeixe;
    Meal fish;
    String dia;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList<Menu> menus;
    DatabaseReference myRef = db.getReference("reservations");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fish_fragment,container,false);

        fish = MealActivity.fishMenu;
        dia = MainActivity.diadasemana;

        imgPeixe = (ImageView) view.findViewById(R.id.imgPeixeMeal);

        Glide.with(this).load(fish.getImg()).into(imgPeixe);

        txtPeixeNome = (TextView) view.findViewById(R.id.txtPeixeNome);
        txtPeixeNome.setText(fish.getName());

        dataCarne = (TextView) view.findViewById(R.id.dataCarne);

        Log.d("ee","dia é "+ dia);
        //dataCarne.setText(dia.toString());



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

                //Adicionar reservas na base de dados

                String key = myRef.push().getKey();

                Reservation reservation= new Reservation ("peixe", user, menus.get(0).fish);
                myRef.child(key).setValue(reservation);

                Intent i = new Intent(getContext().getApplicationContext(),PopupCheckReservation.class);
                startActivity(i);

            }
        });

        return view;
    }
}
