package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bancolombiaapp.databinding.ActivityMyProductsBinding;


public class MyProducts extends AppCompatActivity {

    private ActivityMyProductsBinding myProductsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myProductsBinding = ActivityMyProductsBinding.inflate(getLayoutInflater());
        View view = myProductsBinding.getRoot();
        setContentView(view);

        myProductsBinding.btnEnviarDinero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyProducts.this, OriginAccountActivity.class);
                startActivity(i);
            }
        });

    }
}