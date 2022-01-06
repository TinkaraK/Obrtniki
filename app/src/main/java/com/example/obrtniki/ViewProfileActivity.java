package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//post da dobim podatki ki jih lahko pol uporabim za izgled profila
//view lahko vidijo samo stranke

public class ViewProfileActivity extends AppCompatActivity  {

    Button urediOp,urediOs,shraniOp, shraniOs, mnenja,prikazi_galerijo;
    public static Context context;
    TextView imeP, mailP, telefonP,zvezda,naslovP,regijaP,cenaP;
    EditText editOp, editOs;
    ViewProfileResponse viewProfileResponse;
    ImageView slika;
    int id,userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_obrtnik);

        Intent intent = getIntent();
        Bundle extras= getIntent().getExtras();
        if(extras != null) {
            id = intent.getIntExtra("id", 0);
            userid =intent.getIntExtra("userId",0);
        }

        context = getApplicationContext();
        urediOp=findViewById(R.id.button_uredi_op);
        urediOs=findViewById(R.id.button_uredi_os);
        mnenja=findViewById(R.id.button_show_reviews);
        shraniOp=findViewById(R.id.button_shrani_op);
        shraniOs=findViewById(R.id.button_shrani_os);
        prikazi_galerijo=findViewById(R.id.button_prikazi_slike);
        slika=findViewById(R.id.imageView_profilna);

        imeP=findViewById(R.id.textView_firma);
        mailP=findViewById(R.id.textView_mail);
        telefonP=findViewById(R.id.textView_tel);
        zvezda=findViewById(R.id.zvezda);
        naslovP=findViewById(R.id.textviewnaslov);
        regijaP=findViewById(R.id.regijaobr);
        cenaP=findViewById(R.id.textViewCR);
        editOp=findViewById(R.id.edittext_opis_podjetja);
        editOs=findViewById(R.id.edittext_opis_storitev);


        urediOp.setVisibility(View.INVISIBLE);
        urediOs.setVisibility(View.INVISIBLE);
        shraniOs.setVisibility(View.INVISIBLE);
        shraniOp.setVisibility(View.INVISIBLE);
        mnenja.setVisibility(View.VISIBLE);



        mnenja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewsActivity.class).putExtra("id",id)
                        .putExtra("userId",userid);
                startActivity(intent);
            }
        });

        prikazi_galerijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //odpre nov activity s galerijo slik
                Intent intent = new Intent(context, GalleryActivity.class).putExtra("id",id).putExtra("tip",1);
                startActivity(intent);
            }
        });

        getCraftsmenData();



    }

    private void getCraftsmenData()
    {
        Call<ViewProfileResponse> viewProfileResponseCall = ApiClient.getService().craftsmenData(id);
        viewProfileResponseCall.enqueue(new Callback<ViewProfileResponse>() {
            @Override
            public void onResponse(Call<ViewProfileResponse> call, Response<ViewProfileResponse> response) {
                if (response.isSuccessful()) {

                    String message = "Obrtnik najden.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    viewProfileResponse = response.body();
                    String tip=viewProfileResponse.getTrade_type();
                    switch (tip) {
                        case "arhitekt":
                            slika.setImageResource(R.drawable.arhitekt);
                            break;
                        case "električar":
                            slika.setImageResource(R.drawable.elektricar);
                            break;
                        case "gradbenik":
                            slika.setImageResource(R.drawable.gradbenik);
                            break;
                        case "okna, vrata":
                            slika.setImageResource(R.drawable.oknavrata);
                            break;
                        case "pleskar":
                            slika.setImageResource(R.drawable.slikar);
                            break;
                        case "vodovodar":
                            slika.setImageResource(R.drawable.vodovodar);
                            break;
                        case "vrtnar":
                            slika.setImageResource(R.drawable.vrtnar);
                            break;
                        case "mizar":
                            slika.setImageResource(R.drawable.mizar);
                            break;

                    }
                    imeP.setText(viewProfileResponse.getCompany_name());
                    mailP.setText(viewProfileResponse.getEmail());
                    telefonP.setText(viewProfileResponse.getPhone_number());
                    zvezda.setText(String.format("%.2f  ★",viewProfileResponse.getAvg_rating()));

                    naslovP.setText("Naslov: " +viewProfileResponse.getAddress()+", "+viewProfileResponse.getCity());
                    regijaP.setText("Regija obratovanja: "+viewProfileResponse.getRegion());
                    cenaP.setText("Cenovni razred: "+viewProfileResponse.getPrice_range());

                    editOp.setText(viewProfileResponse.getCompany_description());
                    editOs.setText(viewProfileResponse.getService_description());
                }
                else {
                    String message = "Obrnik ni bil najdem. Napaka na omrežju.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ViewProfileResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            }
        });
    }

}
