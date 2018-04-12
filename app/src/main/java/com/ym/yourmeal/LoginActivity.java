package com.ym.yourmeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.UserManager;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ym.yourmeal.models.Meal;
import com.ym.yourmeal.models.Menu;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText txtemail, txtpassword;
    String email, password;
    static String  userLogado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        txtemail = findViewById(R.id.txtPassword_Atual);
        txtpassword = findViewById(R.id.txtPassword_Nova);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    String userID = user.getUid();

                    ArrayList<User> users = com.ym.yourmeal.imp.UserManager.getInstance().getUsers();

                    for(int x = 0; x<users.size();x++) {
                        if(userID.equals(users.get(x).getId())){
                            if(users.get(x).getType().equals("utilizador")){

                                userLogado = mAuth.getCurrentUser().getEmail();

                                Intent iUser = new Intent(getApplicationContext(), MealActivity.class);
                                startActivity(iUser);

                            }else if(users.get(x).getType().equals("administrador")){
                                Log.d("userID",users.get(x).getType());
                                Intent iFunc = new Intent(getApplicationContext(), EmployeeActivity.class);
                                startActivity(iFunc);
                            }
                        }
                    }
                    Log.d("userID",userID);
                } else {

                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    //Validaçao das credenciais
    private boolean validateForm() {
        boolean valid = true;

        email = txtemail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            txtemail.setError("Required.");
            valid = false;
        } else {
            txtemail.setError(null);
        }

        password = txtpassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            txtpassword.setError("Required.");
            valid = false;
        } else {
            txtpassword.setError(null);
        }

        return valid;
    }







    public void signIn(View view){
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Entrou com sucesso!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Credenciais erradas!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}
