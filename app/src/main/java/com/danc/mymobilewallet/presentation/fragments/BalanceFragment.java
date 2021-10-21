package com.danc.mymobilewallet.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.danc.mobilewallet.domain.models.Request.BalanceRequest;
import com.danc.mobilewallet.domain.models.Request.LoginRequest;
import com.danc.mobilewallet.domain.models.Response.BalanceResponse;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentBalanceBinding;
import com.danc.mymobilewallet.presentation.viewmodels.BalanceViewModel;
import com.danc.mymobilewallet.utils.Resource;

public class BalanceFragment extends Fragment {

    public FragmentBalanceBinding binding;
    private BalanceViewModel balanceViewModel;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_balance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBalanceBinding.bind(view);
        balanceViewModel = new ViewModelProvider(getActivity()).get(BalanceViewModel.class);

        bundle = getArguments();
        LoginResponse loginResponse = bundle.getParcelable("LoginResponse");

        BalanceRequest balanceRequest = new BalanceRequest(
                loginResponse.getCustomerID()
        );

        getBalance(balanceRequest);

        binding.backArrow.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

    }

    private void getBalance(BalanceRequest loginRequest){
        balanceViewModel.postBalanceRequest(loginRequest);

        balanceViewModel.getBalanceResponseLiveData().observe(getViewLifecycleOwner(), resourceEvent -> {
            if (!resourceEvent.getHasBeenHandled()){
                Resource<BalanceResponse> balanceResponseResource = resourceEvent.getContentIfNotHandled();

                if (balanceResponseResource instanceof Resource.Success){
                    BalanceResponse balanceResponse = ((Resource.Success<BalanceResponse>) balanceResponseResource).getValue();
                    binding.balanceFor.setText("Checking your account balance\n" + balanceResponse.getAccountNo());
                    binding.balance.setText(String.valueOf(balanceResponse.getBalance()));

                }
            }
        });
    }
}
