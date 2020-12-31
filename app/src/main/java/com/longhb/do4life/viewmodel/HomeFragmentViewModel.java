package com.longhb.do4life.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.longhb.do4life.model.Post;
import com.longhb.do4life.network.RetrofitModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentViewModel extends ViewModel {
    public MutableLiveData<List<Post>> listPost = new MutableLiveData<>();

    public HomeFragmentViewModel() {
    }

    public void getAllPost(Event event) {
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

    public interface Event {
        void getAllPostError();
    }
}
