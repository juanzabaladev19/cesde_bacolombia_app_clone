/*
package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bancolombiaapp.adapters.TransferAdapter;
import com.example.bancolombiaapp.databinding.ActivityListTransferBinding;
import com.example.bancolombiaapp.models.TransactionsModel;
import com.example.bancolombiaapp.requests.TransactionRequest;
import com.example.bancolombiaapp.services.TransactionsServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListTransferActivity extends AppCompatActivity {

    private ActivityListTransferBinding listTransferBinding;
    private MainActivity mainActivity;
    private ArrayList<TransactionsModel> transfersArrayList;
    private TransferAdapter transferAdapter;
    private int pageCount = 0;
    private Retrofit retrofit;
    private ArrayList<TransactionsModel> transactionsModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listTransferBinding = ActivityListTransferBinding.inflate(getLayoutInflater());
        View view = listTransferBinding.getRoot();
        setContentView(view);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.6/cesde_backend_bancolombia_app_clone/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        transfersArrayList = new ArrayList<>();
        mainActivity = new MainActivity();
        transferAdapter = new TransferAdapter(this, transfersArrayList);
        listTransferBinding.rvListTransfers.setHasFixedSize(true);
        listTransferBinding.rvListTransfers.setLayoutManager(new LinearLayoutManager(this));
        listTransferBinding.rvListTransfers.setAdapter(transferAdapter);
        listTransferBinding.btnMoreTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { listTransfers();}
        });
        listTransfers();
    }

    public void listTransfers (){
        TransactionsServices transactionsServices = retrofit.create(TransactionsServices.class);
        TransactionRequest transactionRequest = new TransactionRequest();

        Call<List<TransactionsModel>> transactionUserServices =transactionsServices.transactionUser(transactionRequest);
        transactionUserServices.enqueue(new Callback<List<TransactionsModel>>() {
            @Override
            public void onResponse(Call<List<TransactionsModel>> call, Response<List<TransactionsModel>> response) {
              if (response.isSuccessful()){
                  List<TransactionsModel>listTransactions =response.body();
                  for (int i = 0; i<listTransactions.size(); i++){
                        transfersArrayList.add(listTransactions.get(i));
                  }
                transferAdapter.notifyDataSetChanged();
                pageCount = pageCount + 1;
                if (listTransactions.size() > 0){
                    Toast.makeText(ListTransferActivity.this, ""+ listTransactions.get(0).getDate(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ListTransferActivity.this, "No tiene mas transacciones", Toast.LENGTH_SHORT).show();
                }
              }
            }

            @Override
            public void onFailure(Call<List<TransactionsModel>> call, Throwable t) {
                Toast.makeText(ListTransferActivity.this, ""+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}*/
