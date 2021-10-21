package com.danc.mymobilewallet.domain.use_case;

import com.danc.mobilewallet.domain.models.Response.AllTransactions;
import com.danc.mymobilewallet.data.remote.WalletApi;

import javax.inject.Inject;

public class AllTransactionsUseCase {

    public WalletApi walletApi;

    @Inject
    public AllTransactionsUseCase(WalletApi walletApi){
        this.walletApi = walletApi;
    }

//    public AllTransactions invoke() {
//        return walletApi.getAllTransactions();
//    }

}
