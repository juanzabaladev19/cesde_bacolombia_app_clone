package com.example.saldosmovimientos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.saldosmovimientos.adapters.TransferAdapter;
import com.example.saldosmovimientos.databinding.ActivityListTransferBinding;
import com.example.saldosmovimientos.entities.TransferEntity;

import java.util.ArrayList;

public class ListTransferActivity extends AppCompatActivity {
    private ActivityListTransferBinding listTransferBinding;
    private MainActivity mainActivity;
    private ArrayList<TransferEntity> transfersArrayList;
    private TransferAdapter transferAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listTransferBinding = ActivityListTransferBinding.inflate(getLayoutInflater());
        View view = listTransferBinding.getRoot();
        setContentView(view);
        transfersArrayList = new ArrayList<>();
        mainActivity = new MainActivity();
        transferAdapter = new TransferAdapter(this,transfersArrayList);
        listTransferBinding.rvListTransfers.setHasFixedSize(true);
        listTransferBinding.rvListTransfers.setLayoutManager(new LinearLayoutManager(this));
        listTransferBinding.rvListTransfers.setAdapter(transferAdapter);
        listTransfers();
    }
    public void listTransfers() {

        TransferEntity transferEntity = new TransferEntity();
        //transferEntity.setAmount();
        //transferEntity.setDate();
        //transferEntity.setType();
        //transfersArrayList.add(transferEntity);

        transferAdapter.notifyDataSetChanged();

    }
}