package com.longhb.do4life.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonSchedule;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.Schedule;
import com.longhb.do4life.network.RetrofitModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamScheduleFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Schedule>> listSchedule = new MutableLiveData<>();

    public void getScheduleByProfileId(JsonSchedule json,EventGetScheduleByProfileId callback){
        RetrofitModule.getInstance().getScheduleByProfileId(json).enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                if (response.isSuccessful()){
                    listSchedule.postValue(response.body());
                    callback.onSuccess();
                    return;
                }
                callback.onError();
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public void getProfile(JsonProfile jsonProfile, ScheduleActivityViewModel.EventGetProfile callback) {
        RetrofitModule.getInstance().getProfile(jsonProfile).enqueue(new Callback<List<ProfileRetrofit>>() {
            @Override
            public void onResponse(Call<List<ProfileRetrofit>> call, Response<List<ProfileRetrofit>> response) {
                if (response.body().size()>0){
                    callback.onSuccess(response.body());
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


    public MutableLiveData<List<Schedule>> getListSchedule() {
        return listSchedule;
    }

    public interface EventGetScheduleByProfileId{
        void onSuccess();
        void onError();
    }
}
