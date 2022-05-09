package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
    }

    public void onClick(View view){
        Intent miIntent= new Intent(TransferActivity. this, MainActivity.class);
        startActivity(miIntent);
    }
}