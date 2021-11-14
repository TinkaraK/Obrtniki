package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstScreenActivity extends AppCompatActivity {
    Button obiskovalciButton, obrtnikiButton;
    EditText obiskovalciInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        Context context = getApplicationContext();

        obiskovalciInput = findViewById(R.id.obiskovalecInput);
        obiskovalciButton = findViewById(R.id.obiskovalecButton);
        obrtnikiButton = findViewById(R.id.obrtnikButton);

        obiskovalciButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (obiskovalciInput.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Za nadaljevanje vpišite ime", Toast.LENGTH_SHORT).show();
                }
                else {
                    String ime = obiskovalciInput.getText().toString();
                    Intent intent = new Intent(context, ListTipiObrti.class);
                    intent.putExtra("imeObiskovalca", ime);
                    startActivity(intent);
                }
            }
        });

        obrtnikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}