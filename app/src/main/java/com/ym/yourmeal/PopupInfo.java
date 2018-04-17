package com.ym.yourmeal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ym.yourmeal.models.Meal;

public class PopupInfo extends AppCompatActivity {

    Meal beef;
    Meal fish;
    Meal vegan;

    TextView cal,pro,lip,desc,hid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8),(int)(heigth*0.6));

        Intent i = getIntent();
        String tipoPrato = i.getStringExtra("prato");

        beef = MealActivity.beefMenu;
        fish = MealActivity.fishMenu;
        vegan = MealActivity.veganMenu;

        cal = findViewById(R.id.txtCalInfo);
        pro = findViewById(R.id.txtProInfo);
        lip = findViewById(R.id.txtLipInfo);
        desc = findViewById(R.id.txtDescInfo);
        hid = findViewById(R.id.txtHidInfo);

        //ALTERAR A POP UP CONSOANTE O TIPO DE CARNE
        if(tipoPrato.equals("carne")){
            desc.setText(beef.getDescription());

            cal.setText(beef.getCals());
            pro.setText(beef.getProt());
            lip.setText(beef.getLip());
            hid.setText(beef.getCarbs());
        }
        if(tipoPrato.equals("peixe")){
            desc.setText(fish.getDescription());

            cal.setText(fish.getCals());
            pro.setText(fish.getProt());
            lip.setText(fish.getLip());
            hid.setText(fish.getCarbs());

        }
        if(tipoPrato.equals("vegan")){
            desc.setText(vegan.getDescription());

            cal.setText(vegan.getCals());
            pro.setText(vegan.getProt());
            lip.setText(vegan.getLip());
            hid.setText(vegan.getCarbs());

        }


        final Button btnCloseInfo = findViewById(R.id.btnCloseInfo);
        btnCloseInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });













    }
}
