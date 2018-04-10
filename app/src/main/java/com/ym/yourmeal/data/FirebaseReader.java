package com.ym.yourmeal.data;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ym.yourmeal.imp.UserManager;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;


public class FirebaseReader extends AppCompatActivity{

    protected void onResume(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = db.getReference("users");

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Percorrer dados da bse de dados Firebase
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    String email = postSnapshot.child("email").getValue().toString();
                    String name = postSnapshot.child("name").getValue().toString();
                    String type = postSnapshot.child("type").getValue().toString();
                    String id = postSnapshot.child("id").getValue().toString();
                    String beef = postSnapshot.child("beef").getValue().toString();
                    String fish = postSnapshot.child("fish").getValue().toString();
                    String vegetarian = postSnapshot.child("vegetarian").getValue().toString();
                    String img = postSnapshot.child("img").getValue().toString();

                    //Criar objeto
                    User user = new User(email, name, type, id, fish, beef, vegetarian, img );

                    //Enviar o objeto para funçao siteMeals para adicionar ao array
                    UserManager.getInstance().setUsers(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
