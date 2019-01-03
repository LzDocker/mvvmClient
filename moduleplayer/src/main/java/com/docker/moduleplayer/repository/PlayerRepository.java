package com.docker.moduleplayer.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.repository.BoundResource.CacheStrategy;
import com.docker.commonlibrary.repository.BoundResource.NetworkBoundResource;
import com.docker.commonlibrary.repository.BoundResource.NetworkBoundResourceCopy;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.util.JsonUtil;
import com.docker.commonlibrary.vo.Resource;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.db.PlayerDatabase;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.FeedArticleListData;

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
    PlayerDatabase playerDatabase;


    @Inject
    public PlayerRepository(PlayerService playerService, AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.playerService = playerService;
    }

    public LiveData<Resource<FeedArticleListData>> getFeedArticle(int page) {

        return new NetworkBoundResourceCopy<FeedArticleListData, FeedArticleListData>(appExecutors, CacheStrategy.FIRST_CACHE_THEN_REQUEST) {

            @Override
            protected void saveCallResult(@NonNull FeedArticleListData item) {
                item.setArticleListStr(JsonUtil.listToJson(item.getDatas()));
                playerDatabase.feedArticleDao().insertAll(item);
            }

            @NonNull
            @Override
            protected LiveData<FeedArticleListData> loadFromDb() {
                final MediatorLiveData<FeedArticleListData> feedArticleListDataMediatorLiveData = new MediatorLiveData<>();
                feedArticleListDataMediatorLiveData.addSource(playerDatabase.feedArticleDao().LoadFeedData(page + 1), feedAndArticle -> {
                    if (feedAndArticle != null) {
                        feedAndArticle.setDatas(JsonUtil.jsonToList(feedAndArticle.getArticleListStr(), FeedArticleData.class));
                    }
                    feedArticleListDataMediatorLiveData.setValue(feedAndArticle);
                });
                return feedArticleListDataMediatorLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> createCall() {
                return playerService.getIndexList(page);
            }
        }.asLiveData();

    }


    public LiveData<Resource<List<BannerVo>>> getBanner() {

        return new NetworkBoundResourceCopy<List<BannerVo>, List<BannerVo>>(appExecutors, CacheStrategy.IF_NONE_CACHE_REQUEST) {

            @Override
            protected void saveCallResult(@NonNull List<BannerVo> item) {

            }

            @NonNull
            @Override
            protected LiveData<List<BannerVo>> loadFromDb() {

                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> createCall() {
                return playerService.getBanner();
            }
        }.asLiveData();
    }


}
