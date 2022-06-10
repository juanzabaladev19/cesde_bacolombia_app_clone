package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bancolombiaapp.databinding.ActivityTransferBinding;
import com.example.bancolombiaapp.databinding.BottomBarComponentBinding;

public class TransferActivity extends AppCompatActivity {
    private ActivityTransferBinding transferBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transferBinding=ActivityTransferBinding.inflate(getLayoutInflater());
        View view=transferBinding.getRoot();
        setContentView(view);

    }

    public void onClick(View view){
        Intent miIntent= new Intent(TransferActivity. this, MainActivity.class);
        startActivity(miIntent);
    }
}