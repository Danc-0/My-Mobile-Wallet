package com.danc.mymobilewallet.domain.use_case;

import com.danc.mobilewallet.domain.models.Request.LastTransactionRequest;
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponse;
import com.danc.mymobilewallet.data.remote.WalletApi;

import javax.inject.Inject;

public class LastTransactionUseCase {

    public WalletApi walletApi;

    @Inject
    public LastTransactionUseCase(WalletApi walletApi){
        this.walletApi = walletApi;
    }

//    public LastTransactionsResponse invoke(LastTransactionRequest lastTransactionRequest){
//        return walletApi.getLast100Transactions(lastTransactionRequest);
//    }

}
