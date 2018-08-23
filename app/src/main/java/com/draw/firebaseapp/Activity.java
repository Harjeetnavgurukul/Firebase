package com.draw.firebaseapp;

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

public class Activity extends AppCompatActivity {

    private Button btnLogin ,btnReset;
    private TextView txtSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth Auth;
    EditText editEmail, editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtSignUp=findViewById(R.id.textViewSignUp);
        btnLogin=findViewById(R.id.buttonSignin);
        progressBar=findViewById(R.id.progress);
        Auth=FirebaseAuth.getInstance();
        editEmail=findViewById(R.id.editTextEmail);
        editText=findViewById(R.id.editTextPassword);
        btnReset=findViewById(R.id.reset1);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity.this,ForgotPassword.class));
                finish();
            }
        });


        //set onClick litesner for signUp...!
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Main.class));
            }
        });

        //set onClick litesner for LoginBtn...!
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = editEmail.getText().toString().trim();
                final String Password = editText.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(Activity.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(Activity.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //progressbar set visibility....!
                progressBar.setVisibility(View.VISIBLE);

                //get user email and password and signIn....!
                Auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            if (Password.length() < 6){
                                editText.setError("minimum password 6 char");
                            }else {
                                Toast.makeText(Activity.this, "Auth fail", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            startActivity(new Intent(Activity.this,profile.class));
                            finish();

                        }

                    }
                });
            }
        });
    }
}
