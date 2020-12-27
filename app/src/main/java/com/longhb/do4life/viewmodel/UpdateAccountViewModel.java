package com.longhb.do4life.viewmodel;

import com.longhb.do4life.model.JsonProfile;
import com.longhb.do4life.network.RetrofitModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAccountViewModel {
    public void updateAccount(JsonProfile jsonProfile, UpdateAccountEvent callback) {
        RetrofitModule.getInstance().updateAccount(jsonProfile).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    callback.onUpdateSuccess();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onUpdateError();
            }
        });
    }

      public interface UpdateAccountEvent {
        void onUpdateSuccess();
        void onUpdateError();
    }
}
