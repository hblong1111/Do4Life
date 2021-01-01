package com.longhb.do4life.viewmodel;

import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.res.MyAccount;
import com.longhb.do4life.model.retrofit.json.JsonAccount;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.CheckLoginEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public void checkLogin(JsonAccount jsonAccount, CheckLoginEvent callback) {
        //todo: fix User
        RetrofitModule.getInstance().checkLogin(jsonAccount).enqueue(new Callback<MyAccount>() {
            @Override
            public void onResponse(Call<MyAccount> call, Response<MyAccount> response) {
                if (response.isSuccessful() && response.body().id!=null) {
                    callback.onLoginSuccess(response.body());
                    return;
                }

                callback.onLoginError();
            }

            @Override
            public void onFailure(Call<MyAccount> call, Throwable t) {
                callback.onLoginError();
            }
        });
    }

}
