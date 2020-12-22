package com.longhb.do4life.network;

import com.longhb.do4life.model.JsonProfile;
import com.longhb.do4life.model.MyAccount;
import com.longhb.do4life.model.Post;
import com.longhb.do4life.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("/createAccount")
    Call<Boolean> createAccount(@Body User user);

    @POST("/getAccountById")
    Call<List<MyAccount>> checkLogin(@Body User user);

    @POST("/updateAccount")
    Call<Boolean> updateAccount(@Body JsonProfile jsonProfile);

    @GET("/getAllPost")
    Call<List<Post>> getAllPost();
}
