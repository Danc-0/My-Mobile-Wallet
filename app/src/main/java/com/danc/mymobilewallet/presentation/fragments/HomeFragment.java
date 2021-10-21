package com.danc.mymobilewallet.presentation.fragments;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    Bundle bundle;
    LoginResponse loginResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentHomeBinding.bind(view);
        bundle = getArguments();
        loginResponse = bundle.getParcelable("LoginResponse");
        binding.tvDetails.setText("Hey," +" " + loginResponse.getCustomerName() + "\nWhat would you like to do?");

        bundle.putParcelable("LoginResponse", loginResponse);
        binding.customerBalance.setOnClickListener(view1 -> {
            Navigation.findNavController(requireView()).navigate(R.id.to_balanceFragment, bundle);

        });

        binding.sendCash.setOnClickListener (view1 -> {
            findNavController(requireView()).navigate(R.id.to_sendMoneyFragment2, bundle);
        });

        binding.statement.setOnClickListener(view1 -> {
            findNavController(requireView()).navigate(R.id.to_view_miniStatementFragment, bundle);
        });

        binding.lastTransaction.setOnClickListener(view1 ->  {
            findNavController(requireView()).navigate(R.id.to_last_transactionsFragment);
        });

        binding.userProfile.setOnClickListener(view1 -> {
            findNavController(requireView()).navigate(R.id.to_profileFragment, bundle);
        });

        binding.logOut.setOnClickListener(view1 -> {
            findNavController(requireView()).popBackStack(R.id.loginFragment, false);
            Toast.makeText(getContext(), "Logging you out", Toast.LENGTH_SHORT).show();
        });

    }
}
