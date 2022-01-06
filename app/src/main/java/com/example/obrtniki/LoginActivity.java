package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button obiskovalciButton;
    EditText obiskovalecEmail, obiskovalecPassword;
    TextView registerInstead;
    Context context;
    int loggedInId;
    SharedPreferences sharedPref;

    Boolean canRegister = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        obiskovalecEmail = findViewById(R.id.obiskovalecEmail);
        obiskovalecPassword = findViewById(R.id.obiskovalecPassword);
        obiskovalciButton = findViewById(R.id.obiskovalecButton);
        registerInstead = findViewById(R.id.registerInstead);
        sharedPref = context.getSharedPreferences(
                "preferences", Context.MODE_PRIVATE);

        registerInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

        obiskovalciButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "";
                String password = "";
                if (obiskovalecEmail.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Vpišite email", Toast.LENGTH_SHORT).show();
                }
                else {
                    email = obiskovalecEmail.getText().toString();
                    canRegister = true;
                }
                if (obiskovalecPassword.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Vpišite geslo", Toast.LENGTH_SHORT).show();
                }
                else {
                    password = obiskovalecPassword.getText().toString();
                    canRegister = true;

                }
                if (canRegister) {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(email);
                    loginRequest.setPassword(password);
                    loginUser(loginRequest);//nafilam podatke
                }
                else {
                    Toast.makeText(context, "Prosimo izpolnite vsa polja", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void loginUser(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);//klice api
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    String message = "Prijava je uspela.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.getRole() == 1) {
                        startActivity(new Intent(context, TradeTypesListActivity.class)
                                .putExtra("data", loginResponse)
                        );
                        finish();
                    }
                    else if (loginResponse.getRole() == 2) {
                        startActivity(new Intent(context, CreateObrtnikActivity.class)
                                .putExtra("data", loginResponse)
                        );
                        finish();
                    }

                    loggedInId = Integer.parseInt(loginResponse.getId());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("userId", loggedInId);
                    editor.apply();
                    startActivity(new Intent(context, TradeTypesListActivity.class)
                            .putExtra("data", loginResponse)
                    );
                    finish();

                }
                else {
                    String message = "Prijava ni uspela. Prosimo poskusite še enkrat.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }


    void startApp() {
         Intent intent = new Intent(context, TradeTypesListActivity.class);
         startActivity(intent);
    }

}