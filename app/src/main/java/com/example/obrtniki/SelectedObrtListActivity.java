package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SpecificObrtnikActivity extends AppCompatActivity {
    TextView tipObrtnika;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_obrtnik);

        tipObrtnika = findViewById(R.id.tipObrtnikaTV);

        Intent intent = getIntent();
        int tip = intent.getIntExtra("tip", 0);
        String naziv = intent.getStringExtra("naziv" );

        tipObrtnika.setText("Tip št. " + tip + " je " + naziv);
    }
}