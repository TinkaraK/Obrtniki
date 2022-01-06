package com.example.obrtniki;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsActivity extends AppCompatActivity{
    //iz api dobim podatke, ki jih vnesem na linearni layout
    LinearLayout seznam;
    int id;
    int userid;
    Button dodaj, dodajz;
    EditText mnenje;
    Spinner zvezde;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reviews);

        Intent intent = getIntent();
        Bundle extras= getIntent().getExtras();
        if(extras != null) {
            id = intent.getIntExtra("id", 0);
            userid = intent.getIntExtra("userId",0);
        }


        context = getApplicationContext();
        seznam=findViewById(R.id.layoutmnenja);
        dodaj=findViewById(R.id.dodajmnenje);
        mnenje=findViewById(R.id.editmnenje);
        zvezde=findViewById(R.id.spinnerzvezde);
        dodajz=findViewById(R.id.dodajzvezde);

        String [] zvezda = {"1","2","3","4","5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, zvezda);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zvezde.setAdapter(adapter);

        dodajz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRatingRequest ratingRequest = new AddRatingRequest();
                ratingRequest.setRating(zvezde.getSelectedItem().toString());
                ratingRequest.setUser_id(userid+"");
                ratingRequest.setCraftsman_id(id+"");
                dodajzvezde(ratingRequest);
            }
        });


        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mnenje.getText().length() != 0) {

                    AddReviewRequest reviewRequest = new AddReviewRequest();
                    reviewRequest.setComment(mnenje.getText().toString());
                    reviewRequest.setCraftsman_id(id+"");
                    reviewRequest.setUser_id(userid+"");

                    dodajmnenje(reviewRequest);
                }
            }
        });

        //seznam.setNestedScrollingEnabled(false);
        getReviews();




    }

    void getReviews ()
    {
        Call<List<ReviewsResponse>> reviewsResponseCall = ApiClient.getService().craftsmenReviews(id);
        reviewsResponseCall.enqueue(new Callback<List<ReviewsResponse>>() {
            @Override
            public void onResponse(Call<List<ReviewsResponse>> call, Response<List<ReviewsResponse>> response) {
                if (response.isSuccessful()) {

                    String message = "Obrtnik najden.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    List<ReviewsResponse> reviewsResponse= response.body();

                    assert reviewsResponse != null;
                    for(ReviewsResponse res: reviewsResponse){
                        TextView mnenje = new TextView(context);
                        mnenje.setText(res.getComment());
                        LinearLayout.LayoutParams params = new
                                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(20,20,20,20); // setMargins(left, top, right, bottom)
                        mnenje.setLayoutParams(params);
                        mnenje.setTextSize(16);
                        mnenje.setTextColor(Color.BLACK);
                        seznam.addView(mnenje);
                    }
                }
                else {
                    String message = "Obrnik ni bil najdem. Napaka na omrežju.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<ReviewsResponse>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            }
        });
    }



    void dodajmnenje(AddReviewRequest request)
    {
        Call<AddReviewResponse> addReviewResponseCall= ApiClient.getService().addReview(request);
        addReviewResponseCall.enqueue(new Callback<AddReviewResponse>() {
            @Override
            public void onResponse(Call<AddReviewResponse> call, Response<AddReviewResponse> response) {
                if (response.isSuccessful()) {
                    String message = "Vaše mnenje je bilo dodano.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    AddReviewResponse addReviewResponse = response.body();

                    TextView m = new TextView(context);
                    m.setText(mnenje.getText());
                    LinearLayout.LayoutParams params = new
                            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(20,20,20,20); // setMargins(left, top, right, bottom)
                    m.setLayoutParams(params);
                    m.setTextSize(16);
                    m.setTextColor(Color.BLACK);
                    seznam.addView(m);

                    mnenje.setText("");


                }
                else {
                    String message = "Mnenje ni bilo dodano. Prosimo poskusite še enkrat.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddReviewResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    void dodajzvezde(AddRatingRequest request){
        Call<AddRatingResponse> addRatingResponseCall= ApiClient.getService().addRating(request);
        addRatingResponseCall.enqueue(new Callback<AddRatingResponse>() {
            @Override
            public void onResponse(Call<AddRatingResponse> call, Response<AddRatingResponse> response) {
                if (response.isSuccessful()) {
                    String message = "Vaša ocena je bila dodana.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    AddRatingResponse addRatingResponse = response.body();
                    zvezde.setSelection(0);

                }
                else {
                    String message = "Ocena ni bila dodana. Prosimo poskusite še enkrat.";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddRatingResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });

    }


}
