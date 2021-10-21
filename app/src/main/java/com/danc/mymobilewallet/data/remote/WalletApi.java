package com.danc.mymobilewallet.data.remote;

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

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WalletApi {

    @POST("customers/login")
    Observable<LoginResponse> loginCustomer(@Body LoginRequest loginRequest);

    @POST("accounts/balance")
    Observable<BalanceResponse> getBalance(@Body BalanceRequest balanceRequest);

    @POST("transactions/send-money")
    Observable<SendMoneyResponse> sendMoney(@Body SendMoneyRequest sendMoneyRequest);

    @POST("transactions/last-100-transactions")
    Observable<LastTransactionsResponse> getLast100Transactions(@Body LastTransactionRequest lastTransactionRequest);

    @POST("transactions/mini-statement")
    Observable<MiniStatementResponse> getMiniStatement(@Body MiniStatementRequest miniStatementRequest);

    @GET("transactions/")
    Observable<AllTransactions> getAllTransactions();

}
