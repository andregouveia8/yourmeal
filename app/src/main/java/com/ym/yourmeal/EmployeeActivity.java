package com.ym.yourmeal;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.ym.yourmeal.R.id.btnFuncPeixe;

public class EmployeeActivity extends AppCompatActivity {

    private  SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.containerFunc);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);





    }



    private void setupViewPager (ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "REFEIÇÕES");
        adapter.addFragment(new Tab2Fragment(), "RESERVAS");

        viewPager.setAdapter(adapter);
    }



}
