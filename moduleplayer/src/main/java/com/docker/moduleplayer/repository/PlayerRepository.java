package com.docker.moduleplayer.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.db.CacheDatabase;
import com.docker.commonlibrary.repository.cache.CacheStrategy;
import com.docker.commonlibrary.repository.BoundResource.NetworkBoundResourceAuto;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.vo.Resource;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;

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
    private final CacheDatabase cacheDatabase;

    @Inject
    public PlayerRepository(PlayerService playerService, AppExecutors appExecutors, CacheDatabase cacheDatabase) {
        super();
        this.appExecutors = appExecutors;
        this.playerService = playerService;
        this.cacheDatabase = cacheDatabase;
    }

    public LiveData<Resource<FeedArticleListData>> getFeedArticle(int page) {
        return new NetworkBoundResourceAuto<FeedArticleListData, FeedArticleListData>(appExecutors, CacheStrategy.FIRST_CACHE_THEN_REQUEST, cacheDatabase, "FeedArticleListData" + page) {

            @NonNull
            @Override
            protected LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> createCall() {
                return playerService.getIndexList(page);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<BannerVo>>> getBanner() {
        return new NetworkBoundResourceAuto<List<BannerVo>, List<BannerVo>>(appExecutors, CacheStrategy.FIRST_CACHE_THEN_REQUEST, cacheDatabase, "banner") {
            @NonNull
            @Override
            protected LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> createCall() {
                return playerService.getBanner();
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData(){
         return new NetworkBoundResourceAuto<List<KnowledgeHierarchyData>,List<KnowledgeHierarchyData>>(appExecutors,CacheStrategy.FIRST_CACHE_THEN_REQUEST,cacheDatabase,"KnowledgeHierarchyData"){
             @NonNull
             @Override
             protected LiveData<ApiResponse<BaseResponse<List<KnowledgeHierarchyData>>>> createCall() {
                 return playerService.getKnowledgeHierarchyData();
             }
         }.asLiveData();
    }


}
