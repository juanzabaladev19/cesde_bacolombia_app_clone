package com.example.saldosmovimientos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.saldosmovimientos.adapters.TransferAdapter;
import com.example.saldosmovimientos.databinding.ActivityMainBinding;
import com.example.saldosmovimientos.models.TransactionsModel;
import com.example.saldosmovimientos.request.TransactionRequest;
import com.example.saldosmovimientos.services.TransactionsServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private ArrayList<TransactionsModel> transfersArrayList;
    private TransferAdapter transferAdapter;
    private int pageCount = 0;
    private Retrofit retrofit;
    private ArrayList<TransactionsModel> transactionsModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.6/cesde_backend_bancolombia_app_clone/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        transfersArrayList = new ArrayList<>();
        transferAdapter = new TransferAdapter(this,transfersArrayList);
        mainBinding.rvListTransfers.setHasFixedSize(true);
        mainBinding.rvListTransfers.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.rvListTransfers.setAdapter(transferAdapter);
        mainBinding.btnDiaDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listTransfers();
            }
        });
        listTransfers();
    }
    public void listTransfers() {


        //Toast.makeText(this, "valorContador"+pageCount, Toast.LENGTH_SHORT).show();
        TransactionsServices transactionsServices = retrofit.create(TransactionsServices.class);
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setPageCount(pageCount);
        Call<List<TransactionsModel>> transactionUserService = transactionsServices.transactionUser(transactionRequest);
        transactionUserService.enqueue(new Callback<List<TransactionsModel>>() {
            @Override
            public void onResponse(Call<List<TransactionsModel>> call, Response<List<TransactionsModel>> response) {
                if(response.isSuccessful()){
                    List<TransactionsModel> listTransactions = response.body();
                    for(int i = 0; i<listTransactions.size(); i++){
                        transfersArrayList.add(listTransactions.get(i));
                    }
                    transferAdapter.notifyDataSetChanged();
                    pageCount = pageCount + 20;
                    Toast.makeText(MainActivity.this, "pageCount: " + pageCount, Toast.LENGTH_SHORT).show();
                    if(listTransactions.size() > 0){
                        Toast.makeText(MainActivity.this, "" + listTransactions.get(0).getDate(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "No tiene más transacciones", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<List<TransactionsModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onClick (View view){
        Intent intent = new Intent(this, ListTransferActivity.class);
    /*    startActivity(intent);
        pageCount = 0;
        //Toast.makeText(this, "valorContador"+pageCount, Toast.LENGTH_SHORT).show();
        TransactionsServices transactionsServices = retrofit.create(TransactionsServices.class);
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setPageCount(pageCount);
        Call<List<TransactionsModel>> transactionUserService = transactionsServices.transactionUser(transactionRequest);
        transactionUserService.enqueue(new Callback<List<TransactionsModel>>() {
            @Override
            public void onResponse(Call<List<TransactionsModel>> call, Response<List<TransactionsModel>> response) {
                if(response.isSuccessful()){
                    List<TransactionsModel> listTransactions = response.body();
                    Toast.makeText(MainActivity.this, "" + listTransactions.get(0).getDestination_account(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<TransactionsModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

}