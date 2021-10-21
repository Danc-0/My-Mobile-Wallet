package com.danc.mymobilewallet.domain.repository;

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

public interface WalletRepository {

    LoginResponse customerLogin(LoginRequest loginRequest);

    BalanceResponse getCustomerBalance(BalanceRequest balanceRequest);

    SendMoneyResponse sendMoney(SendMoneyRequest sendMoneyRequest);

    LastTransactionsResponse lastTransactions(LastTransactionRequest lastTransactionRequest);

    MiniStatementResponse miniStatement(MiniStatementRequest miniStatementRequest);

    AllTransactions allTransactions();

}
