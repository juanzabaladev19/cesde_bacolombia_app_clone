package com.example.saldosmovimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.saldosmovimientos.databinding.ActivityMainBinding;
import com.example.saldosmovimientos.models.TransactionsModel;
import com.example.saldosmovimientos.request.TransactionRequest;
import com.example.saldosmovimientos.services.TransactionsServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private int pageCount  = 0;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.1/cesde_backend_bancolombia_app_clone/")
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
        pageCount = pageCount + 1;
        Toast.makeText(this, "valorContador"+pageCount, Toast.LENGTH_SHORT).show();
        TransactionsServices transactionsServices = retrofit.create(TransactionsServices.class);
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setPageCount(pageCount);
        Call<TransactionsModel>transactionUserService=transactionsServices.transactionUser(transactionRequest);
        transactionUserService.enqueue(new Callback<TransactionsModel>() {
            @Override
            public void onResponse(Call<TransactionsModel> call, Response<TransactionsModel> response) {
                if(response.isSuccessful()){
                    TransactionsModel transactionResponse = response.body();

                }
            }

            @Override
            public void onFailure(Call<TransactionsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

}