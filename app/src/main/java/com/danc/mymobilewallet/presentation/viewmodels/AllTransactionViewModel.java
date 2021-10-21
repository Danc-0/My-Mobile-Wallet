package com.danc.mymobilewallet.presentation.viewmodels;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danc.mobilewallet.domain.models.Request.LastTransactionRequest;
import com.danc.mobilewallet.domain.models.Request.LoginRequest;
import com.danc.mobilewallet.domain.models.Response.AllTransactions;
import com.danc.mobilewallet.domain.models.Response.LoginResponse;
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
public class AllTransactionViewModel extends ViewModel {

    CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<Event<Resource<AllTransactions>>> allTransResponseMutableLiveData;

    private LoginUseCase loginUseCase;
    private WalletApi walletApi;

    @Inject
    public AllTransactionViewModel(WalletApi walletApi) {
        this.walletApi = walletApi;
        allTransResponseMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<Event<Resource<AllTransactions>>> getLastTransactionResponseLiveData() {
        return allTransResponseMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void getAllTransactionsRequest() {
        disposables.add(RxUtility.compose(walletApi.getAllTransactions(),
                response -> {
                    allTransResponseMutableLiveData.postValue(new Event(new Resource.Success(response)));
                },
                error -> {

                    if (error instanceof HttpException) {
                        allTransResponseMutableLiveData.postValue(new Event(new Resource.Failure(((HttpException) error).response().errorBody(), false, ((HttpException) error).code())));
                    } else {
                        allTransResponseMutableLiveData.postValue(new Event(new Resource.Failure(null, true, null)));
                    }
                }));
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
