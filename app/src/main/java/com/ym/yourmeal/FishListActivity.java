package com.ym.yourmeal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ym.yourmeal.imp.MealManager;
import com.ym.yourmeal.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class FishListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_yourmeal);

        ListView listView = (ListView) findViewById(R.id.listFish);

        //ENCHE A LISTVIEW
        final List<String> fish = new ArrayList<String>();
        ArrayList<Meal> meals = MealManager.getInstance().getMeals();
        for (int i = 0; i< meals.size(); i++){
            String dish = meals.get(i).getDish();

            if(dish.equals("Peixe")){
                String name = meals.get(i).getName();
                fish.add(name);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fish);
        listView.setAdapter(adapter);

        //RETORNA O VALOR SELECIONADO
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                String name = fish.get(i);
                Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
                intent.putExtra("peixeName", name);
                setResult(RESULT_OK, intent);
                finish();
            }
        });




    }
}
