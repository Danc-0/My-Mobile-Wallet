package com.danc.mymobilewallet.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.mobilewallet.domain.models.Response.AllTransactionsItem;
import com.danc.mobilewallet.domain.models.Response.MiniStatementResponseItem;
import com.danc.mymobilewallet.databinding.ListItemTransactionBinding;
import com.danc.mymobilewallet.databinding.TransItemBinding;

import java.util.List;

public class MiniTransactionsAdapter extends RecyclerView.Adapter<MiniTransactionsAdapter.TransactionsViewHolder> {

    public List<MiniStatementResponseItem> allTransactionsItems;

    public MiniTransactionsAdapter(List<MiniStatementResponseItem> allTransactionsItems){
        this.allTransactionsItems = allTransactionsItems;
    }


    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TransItemBinding binding = TransItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TransactionsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder holder, int position) {
        MiniStatementResponseItem miniStatementResponseItem = allTransactionsItems.get(position);
        holder.bind(miniStatementResponseItem);

    }

    @Override
    public int getItemCount() {
        return allTransactionsItems.size();
    }

    public class TransactionsViewHolder extends RecyclerView.ViewHolder {

        TransItemBinding binding;

        public TransactionsViewHolder(@NonNull TransItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(MiniStatementResponseItem miniStatementResponseItem){
            binding.balanceAmount.setText(String.valueOf(miniStatementResponseItem.getBalance()));
            binding.customerCode.setText(miniStatementResponseItem.getCustomerId());
            binding.loanAmount.setText(String.valueOf(miniStatementResponseItem.getAmount()));
            binding.transType.setText(miniStatementResponseItem.getTransactionType());
            binding.transCode.setText(miniStatementResponseItem.getTransactionId());

        }

    }

}
