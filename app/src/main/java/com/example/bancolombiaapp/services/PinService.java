package com.example.bancolombiaapp.services;

import com.example.bancolombiaapp.models.PinModel;
import com.example.bancolombiaapp.requests.PinRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PinService {
    @POST("auth/auth_pin.php")
    Call<PinModel> pinUser(@Body PinRequest request);
}
