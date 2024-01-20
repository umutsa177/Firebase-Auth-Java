package com.example.java_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    TextView deneme_text;
    Button addPhoto;
    Button addLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        deneme_text = findViewById(R.id.deneme_text);
        addPhoto = findViewById(R.id.add_photo);

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,AddPhotoFragment.class);
                startActivity(i);
            }
        });

        addLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,AddLabelFragment.class);
                startActivity(i);
            }
        });
    }


}
