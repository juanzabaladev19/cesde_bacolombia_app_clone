package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TransferActionActivity extends AppCompatActivity {

    ImageButton Boton;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_action);

        Boton= (ImageButton)findViewById(R.id.ImgBoton);
        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransferActionActivity.this,DestinationProductActivity.class));
            }
        });

    }
}