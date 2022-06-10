package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DestinationProductActivity extends AppCompatActivity {

    ImageButton boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_product);

        boton=(ImageButton) findViewById(R.id.ImgAtras);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DestinationProductActivity.this,TransferActionActivity.class));
            }
        });
    }
}