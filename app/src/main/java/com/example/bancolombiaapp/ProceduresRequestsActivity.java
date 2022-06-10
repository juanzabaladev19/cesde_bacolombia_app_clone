package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bancolombiaapp.databinding.ActivityProceduresRequestsBinding;
import com.example.bancolombiaapp.databinding.BottomBarComponentBinding;

public class ProceduresRequestsActivity extends AppCompatActivity {
    private ActivityProceduresRequestsBinding proceduresRequestsBinding;
    private BottomBarComponentBinding bottomBarComponentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proceduresRequestsBinding = ActivityProceduresRequestsBinding.inflate(getLayoutInflater());
        bottomBarComponentBinding = BottomBarComponentBinding.inflate(getLayoutInflater());
        View view = proceduresRequestsBinding.getRoot();
        setContentView(view);

        proceduresRequestsBinding.bottomBarComponent.iconTramitesLayout.setBackgroundColor(getResources().getColor(R.color.bc_yellow));

        proceduresRequestsBinding.bottomBarComponent.iconHomeLayout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),HomeScreenActivity.class);
            startActivity(intent);
        });


    }
}