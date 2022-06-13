package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.bancolombiaapp.databinding.ActivityExploreBinding;
import com.example.bancolombiaapp.databinding.BottomBarComponentBinding;

public class ExploreActivity extends AppCompatActivity {
    private ActivityExploreBinding exploreBinding;
    private BottomBarComponentBinding bottomBarComponentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exploreBinding = ActivityExploreBinding.inflate(getLayoutInflater());
        bottomBarComponentBinding = BottomBarComponentBinding.inflate(getLayoutInflater());
        View view = exploreBinding.getRoot();
        setContentView(view);
        exploreBinding.bottomBarComponent.iconExploreLayout.setBackgroundColor(getResources().getColor(R.color.bc_yellow));

        exploreBinding.bottomBarComponent.iconHomeLayout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),HomeScreenActivity.class);
            startActivity(intent);
        });
    }
}