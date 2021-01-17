package com.longhb.do4life.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.retrofit.res.MyAccount;
import com.longhb.do4life.model.retrofit.res.Post;
import com.longhb.do4life.model.retrofit.json.JsonAccount;
import com.longhb.do4life.network.RetrofitModule;
import com.longhb.do4life.utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentViewModel extends ViewModel {
    public MutableLiveData<List<Post>> listPost = new MutableLiveData<>();

    public HomeFragmentViewModel() {
    }

    public void getAccountById(String idAcc,EventGetAcc event) {
        RetrofitModule.getInstance().getAccountById(new JsonAccount(idAcc)).enqueue(new Callback<MyAccount>() {
            @Override
            public void onResponse(Call<MyAccount> call, Response<MyAccount> response) {
                if (response.isSuccessful()) {
                    event.onSuccess(response.body());
                    return;
                }
                event.onError();
            }

            @Override
            public void onFailure(Call<MyAccount> call, Throwable t) {
                t.printStackTrace();
                event.onError();
            }
        });
    }

    public void getAllPost(EventGetAllPost event) {
        RetrofitModule.getInstance().getAllPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body().size() != 0) {
                    listPost.postValue(response.body());
                    return;
                }
                event.getAllPostError();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                event.getAllPostError();

            }
        });
    }


    public void checkChecked(Context context, JsonAccount jsonProfileID, EventGetAccountById callback) {
        ProgressDialog progressDialog = Common.buildDialogLoading(context, "", "Đang tải...");
        RetrofitModule.getInstance().getAccountById(jsonProfileID).enqueue(new Callback<MyAccount>() {
            @Override
            public void onResponse(Call<MyAccount> call, Response<MyAccount> response) {
                callback.getAccountByIdSuccess(response.body().checked);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MyAccount> call, Throwable t) {
                callback.getAccountByIdError();
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    public interface EventGetAcc {
        void onSuccess(MyAccount account);

        void onError();
    }

    public interface EventGetAllPost {
        void getAllPostError();
    }

    public interface EventGetAccountById {
        void getAccountByIdSuccess(boolean checked);

        void getAccountByIdError();
    }
}
