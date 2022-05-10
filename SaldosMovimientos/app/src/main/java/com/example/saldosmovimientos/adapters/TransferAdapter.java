package com.example.saldosmovimientos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saldosmovimientos.databinding.ActivityMainBinding;
import com.example.saldosmovimientos.databinding.TransactionItemBinding;
import com.example.saldosmovimientos.entities.TransferEntity;
import com.example.saldosmovimientos.models.TransactionsModel;

import java.util.ArrayList;
import java.util.Date;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder> {
<<<<<<< HEAD

=======
>>>>>>> 44d3e85ae2734c2bd20d9260ebb950500f533637
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
        TransactionsModel transfer = transferArrayList.get(position);
        holder.itemBinding.tvMonto.setText(String.valueOf(transfer.getAmount()));
        //holder.mainBinding.tvFecha.setText(Date.from(transfer.getDate());
        holder.itemBinding.tvTipoCuenta.setText(transfer.getType());
    }

    @Override
    public int getItemCount() {
        return transferArrayList.size();
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder {
        TransactionItemBinding itemBinding;
        public TransferViewHolder(@NonNull TransactionItemBinding  itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
