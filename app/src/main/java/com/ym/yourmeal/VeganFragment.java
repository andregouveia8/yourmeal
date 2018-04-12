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

import com.squareup.picasso.Picasso;
import com.ym.yourmeal.models.Meal;

public class VeganFragment extends Fragment {

    TextView txtVeganNome;
    ImageView imgVegan;
    Meal vegan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vegan_fragment,container,false);
        vegan = MealActivity.veganMenu;

        imgVegan = (ImageView) view.findViewById(R.id.imgVeganMeal);
        //Picasso.with(getContext()).load(imgVeganBD).into(imgVegan);

        txtVeganNome = (TextView) view.findViewById(R.id.txtVeganNome);
        txtVeganNome.setText(vegan.getName());



        final Button buttonInfoVegan = view.findViewById(R.id.buttonInfoVegan);
        buttonInfoVegan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity().getApplicationContext(),PopupInfo.class);
                i.putExtra("prato", "vegan");
                startActivity(i);

            }
        });

        return view;
    }
}
