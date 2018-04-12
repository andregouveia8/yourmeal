package com.ym.yourmeal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ym.yourmeal.MealActivity;

import com.ym.yourmeal.models.Meal;

public class MeatFragment extends Fragment {
    TextView txtCarneNome;
    ImageView imgCarne;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meat_fragment,container,false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String nameMeat= sharedPref.getString("MeatName","");
        String imgCarneBD= sharedPref.getString("MeatPhoto","");

        imgCarne = (ImageView) view.findViewById(R.id.imgCarneMeal);
        //Picasso.with(getContext()).load(imgCarneBD).into(imgCarne);

        txtCarneNome = (TextView) view.findViewById(R.id.txtCarneNome);
        txtCarneNome.setText(nameMeat);
        return view;



    }
}
