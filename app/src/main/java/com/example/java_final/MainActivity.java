package com.example.java_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash = findViewById(R.id.splash_button);

        splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSplashActivity();
            }
        });
    }

    public  void startSplashActivity() {
        Intent i = new Intent(MainActivity.this, Splash.class);
        startActivity(i);
    }
}