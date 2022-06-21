package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TransferActionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_action);

    }

    public void onClick(View v){
        Intent i = new Intent(TransferActionActivity.this, DestinationProductActivity.class);
        startActivity(i);
    }

}