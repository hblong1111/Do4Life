package com.longhb.do4life.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentViewModel extends ViewModel {

    private MutableLiveData<List<ProfileRetrofit>> listProfile = new MutableLiveData<>();

    public MutableLiveData<List<ProfileRetrofit>> getListProfile() {
        return listProfile;
    }

    public void createProfile(JsonProfile jsonProfile, EventCreate callback) {
        RetrofitModule.getInstance().createProfile(jsonProfile).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public void getProfile(JsonProfile jsonProfile) {
        RetrofitModule.getInstance().getProfile(jsonProfile).enqueue(new Callback<List<ProfileRetrofit>>() {
            @Override
            public void onResponse(Call<List<ProfileRetrofit>> call, Response<List<ProfileRetrofit>> response) {
                listProfile.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProfileRetrofit>> call, Throwable t) {

            }
        });
    }

    public void updateProfile(JsonProfile jsonProfile) {
        RetrofitModule.getInstance().updateProfile(jsonProfile).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("hblong", "ProfileFragmentViewModel | onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("hblong", " | onFailure: ", t);
            }
        });
    }

    public void deleteProfile(JsonProfile jsonProfile,EventCreate callback) {
        RetrofitModule.getInstance().deleteProfile(jsonProfile).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public interface EventCreate {
        void onSuccess(boolean res);

        void onError();
    }
}
