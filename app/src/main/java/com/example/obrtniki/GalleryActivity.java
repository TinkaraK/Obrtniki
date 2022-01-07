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


    public static Context context;
    LinearLayout slikice;
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



        //"http://192.168.0.17:8000/storage/documents/" +id+ "/0.jpg"

        //ImageView slika = new ImageView(context);
        //Glide.with(context).load("http://192.168.0.17:8000/storage/documents/" +id+ "/0.jpg").into(slika);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 200);
        //slika.setLayoutParams(layoutParams);
        //slikice.addView(slika);

        try {
            for (int i = 0; i < 5; i++) {
                ImageView slika = new ImageView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1000, 1200); //slike 600x800
                lp.setMargins(0,0,0,10);
                slika.setLayoutParams(lp);

                Glide.with(context).load("http://192.168.0.17:8000/storage/documents/" +id+ "/"+i+".jpg").error("http://192.168.0.17:8000/storage/documents/0/1.png").into(slika);
                slikice.addView(slika);
            }
        }
        catch (Exception e){
            Toast.makeText(context, "Slike niso na voljo.", Toast.LENGTH_LONG).show();

        }


    }
}
