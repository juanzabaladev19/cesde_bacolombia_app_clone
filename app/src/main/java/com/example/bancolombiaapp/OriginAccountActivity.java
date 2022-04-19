package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bancolombiaapp.databinding.ActivityMyProductsBinding;
import com.example.bancolombiaapp.databinding.ActivityOriginAccountBinding;

public class OriginAccountActivity extends AppCompatActivity {

    private ActivityOriginAccountBinding originAccountBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        originAccountBinding = ActivityOriginAccountBinding.inflate(getLayoutInflater());
        View view = originAccountBinding.getRoot();
        setContentView(view);

        originAccountBinding.btnCancelar.setOnClickListener(v -> {
            Intent i = new Intent(OriginAccountActivity.this,MainActivity.class);
            startActivity(i);
        });
    }
}