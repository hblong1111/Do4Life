package com.longhb.do4life.viewmodel;

import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.MyAccount;
import com.longhb.do4life.model.User;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.CheckLoginEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public void checkLogin(String username, String password, CheckLoginEvent callback) {
        RetrofitModule.getInstance().checkLogin(new User(username, password)).enqueue(new Callback<List<MyAccount>>() {
            @Override
            public void onResponse(Call<List<MyAccount>> call, Response<List<MyAccount>> response) {
                if (response.isSuccessful() && response.body().size() != 0) {
                    callback.onLoginSuccess(response.body().get(0).id);
                    return;
                }

                callback.onLoginError();
            }

            @Override
            public void onFailure(Call<List<MyAccount>> call, Throwable t) {
                callback.onLoginError();
            }
        });
    }

}
