package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateObrtnikActivity extends AppCompatActivity {

    EditText naziv, naslov, kraj, posta, telefon, davcna,opispodjetja,opisstoritve;
    Button createButton, registerButton;
    Boolean canRegister = false;
    LoginResponse loginData;
    Spinner spTip,spReg,spCena;
    String []tip =new String[] {"arhitektura","električarstvo","gradbeništvo","okna in vrata","pleskarstvo","vodovodarstvo","vrtnarstvo","mizarstvo"};
    String []regija= new String[]{"osrednjeslovenska","gorenjska","goriška","obalno-kraška","primorsko-notranjska","jugovzhodna","posavska","zasavska","savinjska","koroška","podravska","pomurska"};
    String []cena= new String[]{"nizkocenovno","normalno","visokocenovno"};
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_obrtnik);

        Intent intent = getIntent();
        Bundle extras= getIntent().getExtras();
        if(extras != null)
            loginData = (LoginResponse) intent.getSerializableExtra("data");

        context = getApplicationContext();

        naziv = findViewById(R.id.naziv);
        naslov = findViewById(R.id.naslov);
        kraj=findViewById(R.id.kraj);
        posta = findViewById(R.id.posta);
        telefon = findViewById(R.id.telefonska);
        davcna = findViewById(R.id.davcna);
        opispodjetja=findViewById(R.id.opisPodjetja);
        opisstoritve=findViewById(R.id.opisStoritve);

        spTip=findViewById(R.id.spinnerTip);
        spReg=findViewById(R.id.spinnerRegija);
        spCena=findViewById(R.id.spinnerCenovniRang);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, tip);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTip.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, regija);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spReg.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, cena);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCena.setAdapter(adapter);


        createButton = findViewById(R.id.loginButtonR);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verjetno odpre obrtnikov profil?
                if(loginData.getCraftsman_id() != -1) {
                    Intent intent = new Intent(context, EditProfileActivity.class)
                            .putExtra("id", loginData.getCraftsman_id())
                            .putExtra("login",loginData);
                    startActivity(intent);
                }
                else
                    Toast.makeText(context, "Vaš profil še ni ustvarjen, prosim izpolnite podatke!", Toast.LENGTH_SHORT).show();

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
        String n = "";
        if (naziv.getText().toString().isEmpty()) {
            //        Toast.makeText(context, "Vpišite naziv podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            n = naziv.getText().toString();
            canRegister = true;
        }

        String nas = "";
        if (naslov.getText().toString().isEmpty()) {
            //         Toast.makeText(context, "Vpišite naslov podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            nas = naslov.getText().toString();
            canRegister = true;
        }

        String k = "";
        if (kraj.getText().toString().isEmpty()) {
            //        Toast.makeText(context, "Vpišite pošto podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            k = kraj.getText().toString();
            canRegister = true;
        }

        String po = "";
        if (posta.getText().toString().isEmpty() && posta.getText().length() == 4) {
            //        Toast.makeText(context, "Vpišite pošto podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            po = posta.getText().toString();
            canRegister = true;
        }


        String telefonska = "";
        if (telefon.getText().toString().isEmpty() && telefon.getText().length()<=13) {
            //        Toast.makeText(context, "Vpišite telefonsko podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            telefonska = telefon.getText().toString();
            canRegister = true;
        }

        String dav = "";
        if (davcna.getText().toString().isEmpty() && davcna.getText().length()<=8) {
            //       Toast.makeText(context, "Vpišite davčno podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            dav = davcna.getText().toString();
            canRegister = true;
        }
        String op = "";
        if (opispodjetja.getText().toString().isEmpty()) {
            //       Toast.makeText(context, "Vpišite davčno podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            op = opispodjetja.getText().toString();
            canRegister = true;
        }
        String os = "";
        if (opisstoritve.getText().toString().isEmpty()) {
            //       Toast.makeText(context, "Vpišite davčno podjetja", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }
        else {
            os = opisstoritve.getText().toString();
            canRegister = true;
        }

        int tip= spTip.getSelectedItemPosition()+1;
        int regija = spReg.getSelectedItemPosition()+1;
        int cena = spCena.getSelectedItemPosition()+1;

        if (canRegister) {
             CreateObrtnikRequest request = new CreateObrtnikRequest();
             request.setCompany_name(n);
             request.setAddress(nas);
             request.setPost_number(po);
             request.setCity(k);
             request.setTax_number(dav);
             request.setTrade_type_id(tip);
             request.setRegion_id(regija);
             request.setPrice_range_id(cena);
             request.setService_description(os);
             request.setUser_id(loginData.getId());
             request.setCompany_description(op);
             request.setPhone_number(telefonska);

             createObrtnik(request);
        }
        else {
            Toast.makeText(context, "Prosimo izpolnite vsa polja", Toast.LENGTH_SHORT).show();
        }


    }

    void createObrtnik(CreateObrtnikRequest request) {
        Call<CreateObrtnikResponse> createObrtnikResponseCall = ApiClient.getService().createObrtnik(request);
        createObrtnikResponseCall.enqueue(new Callback<CreateObrtnikResponse>() {
            @Override
            public void onResponse(Call<CreateObrtnikResponse> call, Response<CreateObrtnikResponse> response) {
                if (response.isSuccessful()) {
                    String message = "Profil ustvarjen";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    CreateObrtnikResponse createObrtnikResponse = response.body();
                    startActivity(new Intent(context, EditProfileActivity.class)
                        .putExtra("data", createObrtnikResponse)
                                .putExtra("login",loginData).putExtra("id",createObrtnikResponse.getId())
                    );

                    finish();
                }
                else {
                    String message = "Profil ni bil ustvarjen. Prosimo poskusite še enkrat.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CreateObrtnikResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }


}