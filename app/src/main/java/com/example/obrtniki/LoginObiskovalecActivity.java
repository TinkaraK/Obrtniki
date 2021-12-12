package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginObiskovalecActivity extends AppCompatActivity {
    Button obiskovalciButton, obrtnikiButton;
    EditText obiskovalecEmail, obiskovalecPassword;
    TextView registerInstead;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_obiskovalec);
         context = getApplicationContext();

        obiskovalecEmail = findViewById(R.id.obiskovalecEmail);
        obiskovalecPassword = findViewById(R.id.obiskovalecPassword);
        obiskovalciButton = findViewById(R.id.obiskovalecButton);
        obrtnikiButton = findViewById(R.id.obrtnikButton);
        registerInstead = findViewById(R.id.registerInstead);

        registerInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterObiskovalecActivity.class);
                startActivity(intent);
            }
        });

        obiskovalciButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (obiskovalecEmail.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Vpišite email", Toast.LENGTH_SHORT).show();
                }
                else if (obiskovalecPassword.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Vpišite geslo", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginObiskovalec(obiskovalecEmail.getText().toString(), obiskovalecPassword.getText().toString());
                   // String ime = obiskovalciInput.getText().toString();
                   // Intent intent = new Intent(context, ListTipiObrti.class);
                  //  intent.putExtra("imeObiskovalca", ime);
                   // startActivity(intent);
                }
            }
        });

        obrtnikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginObrtnikActivity.class);
                startActivity(intent);
            }
        });
    }

    void loginObiskovalec(String email, String password) {
        Toast.makeText(context, "prijavljamo" + email, Toast.LENGTH_SHORT).show();

    }

    void startApp() {
         Intent intent = new Intent(context, ListTipiObrti.class);
         startActivity(intent);
    }

}