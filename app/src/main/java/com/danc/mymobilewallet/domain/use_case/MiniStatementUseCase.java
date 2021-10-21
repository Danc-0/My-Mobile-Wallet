package com.danc.mymobilewallet.domain.use_case;

import com.danc.mobilewallet.domain.models.Request.MiniStatementRequest;
import com.danc.mobilewallet.domain.models.Response.MiniStatementResponse;
import com.danc.mymobilewallet.data.remote.WalletApi;

import javax.inject.Inject;

public class MiniStatementUseCase {

    private WalletApi walletApi;

    @Inject
    public MiniStatementUseCase(WalletApi walletApi){
        this.walletApi = walletApi;
    }

//    public MiniStatementResponse invoke(MiniStatementRequest miniStatementRequest){
//        return walletApi.getMiniStatement(miniStatementRequest);
//    }


}
