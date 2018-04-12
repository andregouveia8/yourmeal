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

import com.squareup.picasso.Picasso;
import com.ym.yourmeal.MealActivity;

import com.ym.yourmeal.models.Meal;

public class MeatFragment extends Fragment {
    TextView txtCarneNome;
    ImageView imgCarne;

    Meal beef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meat_fragment,container,false);
        beef = MealActivity.beefMenu;
        txtCarneNome = (TextView) view.findViewById(R.id.txtCarneNome);
        txtCarneNome.setText(beef.getName());


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

            }
        });




        return view;
    }





}
