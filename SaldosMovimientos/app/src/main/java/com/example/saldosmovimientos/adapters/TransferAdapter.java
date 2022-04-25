package com.example.saldosmovimientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saldosmovimientos.databinding.ActivityMainBinding;
import com.example.saldosmovimientos.entities.TransferEntity;

import java.util.ArrayList;
import java.util.Date;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder> {
    private Context context;
    private ArrayList<TransferEntity> transferArrayList;
    public TransferAdapter(Context context, ArrayList<TransferEntity> transferArrayList) {
        this.context = context;
        this.transferArrayList = transferArrayList;
    }

    private ActivityMainBinding mainBinding;
    @NonNull
    @Override
    public TransferAdapter.TransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(context));
        return new TransferViewHolder(mainBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.TransferViewHolder holder, int position) {
        TransferEntity transfer = transferArrayList.get(position);
        holder.mainBinding.tvMonto.setText(String.valueOf(transfer.getAmount()));
        //holder.mainBinding.tvFecha.setText(Date.from(transfer.getDate());
        holder.mainBinding.tvTipoCuenta.setText(transfer.getType());
    }

    @Override
    public int getItemCount() {
        return transferArrayList.size();
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder {
        ActivityMainBinding mainBinding;
        public TransferViewHolder(@NonNull ActivityMainBinding  itemView) {
            super(itemView.getRoot());
            this.mainBinding = itemView;
        }
    }
}
