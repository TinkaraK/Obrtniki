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

public class RegisterObiskovalecActivity extends AppCompatActivity {
    EditText imeET, priimekET, emailET, gesloET;
    Button registriraj;
    TextView prijaviSe;
    Context context;
    Boolean canRegister = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_obiskovalec);
        context = getApplicationContext();
        imeET = findViewById(R.id.obiskovalecImeR);
        priimekET = findViewById(R.id.obiskovalecPriimekR);
        emailET = findViewById(R.id.obiskovalecEmailR);
        gesloET = findViewById(R.id.obiskovalecPasswordR);

        registriraj = findViewById(R.id.obiskovalecRegister);
        registriraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ime = "";
                if (imeET.getText().toString().isEmpty()) {
                    //        Toast.makeText(context, "Vpišite naziv podjetja", Toast.LENGTH_SHORT).show();
                    canRegister = false;
                }
                else {
                    ime = imeET.getText().toString();
                    canRegister = true;
                }

                String priimek = "";
                if (priimekET.getText().toString().isEmpty()) {
                    //         Toast.makeText(context, "Vpišite naslov podjetja", Toast.LENGTH_SHORT).show();
                    canRegister = false;
                }
                else {
                    priimek = priimekET.getText().toString();
                    canRegister = true;
                }

                String email = "";
                if (emailET.getText().toString().isEmpty()) {
                    //       Toast.makeText(context, "Vpišite email podjetja", Toast.LENGTH_SHORT).show();
                    canRegister = false;
                }
                else {
                    email = emailET.getText().toString();
                    canRegister = true;
                }

                String geslo = "";
                if (gesloET.getText().toString().isEmpty()) {
                    //        Toast.makeText(context, "Vpišite geslo", Toast.LENGTH_SHORT).show();
                    canRegister = false;
                }
                else {
                    geslo = gesloET.getText().toString();
                    canRegister = true;
                }
                if (canRegister) {
                    registerObiskovalec(ime, priimek, email, geslo);
                }
                else {
                    Toast.makeText(context, "Prosimo izpolnite vsa polja", Toast.LENGTH_SHORT).show();
                }
            }
        });
        prijaviSe = findViewById(R.id.obiskovalecLoginInstead);

        prijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginObiskovalecActivity.class);
                startActivity(intent);
            }
        });

    }

    void registerObiskovalec(String ime, String priimek, String email, String geslo) {
        Toast.makeText(context, "Registracija poteka " + ime + priimek, Toast.LENGTH_SHORT).show();

    }
}