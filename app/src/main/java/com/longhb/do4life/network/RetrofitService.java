package com.longhb.do4life.network;

import com.longhb.do4life.model.retrofit.json.JsonProfile;
import com.longhb.do4life.model.retrofit.json.JsonShift;
import com.longhb.do4life.model.retrofit.json.JsonUpdateCMND;
import com.longhb.do4life.model.retrofit.res.Department;
import com.longhb.do4life.model.retrofit.res.MyAccount;
import com.longhb.do4life.model.retrofit.res.Post;
import com.longhb.do4life.model.retrofit.json.JsonAccount;
import com.longhb.do4life.model.retrofit.res.ProfileRetrofit;
import com.longhb.do4life.model.retrofit.res.Shift;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    //Tài khoản
    @POST("/createAccount")
    Call<Boolean> createAccount(@Body JsonAccount user);

    @POST("/getAccount")
    Call<MyAccount> checkLogin(@Body JsonAccount user);

    @POST("/updateCMNDAccount")
    Call<Boolean> updateAccount(@Body JsonUpdateCMND jsonUpdateCMND);

    @POST("/getAccountById")
    Call<MyAccount> getAccountById(@Body JsonAccount jsonProfile);

    //Hồ sơ
    @POST("/createProfile")
    Call<Boolean> createProfile(@Body JsonProfile jsonProfile);

    @POST("/getProfile")
    Call<List<ProfileRetrofit>> getProfile(@Body JsonProfile jsonProfileAcc);

    @POST("/updateProfile")
    Call<Boolean> updateProfile(@Body JsonProfile jsonProfileIDProfile);

    @POST("/deleteProfile")
    Call<Boolean> deleteProfile(@Body JsonProfile jsonProfileIDProfile);

    //Bài viết
    @GET("/getAllPost")
    Call<List<Post>> getAllPost();

    //Khoa khám
    @GET("/getAllDepartment")
    Call<List<Department>> getAllDepartment();

    //Ca khám
    @POST("/getAllShiftByDepartmentId")
    Call<List<Shift>> getAllShiftByDepartmentId( @Body JsonShift jsonShift);
}
