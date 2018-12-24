package com.docker.moduleplayer.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zhangxindang on 2018/12/24.
 */

@Singleton
public class PlayerRepository {

    private final PlayerService playerService;
    private final AppExecutors appExecutors;


    @Inject
    public PlayerRepository(PlayerService playerService, AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.playerService = playerService;
    }


    public LiveData<Resource<List<BannerVo>>>getBanner() {
        return new NetworkBoundResourceReal<List<BannerVo>, List<BannerVo>>(appExecutors) {



            @Override
            protected void saveCallResult(@NonNull List<BannerVo> item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<BannerVo> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<BannerVo>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<BannerVo>>> createCall() {
                return null;
            }


        }.getAsLiveData();
    }
}
