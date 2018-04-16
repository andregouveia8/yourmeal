package com.ym.yourmeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class Tab1Fragment extends Fragment {



    EditText txtCarne, txtPeixe, txtVegan;
    TextView diaT;
    String nameCarne, namePeixe, nameVegan;
    String dia = MainActivity.diadasemana;
    final int REQUEST_CODE = 1;

    String[] menu = new String[]{"beef","fish","vegan"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);


        txtCarne = view.findViewById(R.id.txtCarneFunc);
        txtPeixe = view.findViewById(R.id.txtPeixe_Func);
        txtVegan = view.findViewById(R.id.txtVegan_Func);


        txtCarne.setText(menu[0]);

        txtPeixe.setText(menu[1]);

        txtVegan.setText(menu[2]);


        diaT = view.findViewById(R.id.dataDiaFunc);
        diaT.setText(dia);


        final Button btnCarne = view.findViewById(R.id.btnFuncCarne);
        btnCarne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getContext().getApplicationContext(), MeatListActivity.class);
                startActivity(i);
            }
        });

        final Button btnPeixe = view.findViewById(R.id.btnFuncPeixe);
        btnPeixe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getContext().getApplicationContext(), FishListActivity.class);
                startActivity(i);
            }
        });

        final Button btnVegan = view.findViewById(R.id.btnFuncVegan);
        btnVegan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getContext().getApplicationContext(), MeatListActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
