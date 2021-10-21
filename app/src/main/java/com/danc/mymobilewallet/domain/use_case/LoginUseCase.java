package com.danc.mymobilewallet.domain.use_case;

import com.danc.mobilewallet.domain.models.Request.LoginRequest;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mymobilewallet.data.remote.WalletApi;

import javax.inject.Inject;

public class LoginUseCase {

    private WalletApi walletApi;

    @Inject
    public LoginUseCase(WalletApi walletApi){
        this.walletApi = walletApi;
    }

//    public LoginResponse invoke(LoginRequest loginRequest){
//        return walletApi.loginCustomer(loginRequest);
//    }

}
