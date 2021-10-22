package com.danc.mymobilewallet.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danc.mobilewallet.domain.models.Request.MiniStatementRequest;
import com.danc.mobilewallet.domain.models.Response.BalanceResponse;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mobilewallet.domain.models.Response.MiniStatementResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentHomeBinding;
import com.danc.mymobilewallet.databinding.FragmentMiniStatementBinding;
import com.danc.mymobilewallet.presentation.adapter.MiniTransactionsAdapter;
import com.danc.mymobilewallet.presentation.viewmodels.MiniStatementViewModel;
import com.danc.mymobilewallet.utils.Resource;

public class MiniStatementFragment extends Fragment {

    FragmentMiniStatementBinding binding;
    private MiniStatementViewModel miniStatementViewModel;
    Bundle bundle = new Bundle();
    MiniTransactionsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mini_statement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMiniStatementBinding.bind(view);
        miniStatementViewModel = new ViewModelProvider(getActivity()).get(MiniStatementViewModel.class);

        bundle = getArguments();
        LoginResponse loginResponse = bundle.getParcelable("LoginResponse");

        MiniStatementRequest request = new MiniStatementRequest(
                loginResponse.getCustomerAccount(),
                loginResponse.getCustomerID()
        );

        miniStatementViewModel.getMiniStatement(request);

        miniStatementViewModel.getMiniStatementResponseLiveData().observe(getViewLifecycleOwner(), resourceEvent -> {
            if (!resourceEvent.getHasBeenHandled()) {
                Resource<MiniStatementResponse> balanceResponseResource = resourceEvent.getContentIfNotHandled();

                if (balanceResponseResource instanceof Resource.Success) {
                    MiniStatementResponse miniStatementResponse = ((Resource.Success<MiniStatementResponse>) balanceResponseResource).getValue();
                    if (miniStatementResponse == null || miniStatementResponse.isEmpty()) {
                        binding.noData.setVisibility(View.VISIBLE);
                    } else {
                        adapter = new MiniTransactionsAdapter(miniStatementResponse);
                        LinearLayoutManager linearLayoutManager =
                                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        binding.rvMiniTrans.setAdapter(adapter);
                        binding.rvMiniTrans.setLayoutManager(linearLayoutManager);
                    }

                }
            }
        });

        binding.backArrow.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

    }
}
