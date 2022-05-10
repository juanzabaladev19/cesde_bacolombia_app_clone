package com.example.bancolombiaapp.services;

import com.example.bancolombiaapp.models.TransactionsModel;
import com.example.bancolombiaapp.requests.TransactionRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransactionsServices {
    @POST("transactions/transaction.php")
    Call<List<TransactionsModel>> transactionUser(@Body TransactionRequest transactionRequest);
}
