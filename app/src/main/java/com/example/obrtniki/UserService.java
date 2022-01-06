package com.example.obrtniki;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
 //zahtevki za server
    //pri get ni body
    @POST("craftsmen")
    Call<CreateObrtnikResponse> createObrtnik(@Body CreateObrtnikRequest createObrtnikRequest);


    @GET("craftsmen/{id}")
    Call<ViewProfileResponse> craftsmenData(@Path("id") int id);

    @GET("craftsmen/comments/{id}")
    Call<List<ReviewsResponse>> craftsmenReviews(@Path("id") int id);

    @GET("craftsmen/ratings/{id}")
    Call<List<ReviewsRatingResponse>> craftsmenRatings(@Path("id") int id);

    @POST("comments")
    Call<AddReviewResponse> addReview (@Body AddReviewRequest addReviewRequest);

    @POST("ratings")
    Call<AddRatingResponse> addRating (@Body AddRatingRequest addRatingRequest);
    @POST("images")
    Call<ImageResponse> addImage (@Body ImageRequest imageRequest);

    @PUT("craftsmen/{id}")
    Call<EditProfileResponse> craftsmenDataChanged(@Path("id") int id,@Body EditProfileRequest editProfileRequest);

    /*@GET("trade-types/{id}")
    Call<List<Obrtnik>> getObrtniki(@Path("id") Long id);*/
    @GET("craftsmen")
    Call<List<Obrtnik>> getObrtniki();

}
