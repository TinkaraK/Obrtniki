package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateObrtnikActivity extends AppCompatActivity {
    EditText nazivET, naslovET, postaET, emailET, telefonET, davcnaET;
    Button createButton, registerButton;
    Boolean canRegister = false;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_obrtnik);

        context = getApplicationContext();

        nazivET = findViewById(R.id.naziv);
        naslovET = findViewById(R.id.naslov);
        postaET = findViewById(R.id.posta);
        emailET = findViewById(R.id.email);
        telefonET = findViewById(R.id.telefonska);
        davcnaET = findViewById(R.id.davcna);

        createButton = findViewById(R.id.loginButtonR);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verjetno odpre obrtnikov profil?
                //Intent intent = new Intent(context, LoginObrtnikActivity.class);
                //startActivity(intent);
            }
        });



        registerButton = findViewById(R.id.registerButtonR);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    void register() {
        String naziv = "";
        if (nazivET.getText().toString().isEmpty()) {
            //        Toast.makeText(context, "Vpišite naziv podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            naziv = nazivET.getText().toString();
            canRegister = true;
        }

        String naslov = "";
        if (naslovET.getText().toString().isEmpty()) {
            //         Toast.makeText(context, "Vpišite naslov podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            naslov = naslovET.getText().toString();
            canRegister = true;
        }

        String posta = "";
        if (postaET.getText().toString().isEmpty()) {
            //        Toast.makeText(context, "Vpišite pošto podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            posta = nazivET.getText().toString();
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

        String telefonska = "";
        if (telefonET.getText().toString().isEmpty()) {
            //        Toast.makeText(context, "Vpišite telefonsko podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            telefonska = telefonET.getText().toString();
            canRegister = true;
        }

        String davcna = "";
        if (davcnaET.getText().toString().isEmpty()) {
            //       Toast.makeText(context, "Vpišite davčno podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            davcna = davcnaET.getText().toString();
            canRegister = true;
        }

        if (canRegister) {
            createObrtnik(naziv, naslov, posta, email, davcna, telefonska);
        }
        else {
            Toast.makeText(context, "Prosimo izpolnite vsa polja", Toast.LENGTH_SHORT).show();
        }
    }

    void createObrtnik(String naziv, String naslov, String posta, String email, String davcna, String telefonska) {
        Toast.makeText(context, "Ustvarjam obrtnika", Toast.LENGTH_SHORT).show();
    }


}