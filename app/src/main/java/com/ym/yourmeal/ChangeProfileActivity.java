package com.ym.yourmeal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ym.yourmeal.imp.ReservationManager;
import com.ym.yourmeal.imp.UserManager;
import com.ym.yourmeal.models.Reservation;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ChangeProfileActivity extends AppCompatActivity {
    ImageView imgChangeProfile;
    TextView name, number;
    EditText pass, newpass, confirmpass;

    String passUserBD;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String newPassword = "SOME-SECURE-PASSWORD";

    ArrayList<User> users = UserManager.getInstance().getUsers();
    String userEmail = LoginActivity.userLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);


        imgChangeProfile = (ImageView) findViewById(R.id.imgChangeProfile);
        name = (TextView) findViewById(R.id.txtNomeChangeProfile);
        number = (TextView) findViewById(R.id.txtNumChangeProfile);

        for (int x = 0; x < users.size(); x++) {
            if (userEmail.equals(users.get(x).getEmail())) {

                String email = users.get(x).getEmail();
                String[] parts = email.split(Pattern.quote("@"));
                name.setText(users.get(x).getName());
                number.setText(parts[0]);


                Glide.with(this).load(users.get(x).getImg()).into(imgChangeProfile);

            }
        }


        final Button btnAlterar = findViewById(R.id.btnAlterarPassword);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                pass = findViewById(R.id.txtPassword_Atual);
                newpass = findViewById(R.id.txtPassword_Nova);
                confirmpass = findViewById(R.id.txtPassword_Confirm);


                /*user.updatePassword(newpass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("test", "User password updated.");
                                }else{
                                    Log.d("test", "User password not updated.");
                                }
                            }
                        });*/


            }
        });


    }
}
