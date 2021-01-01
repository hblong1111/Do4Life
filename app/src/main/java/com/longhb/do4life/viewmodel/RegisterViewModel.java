package com.longhb.do4life.viewmodel;

import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.JsonCreateAccount;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.CheckCreateAccountEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    public void createAccount(JsonCreateAccount user, CheckCreateAccountEvent callback) {
        RetrofitModule.getInstance().createAccount(user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body().booleanValue()) {
                        callback.onCreateSuccess();
                        return;
                    }
                    callback.onCreateError();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onCreateError();
            }
        });
    }
}
