package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterObrtnikActivity extends AppCompatActivity {
    EditText nazivET, naslovET, postaET, emailET, telefonET, gesloET, ponoviGesloET, davcnaET;
    Button loginButton, registerButton;
    Boolean canRegister = false;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_obrtnik);

        context = getApplicationContext();

        nazivET = findViewById(R.id.naziv);
        naslovET = findViewById(R.id.naslov);
        postaET = findViewById(R.id.posta);
        emailET = findViewById(R.id.email);
        telefonET = findViewById(R.id.telefonska);
        davcnaET = findViewById(R.id.davcna);
        gesloET = findViewById(R.id.registerPassword);
        ponoviGesloET = findViewById(R.id.repeatRegisterPassword);

        loginButton = findViewById(R.id.loginButtonR);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginObrtnikActivity.class);
                startActivity(intent);
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
        String geslo = "";
        if (gesloET.getText().toString().isEmpty()) {
            //        Toast.makeText(context, "Vpišite geslo", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            geslo = gesloET.getText().toString();
            canRegister = true;
        }
        String gesloRepeat = "";
        if (ponoviGesloET.getText().toString().isEmpty()) {
        //    Toast.makeText(context, "Ponovno vpišite geslo", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            gesloRepeat = ponoviGesloET.getText().toString();
            canRegister = true;
        }

        if (geslo.matches(gesloRepeat))
            canRegister = true;
        else {
            canRegister = false;
            Toast.makeText(context, "Gesli se ne ujemata", Toast.LENGTH_SHORT).show();
        }

        if (canRegister) {
            startRegistering(naziv, naslov, posta, email, davcna, telefonska, geslo);
        }
        else {
            Toast.makeText(context, "Prosimo izpolnite vsa polja", Toast.LENGTH_SHORT).show();
        }
    }

    void startRegistering(String naziv, String naslov, String posta, String email, String davcna, String telefonska, String geslo) {
        Toast.makeText(context, "Registracija poteka", Toast.LENGTH_SHORT).show();
    }
}