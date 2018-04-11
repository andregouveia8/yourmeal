package com.ym.yourmeal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class VeganFragment extends Fragment {

    TextView txtVeganNome;
    ImageView imgVegan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vegan_fragment,container,false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String nameVegan= sharedPref.getString("VeganName","");
        String imgVeganBD= sharedPref.getString("VeganPhoto","");

        imgVegan = (ImageView) view.findViewById(R.id.imgVeganMeal);
        Picasso.with(getContext()).load(imgVeganBD).into(imgVegan);

        txtVeganNome = (TextView) view.findViewById(R.id.txtVeganNome);
        txtVeganNome.setText(nameVegan);

        return view;
    }
}
