package com.ym.yourmeal;

import android.content.Intent;
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

public class VeganListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_list);



        ListView listView = (ListView) findViewById(R.id.listVegan);

        final List<String> vegan = new ArrayList<String>();
        ArrayList<Meal> meals = MealManager.getInstance().getMeals();
        for (int i = 0; i< meals.size(); i++){
            String dish = meals.get(i).getDish();
            Log.d("listviewtag", "Dish: " + dish);
            if(dish.equals("Vegan")){
                String name = meals.get(i).getName();
                vegan.add(name);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vegan);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                String name = vegan.get(i);
                Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
                intent.putExtra("veganName", name);
                setResult(RESULT_OK, intent);
                finish();
            }
        });



    }
}
