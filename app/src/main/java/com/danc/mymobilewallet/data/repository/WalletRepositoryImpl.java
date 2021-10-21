package com.danc.mymobilewallet.data.repository;

import com.danc.mobilewallet.domain.models.Request.BalanceRequest;
import com.danc.mobilewallet.domain.models.Request.LastTransactionRequest;
import com.danc.mobilewallet.domain.models.Request.LoginRequest;
import com.danc.mobilewallet.domain.models.Request.MiniStatementRequest;
import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest;
import com.danc.mobilewallet.domain.models.Response.AllTransactions;
import com.danc.mobilewallet.domain.models.Response.BalanceResponse;
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponse;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
import com.danc.mobilewallet.domain.models.Response.MiniStatementResponse;
import com.danc.mobilewallet.domain.models.Response.SendMoneyResponse;
import com.danc.mymobilewallet.domain.repository.WalletRepository;

import javax.inject.Inject;

public class WalletRepositoryImpl implements WalletRepository {

    @Inject
    public WalletRepositoryImpl(){

    }

    @Override
    public LoginResponse customerLogin(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public BalanceResponse getCustomerBalance(BalanceRequest balanceRequest) {
        return null;
    }

    @Override
    public SendMoneyResponse sendMoney(SendMoneyRequest sendMoneyRequest) {
        return null;
    }

    @Override
    public LastTransactionsResponse lastTransactions(LastTransactionRequest lastTransactionRequest) {
        return null;
    }

    @Override
    public MiniStatementResponse miniStatement(MiniStatementRequest miniStatementRequest) {
        return null;
    }

    @Override
    public AllTransactions allTransactions() {
        return null;
    }
}
