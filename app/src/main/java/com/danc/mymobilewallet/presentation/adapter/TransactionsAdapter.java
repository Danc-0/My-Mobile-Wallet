package com.danc.mymobilewallet.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.mobilewallet.domain.models.Response.AllTransactionsItem;
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponseItem;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.ListItemTransactionBinding;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder> {

    public List<LastTransactionsResponseItem> allTransactionsItems;

    public TransactionsAdapter(List<LastTransactionsResponseItem> allTransactionsItems){
        this.allTransactionsItems = allTransactionsItems;
    }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemTransactionBinding binding = ListItemTransactionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TransactionsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder holder, int position) {
        LastTransactionsResponseItem allTransactionsItem = allTransactionsItems.get(position);
        holder.bind(allTransactionsItem);

    }

    @Override
    public int getItemCount() {
        return allTransactionsItems.size();
    }

    public class TransactionsViewHolder extends RecyclerView.ViewHolder {

        ListItemTransactionBinding binding;

        public TransactionsViewHolder(@NonNull ListItemTransactionBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(LastTransactionsResponseItem allTransactionsItem){
            binding.tvAmount.setText(String.valueOf(allTransactionsItem.getAmount()));
            binding.tvID.setText(allTransactionsItem.getTransactionId());
        }
    }

}
