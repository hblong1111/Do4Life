package com.longhb.do4life.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.json.JsonScheduleHistory;
import com.longhb.do4life.model.retrofit.res.ScheduleHistory;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragmentViewModel extends ViewModel  {
    private RetrofitService retrofit;

    private MutableLiveData<List<ScheduleHistory>> list = new MutableLiveData<>();

    private Event callback;

    public HistoryFragmentViewModel( ) {
        retrofit = RetrofitModule.getInstance();
    }

    public void getScheduleHistoryByProfileId(JsonScheduleHistory jsonScheduleHistory) {
        retrofit.getScheduleHistoryByProfileId(jsonScheduleHistory).enqueue(new Callback<List<ScheduleHistory>>() {
            @Override
            public void onResponse(Call<List<ScheduleHistory>> call, Response<List<ScheduleHistory>> response) {

                if (response.isSuccessful()) {
                    list.postValue(response.body());
                    callback.onSuccess();
                    return;
                }
                callback.onError();
            }

            @Override
            public void onFailure(Call<List<ScheduleHistory>> call, Throwable t) {
                callback.onError();
            }
        });
    }


    public MutableLiveData<List<ScheduleHistory>> getList() {
        return list;
    }

    public interface Event {
        void onSuccess();

        void onError();
    }

}
