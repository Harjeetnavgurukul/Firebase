package com.draw.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity implements View.OnClickListener{

    //firebase auth project..!
    private FirebaseAuth firebaseAuth;

    //view object...!
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //intializing firebase authentication object...!
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in....!
        //that means current user will return...!
        if (firebaseAuth.getCurrentUser() == null){

            //closing this activity...!
            finish();

            //starting login activity...!
            startActivity(new Intent(this, Activity.class));
        }

        //getting current user...!
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //intializing view...!
        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        buttonLogout = findViewById(R.id.buttonLogout);

        //adding listener to button...!
        buttonLogout.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        //if logout is pressed...!
        if (view == buttonLogout){

            //loggin out the user...!
            firebaseAuth.signOut();

            //closing activity...!
            finish();

            //starting login activity...!
            startActivity(new Intent(this, Activity.class));
        }

    }
}
