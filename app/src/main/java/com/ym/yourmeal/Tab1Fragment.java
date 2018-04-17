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

import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;

public class Tab1Fragment extends Fragment {



    EditText txtCarne, txtPeixe, txtVegan;
    TextView diaT;
    String nameCarne, namePeixe, nameVegan;
    String dia = MainActivity.diadasemana;
    final int REQUEST_CODE = 1;
    String carne, peixe, vegan;

    FirebaseDatabase db = FirebaseDatabase.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);


        txtCarne = view.findViewById(R.id.txtCarneFunc);
        txtPeixe = view.findViewById(R.id.txtPeixe_Func);
        txtVegan = view.findViewById(R.id.txtVegan_Func);




        diaT = view.findViewById(R.id.dataDiaFunc);
        diaT.setText(dia);


        final Button btnCarne = view.findViewById(R.id.btnFuncCarne);
        btnCarne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getContext().getApplicationContext(), MeatListActivity.class);
                startActivityForResult(i,REQUEST_CODE);
            }
        });

        final Button btnPeixe = view.findViewById(R.id.btnFuncPeixe);
        btnPeixe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getContext().getApplicationContext(), FishListActivity.class);
                startActivityForResult(i,REQUEST_CODE);
            }
        });

        final Button btnVegan = view.findViewById(R.id.btnFuncVegan);
        btnVegan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getContext().getApplicationContext(), VeganListActivity.class);
                startActivityForResult(i,REQUEST_CODE);
            }
        });

//ALTERAR O MENU

        final Button btnAdicionar = view.findViewById(R.id.btnFuncAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String carneBD = txtCarne.getText().toString();
                String peixeBD = txtPeixe.getText().toString();
                String veganBD = txtVegan.getText().toString();

                db.getReference("menu").child("beef").setValue(carneBD);
                db.getReference("menu").child("fish").setValue(peixeBD);
                db.getReference("menu").child("vegetarian").setValue(veganBD);

                db.getReference("reservations").removeValue();


            }
        });








        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.hasExtra("carneName")) {
                carne = data.getExtras().getString("carneName");
                txtCarne.setText(carne);
            } else
            if (data.hasExtra("peixeName")) {
                peixe = data.getExtras().getString("peixeName");
                txtPeixe.setText(peixe);
            } else
            if (data.hasExtra("veganName")) {
                vegan = data.getExtras().getString("veganName");
                txtVegan.setText(vegan);
            }
        }

    }
}
