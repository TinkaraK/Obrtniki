package com.example.obrtniki;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//lahko vidi samo obrtnik
public class EditProfileActivity extends AppCompatActivity{

    TextView imeP, mailP, telefonP,zvezda,naslovP,regijaP,cenaP;
    Button urediOp,urediOs;
    Button shraniOp,shraniOs;
    EditText editOp, editOs;
    Button reviews,galerija;
    CreateObrtnikResponse data;
    ViewProfileResponse viewProfileResponse;
    LoginResponse loginData;
    ImageView slika;
    int id;
    public static Context context;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_obrtnik);

        Intent intent = getIntent();
        Bundle extras= getIntent().getExtras();
        if(extras != null) {
            data = (CreateObrtnikResponse) intent.getSerializableExtra("data");
            loginData = (LoginResponse) intent.getSerializableExtra("login");
            id = intent.getIntExtra("id", 0);
        }


        context = getApplicationContext();

        imeP=findViewById(R.id.textView_firma);
        mailP=findViewById(R.id.textView_mail);
        telefonP=findViewById(R.id.textView_tel);
        zvezda=findViewById(R.id.zvezda);
        naslovP=findViewById(R.id.textviewnaslov);
        regijaP=findViewById(R.id.regijaobr);
        cenaP=findViewById(R.id.textViewCR);
        slika=findViewById(R.id.imageView_profilna);


        urediOp=findViewById(R.id.button_uredi_op);
        urediOs=findViewById(R.id.button_uredi_os);
        shraniOp=findViewById(R.id.button_shrani_op);
        shraniOs=findViewById(R.id.button_shrani_os);
        reviews=findViewById(R.id.button_show_reviews);
        galerija=findViewById(R.id.button_prikazi_slike);
        editOp=findViewById(R.id.edittext_opis_podjetja);
        editOs=findViewById(R.id.edittext_opis_storitev);

        reviews.setVisibility(View.INVISIBLE);
        shraniOs.setVisibility(View.INVISIBLE);
        shraniOp.setVisibility(View.INVISIBLE);

        getCraftsmenData();

        /*reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewsActivity.class).putExtra("id",id).putExtra("userId",loginData.getId());
                startActivity(intent);
            }
        });*/

        urediOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shraniOp.setVisibility(View.VISIBLE);
                urediOp.setVisibility(View.INVISIBLE);
                editOp.setInputType(InputType.TYPE_CLASS_TEXT);
                //spreminja textview
            }
        });
        shraniOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shraniOp.setVisibility(View.INVISIBLE);
                urediOp.setVisibility(View.VISIBLE);
                editOp.setInputType(InputType.TYPE_NULL);
                spremembe();
            }
        });

        urediOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shraniOs.setVisibility(View.VISIBLE);
                urediOs.setVisibility(View.INVISIBLE);
                editOs.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });
        shraniOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shraniOs.setVisibility(View.INVISIBLE);
                urediOs.setVisibility(View.VISIBLE);
                editOs.setInputType(InputType.TYPE_NULL);
                spremembe();

            }
        });

        galerija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //odpre nov activity s galerijo slik
                Intent intent = new Intent(context, GalleryActivity.class).putExtra("id",id).putExtra("tip",0);
                startActivity(intent);
            }
        });



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

    private void spremembe()
    {
        EditProfileRequest editProfileRequest = new EditProfileRequest();
        String []tip =new String[] {"arhitekt","električar","gradbenik","okna, vrata","pleskar","vodovodar","vrtnar","mizar","varnost","ogrevanje/hlajenje"};
        String []regija= new String[]{"osrednjeslovenska","gorenjska","goriška","obalno-kraška","primorsko-notranjska","jugovzhodna","posavska","zasavska","savinjska","koroška","podravska","pomurska"};
        String []cena= new String[]{"nizkocenovno","normalno","visokocenovno"};

        editProfileRequest.setId(id);
        editProfileRequest.setAddress(viewProfileResponse.getAddress());
        editProfileRequest.setCity(viewProfileResponse.getCity());
        editProfileRequest.setCompany_description(editOp.getText().toString());
        editProfileRequest.setService_description(editOs.getText().toString());
        editProfileRequest.setCompany_name(viewProfileResponse.getCompany_name());
        editProfileRequest.setPhone_number(viewProfileResponse.getPhone_number());
        editProfileRequest.setPost_number(viewProfileResponse.getPost_number()+"");
        editProfileRequest.setTax_number(viewProfileResponse.getTax_number());
        int rid=0;
        for (int i= 0; i < regija.length; i++) {
            if(regija[i].equals(viewProfileResponse.getRegion()))
                rid=i;
        }
        int pid=0;
        for (int i= 0; i < cena.length; i++) {
            if(cena[i].equals(viewProfileResponse.getPrice_range()))
                pid=i;
        }

        int tid=0;
        for (int i= 0; i < tip.length; i++) {
            if(tip[i].equals(viewProfileResponse.getTrade_type()))
                tid=i;
        }

        editProfileRequest.setRegion_id(rid+1+"");
        editProfileRequest.setPrice_range_id(pid+1+"");
        editProfileRequest.setUser_id(loginData.getId());
        editProfileRequest.setTrade_type_id(tid+1+"");

        posljispremembe(editProfileRequest);

    }

    private void posljispremembe(EditProfileRequest editProfileRequest)
    {
        Call<EditProfileResponse> editProfileResponseCall = ApiClient.getService().craftsmenDataChanged(id,editProfileRequest);
        editProfileResponseCall.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if (response.isSuccessful()) {
                    String message = "Sprememba sprejeta.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    EditProfileResponse editProfileResponse = response.body();
                    getCraftsmenData();
                }
                else {
                    String message = "Sprememba ni bila sprejeta. Napaka na omrežju..";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
