package com.example.bancolombiaapp.services;

import com.example.bancolombiaapp.models.LoginUsernameModel;
import com.example.bancolombiaapp.requests.LoginUsernameRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("auth/auth_username.php")
    Call<LoginUsernameModel> loginUsername(@Body LoginUsernameRequest request);
}
