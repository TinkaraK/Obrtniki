package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListTipiObrti extends AppCompatActivity {

    List<TipObrtnika> listObrtnikov;
    TextView dobrodosli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tipi_obrti);

        Intent intent = getIntent();
        String ime = intent.getStringExtra("imeObiskovalca");

        dobrodosli = findViewById(R.id.dobrodosli);
        dobrodosli.setText("Dobrodošli, " + ime);

        listObrtnikov = new ArrayList<>();
        listObrtnikov.add(new TipObrtnika(1, "arhitekti", R.drawable.arhitekt));
        listObrtnikov.add(new TipObrtnika(2, "električarji", R.drawable.elektricar));
        listObrtnikov.add(new TipObrtnika(3, "gradbeniki", R.drawable.gradbenik));
        listObrtnikov.add(new TipObrtnika(4, "okna, vrata", R.drawable.oknavrata));
        listObrtnikov.add(new TipObrtnika(5, "slikarji", R.drawable.slikar));
        listObrtnikov.add(new TipObrtnika(6, "vodovodarji", R.drawable.vodovodar));
        listObrtnikov.add(new TipObrtnika(7, "vrtnarji", R.drawable.vrtnar));
        listObrtnikov.add(new TipObrtnika(8, "mizarji", R.drawable.mizar));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView_tipiObrtnikov);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, listObrtnikov);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));

        myrv.setAdapter(myAdapter);
    }
}