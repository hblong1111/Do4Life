package com.longhb.do4life.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonShift;
import com.longhb.do4life.model.retrofit.res.Department;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.Shift;
import com.longhb.do4life.network.RetrofitModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivityViewModel extends ViewModel {

    private MutableLiveData<List<Shift>> litShift = new MutableLiveData<>();


    public void getProfile(JsonProfile jsonProfile,EventGetProfile callback) {
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

    public void getAllDepartment(EventGetAllDepartment callback) {
        RetrofitModule.getInstance().getAllDepartment().enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if (response.isSuccessful() && response.body().size() > 0) {
                    callback.onSuccess(response.body());
                    return;
                }
                callback.onError();
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public void getAllShiftByDepartmentId(EventGetAllShift callback, JsonShift jsonShift) {
        RetrofitModule.getInstance().getAllShiftByDepartmentId(jsonShift).enqueue(new Callback<List<Shift>>() {
            @Override
            public void onResponse(Call<List<Shift>> call, Response<List<Shift>> response) {
                if (response.isSuccessful() && response.body().size() > 0) {
                    litShift.postValue(response.body());
                    callback.onSuccess();
                    return;
                }
                callback.onError();
            }

            @Override
            public void onFailure(Call<List<Shift>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public MutableLiveData<List<Shift>> getLitShift() {
        return litShift;
    }

    public interface EventGetAllDepartment {
        void onSuccess(List<Department> body);

        void onError();
    }

    public interface EventGetAllShift {
        void onSuccess();

        void onError();
    }
    public interface EventGetProfile {
        void onSuccess(List<ProfileRetrofit> body);

        void onError();
    }
}
