package com.danc.mymobilewallet.domain.use_case;

import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest;
import com.danc.mobilewallet.domain.models.Response.SendMoneyResponse;
import com.danc.mymobilewallet.data.remote.WalletApi;

import javax.inject.Inject;

public class SendMoneyUseCase {

    private WalletApi walletApi;

    @Inject
    public SendMoneyUseCase(WalletApi walletApi){
        this.walletApi = walletApi;
    }

//    public SendMoneyResponse invoke(SendMoneyRequest sendMoneyRequest){
//        return walletApi.sendMoney(sendMoneyRequest);
//    }

}
