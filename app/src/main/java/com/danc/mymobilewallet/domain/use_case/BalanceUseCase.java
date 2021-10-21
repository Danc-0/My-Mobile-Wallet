package com.danc.mymobilewallet.domain.use_case;

import com.danc.mobilewallet.domain.models.Request.BalanceRequest;
import com.danc.mobilewallet.domain.models.Response.BalanceResponse;
import com.danc.mymobilewallet.data.remote.WalletApi;

import javax.inject.Inject;

public class BalanceUseCase {

    public WalletApi walletApi;

    @Inject
    public BalanceUseCase(WalletApi walletApi){
        this.walletApi = walletApi;
    }

//    private BalanceResponse invoke(BalanceRequest balanceRequest){
//        return walletApi.getBalance(balanceRequest);
//    }

}
