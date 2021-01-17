package com.longhb.do4life.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonScheduleHistory;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.ScheduleHistory;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragmentViewModel extends ViewModel {
    private RetrofitService retrofit;

    private MutableLiveData<List<ScheduleHistory>> list = new MutableLiveData<>();

    private Event callback;

    public HistoryFragmentViewModel() {
        retrofit = RetrofitModule.getInstance();
    }


    public void getProfile(JsonProfile jsonProfile) {
        RetrofitModule.getInstance().getProfile(jsonProfile).enqueue(new Callback<List<ProfileRetrofit>>() {
            @Override
            public void onResponse(Call<List<ProfileRetrofit>> call, Response<List<ProfileRetrofit>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() == 0) {
                        callback.noResponseProfile();
                    }else {
                        callback.onSuccessProfile(response.body());
                    }
                    return;
                }
                callback.onError();
            }

            @Override
            public void onFailure(Call<List<ProfileRetrofit>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public void getScheduleHistoryByProfileId(JsonScheduleHistory jsonScheduleHistory) {
        retrofit.getScheduleHistoryByProfileId(jsonScheduleHistory).enqueue(new Callback<List<ScheduleHistory>>() {
            @Override
            public void onResponse(Call<List<ScheduleHistory>> call, Response<List<ScheduleHistory>> response) {
                if (response.isSuccessful()) {
                    list.postValue(response.body());
                    if (response.body().size() == 0) {
                        callback.noResponse();
                    } else {
                        callback.onSuccess();
                    }
                    return;
                }
                callback.onError();
            }

            @Override
            public void onFailure(Call<List<ScheduleHistory>> call, Throwable t) {
                callback.onError();
                t.printStackTrace();
            }
        });
    }


    public MutableLiveData<List<ScheduleHistory>> getList() {
        return list;
    }

    public void setCallback(Event callback) {
        this.callback = callback;
    }

    public interface Event {
        void onSuccess();

        void onError();

        void noResponse();

        void noResponseProfile();

        void onSuccessProfile(List<ProfileRetrofit> listProfile);
    }

}
