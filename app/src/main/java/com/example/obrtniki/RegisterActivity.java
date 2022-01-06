package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText imeET, priimekET, emailET, gesloET;
    Button registriraj;
    TextView prijaviSe;
    CheckBox roleCB;
    Context context;
    Boolean canRegister = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = getApplicationContext();
        imeET = findViewById(R.id.obiskovalecImeR);
        priimekET = findViewById(R.id.obiskovalecPriimekR);
        emailET = findViewById(R.id.obiskovalecEmailR);
        gesloET = findViewById(R.id.obiskovalecPasswordR);
        roleCB = findViewById(R.id.obrtnikCheckbox);
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
                    canRegister = false;
                }
                else {
                    email = emailET.getText().toString();
                    canRegister = true;
                }

                String geslo = "";
                if (gesloET.getText().toString().isEmpty()) {
                    canRegister = false;
                }
                else {
                    geslo = gesloET.getText().toString();
                    canRegister = true;
                }
                int role = 1;
                if (roleCB.isChecked()) {
                    role = 2;
                }

                if (canRegister) {
                    RegisterRequest registerRequest = new RegisterRequest(); //podattki za post
                    registerRequest.setFirstName(ime);
                    registerRequest.setLastName(priimek);
                    registerRequest.setEmail(email);
                    registerRequest.setPassword(geslo);
                    registerRequest.setRole(role);
                    registerObiskovalec(registerRequest);
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
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    void registerObiskovalec(RegisterRequest registerRequest) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    String message = "Registracija je uspela";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, LoginActivity.class));
                    finish();
                }
                else {
                    String message = "Registracija ni uspela. Prosimo poskusite še enkrat.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}