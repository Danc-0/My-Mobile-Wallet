package com.danc.mymobilewallet.presentation.viewmodels;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danc.mobilewallet.domain.models.Request.BalanceRequest;
import com.danc.mobilewallet.domain.models.Request.LoginRequest;
import com.danc.mobilewallet.domain.models.Response.BalanceResponse;
import com.danc.mymobilewallet.data.remote.WalletApi;
import com.danc.mymobilewallet.domain.use_case.LoginUseCase;
import com.danc.mymobilewallet.utils.Event;
import com.danc.mymobilewallet.utils.Resource;
import com.danc.mymobilewallet.utils.RxUtility;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

@HiltViewModel
public class BalanceViewModel extends ViewModel {

    CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<Event<Resource<BalanceResponse>>> balanceLiveData;

    private LoginUseCase loginUseCase;
    private WalletApi walletApi;

    @Inject
    public BalanceViewModel(WalletApi walletApi) {
        this.walletApi = walletApi;
        balanceLiveData = new MutableLiveData<>();
    }

    public LiveData<Event<Resource<BalanceResponse>>> getBalanceResponseLiveData() {
        return balanceLiveData;
    }

    @SuppressLint("CheckResult")
    public void postBalanceRequest(BalanceRequest balanceRequest) {
        disposables.add(RxUtility.compose(walletApi.getBalance(balanceRequest),
                response -> {
                    balanceLiveData.postValue(new Event(new Resource.Success(response)));
                },
                error -> {

                    if (error instanceof HttpException) {
                        balanceLiveData.postValue(new Event(new Resource.Failure(((HttpException) error).response().errorBody(), false, ((HttpException) error).code())));
                    } else {
                        balanceLiveData.postValue(new Event(new Resource.Failure(null, true, null)));
                    }
                }));
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
