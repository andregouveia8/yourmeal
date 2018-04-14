package com.ym.yourmeal;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class PopupCheckReservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_check_reservation);

        ActionBar actionBar = getSupportActionBar();

        actionBar.hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8),(int)(heigth*0.6));


        final Button btnCloseCheckPopUp = findViewById(R.id.btnCloseCheckPopUp);
        btnCloseCheckPopUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


        final Button btnSimPopUpCheck = findViewById(R.id.btnSimPopUpCheck);
        btnSimPopUpCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });




    }
}
