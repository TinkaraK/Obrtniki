package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button register, login;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Context context = getApplicationContext();

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        login = findViewById(R.id.loginButtonL);
        register = findViewById(R.id.registerButtonL);

        register.setOnClickListener(v -> {
            Intent intent = new Intent(context, RegisterActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();
            Toast.makeText(context, "Logging in: " + emailString, Toast.LENGTH_SHORT).show();
            login(emailString, passwordString);
        });
    }

    void login(String email, String password) {

    }
}