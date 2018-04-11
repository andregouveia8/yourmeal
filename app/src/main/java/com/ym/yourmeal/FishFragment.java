package com.ym.yourmeal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FishFragment extends Fragment {
    TextView txtPeixeNome;
    ImageView imgPeixe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fish_fragment,container,false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String nameFish= sharedPref.getString("FishName","");
        String imgPeixeBD= sharedPref.getString("FishPhoto","");

        imgPeixe = (ImageView) view.findViewById(R.id.imgPeixeMeal);
        Picasso.with(getContext()).load(imgPeixeBD).into(imgPeixe);

        txtPeixeNome = (TextView) view.findViewById(R.id.txtPeixeNome);
        txtPeixeNome.setText(nameFish);

        return view;
    }
}
