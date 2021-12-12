package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeTypesListActivity extends AppCompatActivity {
    LoginResponse loginResponse;
    List<TradeType> listObrtnikov;
    TextView dobrodosli;
    TextView logoutTV;
    Context context;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_types_list);
        context = getApplicationContext();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            loginResponse = (LoginResponse) intent.getSerializableExtra("data");
            Log.i("Tinkara", loginResponse.getFirst_name());
        }
        sharedPref = context.getSharedPreferences("token", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", loginResponse.getToken());
        editor.apply();


        dobrodosli = findViewById(R.id.dobrodosli);
        dobrodosli.setText("Dobrodošli, " + loginResponse.getFirst_name());

        listObrtnikov = new ArrayList<>();
        listObrtnikov.add(new TradeType(1, "arhitekti", R.drawable.arhitekt));
        listObrtnikov.add(new TradeType(2, "električarji", R.drawable.elektricar));
        listObrtnikov.add(new TradeType(3, "gradbeniki", R.drawable.gradbenik));
        listObrtnikov.add(new TradeType(4, "okna, vrata", R.drawable.oknavrata));
        listObrtnikov.add(new TradeType(5, "slikarji", R.drawable.slikar));
        listObrtnikov.add(new TradeType(6, "vodovodarji", R.drawable.vodovodar));
        listObrtnikov.add(new TradeType(7, "vrtnarji", R.drawable.vrtnar));
        listObrtnikov.add(new TradeType(8, "mizarji", R.drawable.mizar));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView_tipiObrtnikov);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, listObrtnikov);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));

        myrv.setAdapter(myAdapter);
    }


}