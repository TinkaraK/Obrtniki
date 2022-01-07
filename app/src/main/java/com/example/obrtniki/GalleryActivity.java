package com.example.obrtniki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GalleryActivity extends AppCompatActivity{

    private ImageSwitcher slike;
    Button prejsna, naslednja,izberi;
    public static Context context;
    LinearLayout slikice;

    private ArrayList<Uri> imageUris;
    //request code
    private static final int PICK_IMAGES_CODE=0;
    //position of selected image
    int position=0;
    int id,tip;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent intent = getIntent();
        Bundle extras= getIntent().getExtras();
        if(extras != null) {
            id = intent.getIntExtra("id", 0);
            tip = intent.getIntExtra("tip",0);
        }


        context = getApplicationContext();

        slikice = findViewById(R.id.layoutslik);
        slike=findViewById(R.id.Images);
        prejsna=findViewById(R.id.button_prejsna);
        naslednja=findViewById(R.id.button_naslednja);
        izberi=findViewById(R.id.button_izberi_slike);


        if(tip==1) {
            izberi.setVisibility(View.GONE);
            slike.setVisibility(View.GONE);
            prejsna.setVisibility(View.GONE);
            naslednja.setVisibility(View.GONE);
        }
        imageUris= new ArrayList<>();



        slike.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(getApplicationContext());
            }
        });

        //"http://192.168.0.17:8000/storage/documents/" +id+ "/0.jpg"

        //ImageView slika = new ImageView(context);
        //Glide.with(context).load("http://192.168.0.17:8000/storage/documents/" +id+ "/0.jpg").into(slika);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 200);
        //slika.setLayoutParams(layoutParams);
        //slikice.addView(slika);

        for (int i = 0; i < 5; i++) {
            ImageView slika = new ImageView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,800 ); //slike 600x800
            lp.setMargins(0,0,0,10);
            slika.setLayoutParams(lp);
            Glide.with(context).load("http://192.168.0.17:8000/storage/documents/" +id+ "/"+i+".jpg").into(slika);
            slikice.addView(slika);
        }




        izberi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImagesIntent();
            }
        });

        prejsna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>0) {
                    position--;
                    slike.setImageURI(imageUris.get(position));
                }
                else
                {
                    Toast.makeText(context,"Ni prejšnjih slik",Toast.LENGTH_SHORT).show();
                }
            }
        });

        naslednja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<imageUris.size()-1){
                    position++;
                    slike.setImageURI(imageUris.get(position));
                }
                else{
                    Toast.makeText(context,"Ni več slik", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void pickImagesIntent(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Izberi slike"),PICK_IMAGES_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGES_CODE){
            if(resultCode == Activity.RESULT_OK){
                if(data.getClipData() != null){
                    //vec slik
                    int count= data.getClipData().getItemCount();
                    for(int i=0;i<count;i++){
                        Uri imageUri=data.getClipData().getItemAt(i).getUri();
                        imageUris.add(imageUri);

                    }
                    slike.setImageURI(imageUris.get(0));
                    position = 0;
                }
                else {
                    //ena slika
                    Uri imageUri=data.getData();
                    imageUris.add(imageUri);
                    slike.setImageURI(imageUris.get(0));
                    position=0;
                }
            }
        }

       addimages();
    }

    void addimages()
    {
        for (int i = 0; i < imageUris.size(); i++) {
            ImageRequest imageRequest = new ImageRequest();
            imageRequest.setCraftsman_id(id);
            imageRequest.setFile(imageUris.get(i).toString());
            imageRequest.setTitle(i+"");
            add(imageRequest);

        }

    }

    void add(ImageRequest request)
    {
        Call<ImageResponse> imageResponseCall = ApiClient.getService().addImage(request);
        imageResponseCall.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    String message = "";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                }
                else{
                    String message = "";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            }
        });

    }
}
