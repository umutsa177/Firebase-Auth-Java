package com.example.java_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView name;
    TextView no;
    Button login;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        name = findViewById(R.id.name);
        no = findViewById(R.id.school_no);
        login = findViewById(R.id.login_splash);
        signup = findViewById(R.id.signup_splash);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startHomeActivity();
        }

        login.setOnClickListener(view -> startLoginActivity());

        signup.setOnClickListener(view -> startSignupActivity());
    }

    public  void startLoginActivity() {
        Intent i = new Intent(Splash.this, Login.class);
        startActivity(i);
    }

    public  void startSignupActivity() {
        Intent i = new Intent(Splash.this, SignUp.class);
        startActivity(i);
        finish();
    }

    public  void startHomeActivity() {
        Intent i = new Intent(Splash.this, Home.class);
        startActivity(i);
        finish();
    }

}
