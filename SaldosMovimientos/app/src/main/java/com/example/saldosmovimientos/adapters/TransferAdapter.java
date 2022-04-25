package com.example.saldosmovimientos.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder > {
    @NonNull
    @Override
    public TransferAdapter.TransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.TransferViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder {
        public TransferViewHolder(@NonNull  itemView) {
            super(itemView);
        }
    }
}
