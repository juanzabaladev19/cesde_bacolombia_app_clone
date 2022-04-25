package com.example.saldosmovimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.saldosmovimientos.databinding.ActivityMainBinding;
import com.example.saldosmovimientos.models.TransactionsModel;
import com.example.saldosmovimientos.request.TransactionRequest;
import com.example.saldosmovimientos.services.TransactionsServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private int pageCount =1;
    private Retrofit retrofit;
    private ArrayList<TransactionsModel> transactionsModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        //transactionsModelArrayList = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3:8080/cesde_backend_bancolombia_app_clone/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mainBinding.btnDiaDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TransferActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClick (View view){
        pageCount = 1;
        //Toast.makeText(this, "valorContador"+pageCount, Toast.LENGTH_SHORT).show();
        TransactionsServices transactionsServices = retrofit.create(TransactionsServices.class);
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setPageCount(pageCount);
        Call<TransactionsModel> transactionUserService = transactionsServices.transactionUser(transactionRequest);
        transactionUserService.enqueue(new Callback<TransactionsModel>() {
            @Override
            public void onResponse(Call<TransactionsModel> call, Response<TransactionsModel> response) {
                //Log.d("RETROFIT",);
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,
                            "llegan los datos"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this, "No llegan los datos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TransactionsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Error en conexion "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}