package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bancolombiaapp.databinding.ActivityPinImgBinding;

public class PinImgActivity extends AppCompatActivity {

    private ActivityPinImgBinding pinImgBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pinImgBinding = ActivityPinImgBinding.inflate(getLayoutInflater());
        View view = pinImgBinding.getRoot();
        setContentView(view);

        pinImgBinding.pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);

                    return;
                }else{
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                    return;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);

                    return;
                }else{
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.YELLOW);
                    Toast.makeText(PinImgActivity.this, "length:" + editable.length(), Toast.LENGTH_SHORT).show();
                    if(editable.length() ==4){
                        pinImgBinding.btnContinuar.setBackgroundColor(Color.YELLOW);
                        return;
                    }else{
                        pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                        return;
                    }

                }
            }
        });
    }
    public void goToMainActivity(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}