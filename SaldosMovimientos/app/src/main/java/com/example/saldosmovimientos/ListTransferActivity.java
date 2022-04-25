package com.example.saldosmovimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.saldosmovimientos.databinding.ActivityListTransferBinding;

public class ListTransferActivity extends AppCompatActivity {
    private ActivityListTransferBinding listTransferBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listTransferBinding = ActivityListTransferBinding.inflate(getLayoutInflater());
        View view = listTransferBinding.getRoot();
        setContentView(view);
    }
    public void listTransfer(){
        MainActivity mainActivity = new MainActivity();
    }
}