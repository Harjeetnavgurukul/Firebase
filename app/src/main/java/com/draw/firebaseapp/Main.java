package com.draw.firebaseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity {

    private EditText editEmail, editText;
    private Button btnSignUp;
    private TextView textViewSignIn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editEmail=findViewById(R.id.editTextEmail);
        editText=findViewById(R.id.editTextPassword);
        btnSignUp=findViewById(R.id.buttonSignup);
        textViewSignIn=findViewById(R.id.textViewSignin);
        progressBar=findViewById(R.id.progress1);
        mAuth=FirebaseAuth.getInstance();


        //set click listener for email and password...!
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = editEmail.getText().toString().trim();
                final String Password = editText.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(Main.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(Main.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length()<6){
                    Toast.makeText(Main.this, "password minimum 6 char required", Toast.LENGTH_SHORT).show();
                }

                //progressbar set visibility....!
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(Main.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Main.this, "signUp complete"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()){
                                    Toast.makeText(Main.this, "Auth fail", Toast.LENGTH_SHORT).show();
                                }else {
                                    startActivity(new Intent(Main.this,profile.class));
                                    finish();
                                }
                            }
                        });

            }
        });

        //set click listener go to login page activity...!
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, Activity.class));
                finish();

            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}