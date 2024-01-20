package com.example.java_final;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;

    TextView login;
    EditText email;
    EditText password;

    Button signupButton;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.login_text);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signupButton = findViewById(R.id.signup_button);
        loginButton = findViewById(R.id.log_in);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignupActivity();
            }
        });



        loginButton.setOnClickListener(view -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmailAndPassword:success");
                                startHomeActivity();
                            } else {
                                // Giriş başarısızsa, kullanıcıya bir hata mesajı göster
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }


                    });
        });
    }

    public  void startSignupActivity() {
        Intent i = new Intent(Login.this, SignUp.class);
        startActivity(i);
    }

    public  void startHomeActivity() {
        Intent i = new Intent(Login.this, Home.class);
        startActivity(i);
        finish();
    }
}
