package com.example.obrtniki;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    /*@GET("trade-types/{id}")
    Call<List<Obrtnik>> getObrtniki(@Path("id") Long id);*/
    @GET("craftsmen")
    Call<List<Obrtnik>> getObrtniki();
}
