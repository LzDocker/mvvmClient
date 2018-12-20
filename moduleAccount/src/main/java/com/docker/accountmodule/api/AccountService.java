package com.docker.accountmodule.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.docker.accountmodule.vo.BannerVo;
import com.docker.accountmodule.vo.LoginVo;
import com.docker.accountmodule.vo.RegisterVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by zhangxindang on 2018/10/22.
 */

public interface AccountService {

    @GET("banner/json")
    LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> getBanners();

    @POST("user/register")
    @FormUrlEncoded
    LiveData<ApiResponse<BaseResponse<LoginVo>>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    @POST("user/login")
    @FormUrlEncoded
    LiveData<ApiResponse<BaseResponse<LoginVo>>> login(@Field("username") String username, @Field("password") String password);

    @GET("lg/collect/list/0/json")
    LiveData<ApiResponse<BaseResponse<String>>> get();

}
