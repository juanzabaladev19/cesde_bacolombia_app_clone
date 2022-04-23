package com.example.saldosmovimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TransfenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfencias);
    }

    public void onClick(View view){
        Intent miIntent= new Intent(TransfenciasActivity. this, MainActivity.class);
        startActivity(miIntent);
    }
}