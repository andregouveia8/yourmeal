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
import com.ym.yourmeal.models.Meal;

public class FishFragment extends Fragment {
    TextView txtPeixeNome, dataCarne;
    ImageView imgPeixe;
    Meal fish;
    String dia;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fish_fragment,container,false);

        fish = MealActivity.fishMenu;
        dia = MainActivity.diadasemana;

        imgPeixe = (ImageView) view.findViewById(R.id.imgPeixeMeal);
        //Picasso.with(getContext()).load(imgPeixeBD).into(imgPeixe);

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

        return view;
    }
}
