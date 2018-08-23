package com.draw.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        inputEmail=findViewById(R.id.edit);
        btnReset=findViewById(R.id.reset);
        btnBack=findViewById(R.id.btnReset);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progress2);

        //set on click listener get Email for forgot password...!
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=inputEmail.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(ForgotPassword.this, "Enter your email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                //set progressbar set visibility...!
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(Email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPassword.this, "We have send you email please check is", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ForgotPassword.this, "Fail to send email", Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });

        //set click listener ForgotPassword to Activity...!
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this,Activity.class));
                finish();
            }
        });
    }
}
