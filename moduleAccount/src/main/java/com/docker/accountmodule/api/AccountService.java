package com.docker.accountmodule.api;

import android.arch.lifecycle.LiveData;

import com.docker.accountmodule.vo.LoginVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangxindang on 2018/10/22.
 */

public interface AccountService {


    @POST("user/register")
    @FormUrlEncoded
    LiveData<ApiResponse<BaseResponse<LoginVo>>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    @POST("user/login")
    @FormUrlEncoded
    LiveData<ApiResponse<BaseResponse<LoginVo>>> login(@Field("username") String username, @Field("password") String password);


}
