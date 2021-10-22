package com.danc.mymobilewallet.presentation.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danc.mobilewallet.domain.models.Request.LastTransactionRequest;
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponse;
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponseItem;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentTransactionsBinding;
import com.danc.mymobilewallet.presentation.adapter.TransactionsAdapter;
import com.danc.mymobilewallet.presentation.viewmodels.LastTransactionViewModel;
import com.danc.mymobilewallet.utils.Resource;

public class TransactionFragment extends Fragment {

    public LastTransactionViewModel transactionViewModel;
    FragmentTransactionsBinding binding;
    TransactionsAdapter adapter;
    double totalValue;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transactions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentTransactionsBinding.bind(view);
        transactionViewModel = new ViewModelProvider(getActivity()).get(LastTransactionViewModel.class);

        bundle = getArguments();
        LoginResponse loginResponse = bundle.getParcelable("LoginResponse");

        LastTransactionRequest lastTransactionRequest = new LastTransactionRequest(
                loginResponse.getCustomerID()
        );

        getLastTransactions(lastTransactionRequest);

        binding.backArrow.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

    }

    public void getLastTransactions(LastTransactionRequest lastTransactionRequest) {
        transactionViewModel.getLast100Transactions(lastTransactionRequest);

        transactionViewModel.getLastTransactionResponseLiveData().observe(getViewLifecycleOwner(), resourceEvent -> {
            if (!resourceEvent.getHasBeenHandled()) {
                Resource<LastTransactionsResponse> lastTransactionsResponseResource = resourceEvent.getContentIfNotHandled();

                if (lastTransactionsResponseResource instanceof Resource.Success) {
                    LastTransactionsResponse lastTransactionsResponse = ((Resource.Success<LastTransactionsResponse>) lastTransactionsResponseResource).getValue();

                    if (lastTransactionsResponse == null || lastTransactionsResponse.isEmpty()) {
                        binding.noData.setVisibility(View.VISIBLE);
                        binding.amountTotal.setVisibility(View.INVISIBLE);
                        binding.totals.setVisibility(View.INVISIBLE);
                    } else {

                        adapter = new TransactionsAdapter(lastTransactionsResponse);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        binding.rvTransactions.setAdapter(adapter);
                        binding.rvTransactions.setLayoutManager(linearLayoutManager);
                        for (LastTransactionsResponseItem lastTransactionsResponseItems : lastTransactionsResponse) {
                            totalValue += lastTransactionsResponseItems.getAmount();
                        }

                        binding.amountTotal.setText(String.valueOf(totalValue));
                    }
                }
            }
        });
    }
}
