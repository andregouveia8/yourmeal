package com.ym.yourmeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.ym.yourmeal.MealActivity;

import com.ym.yourmeal.imp.MenuManager;
import com.ym.yourmeal.imp.UserManager;
import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.Menu;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;


public class MeatFragment extends Fragment {
    TextView txtCarneNome;
    ImageView imgCarne;
    String user;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList<Menu> menus;
    DatabaseReference myRef = db.getReference("reservations");
    ArrayList<User> users = UserManager.getInstance().getUsers();

    Meal beef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meat_fragment,container,false);
        beef = MealActivity.beefMenu;
        user = LoginActivity.userLogado;

        imgCarne  = (ImageView) view.findViewById(R.id.imgCarneMeal);
        txtCarneNome = (TextView) view.findViewById(R.id.txtCarneNome);


        txtCarneNome.setText(beef.getName());
        Glide.with(this).load(beef.getImg()).into(imgCarne);


        final Button btnInfoCarne = view.findViewById(R.id.buttonInfoCarne);
        btnInfoCarne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity().getApplicationContext(),PopupInfo.class);
                i.putExtra("prato", "carne");
                startActivity(i);

            }
        });


        final Button btnReservarCarne = view.findViewById(R.id.buttonReservarCarne);
        btnReservarCarne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String user = LoginActivity.userLogado;
                menus = MenuManager.getInstance().getMenus();

                //Adicionar reservas na base de dados

                String key = myRef.push().getKey();

                Log.d("e", menus.get(0).beef);

                Reservation reservation= new Reservation ("carne", user, menus.get(0).beef);
                myRef.child(key).setValue(reservation);


                for (int x = 0; x < users.size(); x++){
                    if(user.equals(users.get(x).getEmail())){
                        int beef = Integer.parseInt(users.get(x).getBeef().toString());

                        beef = beef + 1;

                        //TODO ACABAR O ATUALIZAR ESTATISTICAS CARNE
                        db.getReference("users").child("1").child("beef").setValue(beef);



                    }
                }




                Intent i = new Intent(getContext().getApplicationContext(),PopupCheckReservation.class);
                startActivity(i);

            }
        });




        return view;
    }





}
