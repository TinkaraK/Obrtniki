package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectedObrtListActivity extends AppCompatActivity implements FiltriDialog.FilterDialogListener, RecyclerViewAdapterObrtniki.OnClickListener {
    TextView title;
    EditText searchView;
    Button button;
    Context context;
    ArrayList<Obrtnik> listObrtnikov = new ArrayList<>();
    RecyclerViewAdapterObrtniki myAdapter;
    int type_id;
    String typeA[] = {"Arhitekti",
            "Električarji",
            "Gradbeniki",
            "Okna, vrata",
            "Pleskarji",
            "Vodovodarji",
            "Vrtnarji",
            "Mizarji",
            "Varnostniki",
            "Ogrevanje/hlajenje"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_obrt);

        searchView = findViewById(R.id.searchBox);
        context = getApplicationContext();
        myAdapter = new RecyclerViewAdapterObrtniki(context, listObrtnikov, this);
        button = (Button) findViewById(R.id.filtri_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openDialog();
            }
        });

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            type_id = intent.getIntExtra("tip", 0);
        }

        title = (TextView) findViewById(R.id.obrtniki_title);
        title.setText(typeA[type_id-1]);

        Call<List<Obrtnik>> call = ApiClient.getService().getObrtniki();

        call.enqueue(new Callback<List<Obrtnik>>() {
            @Override
            public void onResponse(Call<List<Obrtnik>> call, Response<List<Obrtnik>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    return;
                }
                List<Obrtnik> temp = response.body();
                //System.out.println(content);
                for( Obrtnik obrtnik : temp){
                    //content += " NAZIV: " + obrtnik.getCompany_name();
                    if(obrtnik.getTrade_type_id() == type_id)
                        listObrtnikov.add(obrtnik);
                }
                RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView_listObrtniki);
                myrv.setLayoutManager(new GridLayoutManager(context, 1));
                myrv.setAdapter(myAdapter);

                searchView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        search(s.toString());
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Obrtnik>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
    public void openDialog(){
        FiltriDialog filtriDialog = new FiltriDialog();
        filtriDialog.show(getSupportFragmentManager(), "Filtri");
    }

    private void search(String text){
        ArrayList<Obrtnik> searchList = new ArrayList<>();

        for(Obrtnik obr : listObrtnikov){
            if (obr.getCompany_name().toLowerCase().startsWith(text.toLowerCase())){
                searchList.add(obr);
            }
        }
        myAdapter.filterList(searchList);
    }

    @Override
    public void applyTexts(int reg_from_spn, int ocn_from_spn, int cen_from_spn) {
        ArrayList<Obrtnik> filteredList = new ArrayList<>();
        for( Obrtnik obr : listObrtnikov) {
            if ((reg_from_spn == 0 || obr.region_id == reg_from_spn)
                    & (ocn_from_spn == 0 || obr.avg_rating > ocn_from_spn / 2.0)
                    & (cen_from_spn == 0 || obr.price_range_id == cen_from_spn))
                filteredList.add(obr);
        }
        myAdapter.filterList(filteredList);
    }

    @Override
    public void onRowClick(int position) {
        Obrtnik obr = myAdapter.mData.get(position);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        System.out.println("USER ID: " + userId);
        /*Intent intent = new Intent(this, ViewProfileActivity.class);
        intent.putExtra("id", obr.getId());
        startActivity(intent);*/
    }
}
