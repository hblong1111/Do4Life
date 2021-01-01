package com.longhb.do4life.network;

import com.longhb.do4life.model.retrofit.json.JsonUpdateCMND;
import com.longhb.do4life.model.retrofit.res.MyAccount;
import com.longhb.do4life.model.retrofit.res.Post;
import com.longhb.do4life.model.retrofit.json.JsonAccount;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    //Đăng ký tài khoản
    @POST("/createAccount")
    Call<Boolean> createAccount(@Body JsonAccount user);

    @POST("/getAccount")
    Call<MyAccount> checkLogin(@Body JsonAccount user);

    @POST("/updateCMNDAccount")
    Call<Boolean> updateAccount(@Body JsonUpdateCMND jsonUpdateCMND);

    @POST("/getAccountById")
    Call<MyAccount> getAccountById(@Body JsonAccount jsonProfile);

    @GET("/getAllPost")
    Call<List<Post>> getAllPost();
}
