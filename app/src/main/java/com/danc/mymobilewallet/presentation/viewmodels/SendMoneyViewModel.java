package com.danc.mymobilewallet.presentation.viewmodels;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest;
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
public class SendMoneyViewModel extends ViewModel {

    CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<Event<Resource<SendMoneyRequest>>> sendMoneyLivedata;

    private LoginUseCase loginUseCase;
    private WalletApi walletApi;

    @Inject
    public SendMoneyViewModel(WalletApi walletApi) {
        this.walletApi = walletApi;
        sendMoneyLivedata = new MutableLiveData<>();
    }

    public LiveData<Event<Resource<SendMoneyRequest>>> getLoginResponseLiveData() {
        return sendMoneyLivedata;
    }

    @SuppressLint("CheckResult")
    public void postSendRequest(SendMoneyRequest sendMoneyRequest) {
        disposables.add(RxUtility.compose(walletApi.sendMoney(sendMoneyRequest),
                response -> {
                    sendMoneyLivedata.postValue(new Event(new Resource.Success(response)));
                },
                error -> {

                    if (error instanceof HttpException) {
                        sendMoneyLivedata.postValue(new Event(new Resource.Failure(((HttpException) error).response().errorBody(), false, ((HttpException) error).code())));
                    } else {
                        sendMoneyLivedata.postValue(new Event(new Resource.Failure(null, true, null)));
                    }
                }));
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
