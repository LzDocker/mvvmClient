package com.docker.commonlibrary.vo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.docker.commonlibrary.vo.Status.BUSSINESSERROR;
import static com.docker.commonlibrary.vo.Status.ERROR;
import static com.docker.commonlibrary.vo.Status.LOADING;
import static com.docker.commonlibrary.vo.Status.SUCCESS;

/**
 * Created by zhangxindang on 2018/12/24.
 */

public class Resource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable String msg,@Nullable T data) {
        return new Resource<>(LOADING, data, msg);
    }

    public static <T> Resource<T> bussinessError(String msg, @Nullable T data) {
        return new Resource<>(BUSSINESSERROR, data, msg);
    }
}
