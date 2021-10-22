package com.danc.mymobilewallet.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mobilewallet.domain.models.Response.SendMoneyResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentSendMoneyBinding;
import com.danc.mymobilewallet.presentation.viewmodels.SendMoneyViewModel;
import com.danc.mymobilewallet.utils.Resource;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SendMoneyFragment extends Fragment {

    FragmentSendMoneyBinding binding;
    SendMoneyViewModel sendMoneyViewModel;
    Bundle bundle = new Bundle();
    LoginResponse loginResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentSendMoneyBinding.bind(view);
        sendMoneyViewModel = new ViewModelProvider(getActivity()).get(SendMoneyViewModel.class);

        bundle = getArguments();
        loginResponse = bundle.getParcelable("LoginResponse");

        binding.sendMoney.setOnClickListener(view1 -> {
            validateAccount();
        });


    }

    public void sendMoney(SendMoneyRequest sendMoneyRequest) {
        sendMoneyViewModel.postSendRequest(sendMoneyRequest);

        sendMoneyViewModel.postSendResponseLiveData().observe(getViewLifecycleOwner(), resourceEvent -> {
            if (!resourceEvent.getHasBeenHandled()) {
                Resource<SendMoneyResponse> sendMoneyResponseResource = resourceEvent.getContentIfNotHandled();
                if (sendMoneyResponseResource instanceof Resource.Success) {
                    SendMoneyResponse sendMoneyResponse = ((Resource.Success<SendMoneyResponse>) sendMoneyResponseResource).getValue();
                    if (sendMoneyResponse.getResponse_status() == true) {
                        Toast.makeText(getContext(), "You have successfully sent " + binding.amount.getText().toString() + " to " + binding.account.getText().toString(), Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

    private Boolean validateAccount() {
        if (binding.account.getText().toString().trim().isEmpty()) {
            binding.outlinedTextField.setError("Required Field!");
            binding.account.requestFocus();
            return false;
        } else {
            validateAmount();
        }
        return true;
    }

    private Boolean validateAmount() {
        if (binding.amount.getText().toString().trim().isEmpty()) {
            binding.outlinedTextField1.setError("Required Field!");
            binding.amount.requestFocus();
            return false;
        } else {
            SendMoneyRequest sendMoneyRequest = new SendMoneyRequest(
                    loginResponse.getCustomerAccount(),
                    binding.account.getText().toString(),
                    Integer.parseInt(binding.amount.getText().toString()),
                    loginResponse.getCustomerID()
            );
            sendMoney(sendMoneyRequest);
        }
        return true;
    }
}
