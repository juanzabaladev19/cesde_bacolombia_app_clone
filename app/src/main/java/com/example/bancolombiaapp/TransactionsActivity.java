package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bancolombiaapp.databinding.ActivityTransactionsBinding;
import com.example.bancolombiaapp.databinding.BottomBarComponentBinding;

public class TransactionsActivity extends AppCompatActivity {

    private ActivityTransactionsBinding transactionsBinding;
    private BottomBarComponentBinding bottomBarComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transactionsBinding=ActivityTransactionsBinding.inflate(getLayoutInflater());
        bottomBarComponent=BottomBarComponentBinding.inflate(getLayoutInflater());
        View view=transactionsBinding.getRoot();
        setContentView(view);
        transactionsBinding.bottomBarComponent.iconTransactionLayout.setBackgroundColor(getResources().getColor(R.color.bc_yellow));
        transactionsBinding.bottomBarComponent.iconHomeLayout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),HomeScreenActivity.class);
            startActivity(intent);
        });
    }
}