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

import com.danc.mobilewallet.domain.models.Request.MiniStatementRequest;
import com.danc.mobilewallet.domain.models.Response.BalanceResponse;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mobilewallet.domain.models.Response.MiniStatementResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentHomeBinding;
import com.danc.mymobilewallet.presentation.viewmodels.MiniStatementViewModel;
import com.danc.mymobilewallet.utils.Resource;

public class MiniStatementFragment extends Fragment {

    FragmentHomeBinding binding;
    private MiniStatementViewModel miniStatementViewModel;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);
        miniStatementViewModel = new ViewModelProvider(getActivity()).get(MiniStatementViewModel.class);

        bundle = getArguments();

        LoginResponse loginResponse = bundle.getParcelable("LoginResponse");

        MiniStatementRequest request = new MiniStatementRequest(
                loginResponse.getCustomerAccount(),
                loginResponse.getCustomerID()
        );

        miniStatementViewModel.getMiniStatement(request);

        miniStatementViewModel.getMiniStatementResponseLiveData().observe(getViewLifecycleOwner(), resourceEvent -> {
            if (!resourceEvent.getHasBeenHandled()){
                Resource<MiniStatementResponse> balanceResponseResource = resourceEvent.getContentIfNotHandled();

                if (balanceResponseResource instanceof Resource.Success){
                    MiniStatementResponse miniStatementResponse = ((Resource.Success<MiniStatementResponse>) balanceResponseResource).getValue();
                    Log.d("TAG", "onViewCreated: " + miniStatementResponse);

                }
            }
        });

    }
}
