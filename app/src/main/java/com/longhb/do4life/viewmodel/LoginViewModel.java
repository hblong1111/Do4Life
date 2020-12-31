package com.longhb.do4life.viewmodel;

import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.MyAccount;
import com.longhb.do4life.model.retrofit.JsonCheckLogin;
import com.longhb.do4life.model.retrofit.JsonCreateAccount;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.CheckLoginEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public void checkLogin(JsonCheckLogin  jsonCheckLogin, CheckLoginEvent callback) {
        //todo: fix User
        RetrofitModule.getInstance().checkLogin(jsonCheckLogin).enqueue(new Callback<MyAccount>() {
            @Override
            public void onResponse(Call<MyAccount> call, Response<MyAccount> response) {
                if (response.isSuccessful() && response.body().id!=null) {
                    callback.onLoginSuccess(response.body().id);
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
