package com.danc.mymobilewallet.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentProfileBinding.bind(view);
        bundle = getArguments();

        LoginResponse loginResponse = bundle.getParcelable("LoginResponse");
        binding.names.setText(loginResponse.getCustomerName());
        binding.email.setText(loginResponse.getEmail());
        binding.customerID.setText(loginResponse.getCustomerID());
        binding.customerAccount.setText(loginResponse.getCustomerAccount());

    }
}
