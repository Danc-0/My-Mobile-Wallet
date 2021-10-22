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
import androidx.navigation.Navigation;

import com.danc.mobilewallet.domain.models.Request.LoginRequest;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mymobilewallet.R;
import com.danc.mymobilewallet.databinding.FragmentLoginBinding;
import com.danc.mymobilewallet.presentation.viewmodels.LoginViewModel;
import com.danc.mymobilewallet.utils.Resource;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.scopes.FragmentScoped;

@AndroidEntryPoint
@FragmentScoped
public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    Context context = getActivity();
    SharedPreferences sharedPref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(view1 -> {
            LoginRequest loginRequest = new LoginRequest(
                    binding.tvAccountNumber.getText().toString(),
                    binding.tvPwd.getText().toString()
            );
            loginUser(loginRequest);

        });
    }

    private void loginUser(LoginRequest loginRequest){
        loginViewModel.postLoginRequest(loginRequest);

        loginViewModel.getLoginResponseLiveData().observe(getViewLifecycleOwner(), resourceEvent -> {
            if (!resourceEvent.getHasBeenHandled()){
                Resource<LoginResponse> loginResponseResource = resourceEvent.getContentIfNotHandled();

                if (loginResponseResource instanceof Resource.Success){
                    LoginResponse loginResponse = ((Resource.Success<LoginResponse>) loginResponseResource).getValue();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("LoginResponse", loginResponse);
                    Navigation.findNavController(requireView()).navigate(R.id.to_homeFragment, bundle);

                }
            }
        });
    }
}
