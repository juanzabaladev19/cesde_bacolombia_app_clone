package com.example.bancolombiaapp.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static Retrofit retrofit;
    private static final String serverIp = "54.242.111.128";
    private static final String serverPort = "";
    //private static final String baseUrl = "http://" + serverIp + serverPort + "/cesde_backend_bancolombia_app_clone/";
    private static final String baseUrl = "http://" + serverIp + serverPort;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static Retrofit build(){
        return  retrofit;
    }
}
