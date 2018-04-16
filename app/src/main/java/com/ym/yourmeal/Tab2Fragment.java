package com.ym.yourmeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.models.Reservation;

import java.util.ArrayList;

public class Tab2Fragment extends Fragment {

    EditText txtReservasCarne;
    EditText txtReservasPeixe;
    EditText txtReservasVegan;


    TextView diaT;
    String dia = MainActivity.diadasemana;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        ArrayList<Reservation> reserves = ReservationManager.getInstance().getReservations();

        txtReservasCarne = (EditText) view.findViewById(R.id.txtCarne_Func);
        txtReservasPeixe = (EditText) view.findViewById(R.id.txtPeixe_Func);
        txtReservasVegan = (EditText) view.findViewById(R.id.txtVegan_Func);

        diaT = view.findViewById(R.id.dataDiaFunc2);
        diaT.setText(dia);

        int reservasCarne = 0;
        int reservasPeixe = 0;
        int reservasVegan = 0;

        for (int i = 0 ; i< reserves.size();i++){
            String tipo = reserves.get(i).getDish();
            if(tipo.equals("carne")){
                reservasCarne = reservasCarne + 1;
            } else if (tipo.equals("peixe")){
                reservasPeixe = reservasPeixe + 1;
            } else{
                reservasVegan = reservasVegan + 1;
            }
        }
        txtReservasCarne.setText(String.valueOf(reservasCarne) + " Reservas");
        txtReservasPeixe.setText(String.valueOf(reservasPeixe) + " Reservas");
        txtReservasVegan.setText(String.valueOf(reservasVegan) + " Reservas");

        return view;
    }
}
