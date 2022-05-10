package com.example.bancolombiaapp.adapters;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bancolombiaapp.databinding.ActivityMovementsBinding;

import com.example.bancolombiaapp.databinding.TransactionItemBinding;
import com.example.bancolombiaapp.models.TransactionsModel;




import java.util.ArrayList;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder> {

    private Context context;
    private ArrayList<TransactionsModel> transferArrayList;
    public TransferAdapter(Context context, ArrayList<TransactionsModel> transferArrayList) {
        this.context = context;
        this.transferArrayList = transferArrayList;
    }

    private TransactionItemBinding itemBinding;
    @NonNull
    @Override
    public TransferAdapter.TransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemBinding = TransactionItemBinding.inflate(LayoutInflater.from(context));
        return new TransferViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.TransferViewHolder holder, int position) {
        TransactionsModel transfer =transferArrayList.get(position);
        holder.itemBinding.tvMonto.setText(String.valueOf(transfer.getAmount()));
        holder.itemBinding.tvFecha.setText(String.valueOf(transfer.getDate()));
        holder.itemBinding.tvTipoCuenta.setText(transfer.getType());
    }

    @Override
    public int getItemCount() {
        return transferArrayList.size();
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder {
        TransactionItemBinding itemBinding;
        public TransferViewHolder(@NonNull TransactionItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding =itemView;
        }
    }


}
