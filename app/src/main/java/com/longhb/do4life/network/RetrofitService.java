package com.longhb.do4life.network;

import com.longhb.do4life.model.JsonProfile;
import com.longhb.do4life.model.MyAccount;
import com.longhb.do4life.model.Post;
import com.longhb.do4life.model.retrofit.JsonCheckLogin;
import com.longhb.do4life.model.retrofit.JsonCreateAccount;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    //Đăng ký tài khoản
    @POST("/createAccount")
    Call<Boolean> createAccount(@Body JsonCreateAccount user);

    @POST("/getAccount")
    Call<MyAccount> checkLogin(@Body JsonCheckLogin user);

    @POST("/updateCMNDAccount")
    Call<Boolean> updateAccount(@Body JsonProfile jsonProfile);

    @POST("/getAccountById")
    Call<MyAccount> getAccountById(@Body JsonProfile jsonProfile);

    @GET("/getAllPost")
    Call<List<Post>> getAllPost();
}
