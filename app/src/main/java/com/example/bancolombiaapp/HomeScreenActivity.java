package com.example.bancolombiaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bancolombiaapp.databinding.ActivityHomeScreenBinding;
import com.example.bancolombiaapp.databinding.BottomBarComponentBinding;


public class HomeScreenActivity extends AppCompatActivity {

    private ActivityHomeScreenBinding homeScreenBinding;
    private BottomBarComponentBinding bottomBarComponentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeScreenBinding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        bottomBarComponentBinding = BottomBarComponentBinding.inflate(getLayoutInflater());
        View view = homeScreenBinding.getRoot();
        setContentView(view);



        homeScreenBinding.bottomBarComponent.iconHomeLayout.setBackgroundColor(getResources().getColor(R.color.bc_yellow));
        homeScreenBinding.btnSignIn.setOnClickListener((v) -> {
            Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
            startActivity(intent);
        });
        homeScreenBinding.bottomBarComponent.iconTramitesLayout.setOnClickListener(view1 -> {
            Intent intent =new Intent(getApplicationContext(),ProceduresRequestsActivity.class);
            startActivity(intent);
        });


    }
}