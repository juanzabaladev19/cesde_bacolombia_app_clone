package com.example.saldosmovimientos.services;

import com.example.saldosmovimientos.models.TransactionsModel;
import com.example.saldosmovimientos.request.TransactionRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransactionsServices {
    @POST("transactions/transaction.php")
    Call<List<TransactionsModel>> transactionUser(@Body TransactionRequest transactionRequest);
}
