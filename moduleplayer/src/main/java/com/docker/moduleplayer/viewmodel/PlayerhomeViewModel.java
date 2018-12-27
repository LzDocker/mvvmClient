package com.docker.moduleplayer.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.base.BaseViewModel;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.vo.Resource;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.repository.PlayerRepository;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;
import com.docker.moduleplayer.vo.NavigationListData;
import com.docker.moduleplayer.vo.ProjectClassifyData;
import com.docker.moduleplayer.vo.ProjectListData;
import com.docker.moduleplayer.vo.WxAuthor;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by zhangxindang on 2018/12/13.
 */

public class PlayerhomeViewModel extends BaseViewModel {

    @Inject
    PlayerService service;

    @Inject
    public PlayerhomeViewModel() {
    }


    @Inject
    PlayerRepository playerRepository;

    @Inject
    AppExecutors appExecutors;


    private final MutableLiveData<Integer> pagePm = new MutableLiveData();
    public final LiveData<Resource<FeedArticleListData>> ArticleData =
            Transformations.switchMap(pagePm, new Function<Integer, LiveData<Resource<FeedArticleListData>>>() {
                @Override
                public LiveData<Resource<FeedArticleListData>> apply(Integer input) {
                    return playerRepository.getFeedArticle(input);
                }
            });

    public void getArticleIndex(int page) {
        pagePm.setValue(page);
    }


    public LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> getBanner() {
        return service.getBanner();
    }

    public LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getIndexList(int num) {
        return service.getIndexList(num);
    }

    public LiveData<ApiResponse<BaseResponse<List<KnowledgeHierarchyData>>>> getKnowledgeHierarchyData() {
        return service.getKnowledgeHierarchyData();
    }

    public LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getKnowLedgeDetial(int page, int cid) {
        return service.getKnowledgeHierarchyDetailData(page, cid);
    }

    public LiveData<ApiResponse<BaseResponse<List<WxAuthor>>>> getWxAuthorListData() {
        return service.getWxAuthorListData();
    }

    public LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getWxSumData(int id, int page) {
        return service.getWxSumData(id, page);
    }

    public LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getWxSearchSumData(int id, int page, String k) {
        return service.getWxSearchSumData(id, page, k);
    }

    public LiveData<ApiResponse<BaseResponse<List<NavigationListData>>>> getNavigationListData() {
        return service.getNavigationListData();
    }

    public LiveData<ApiResponse<BaseResponse<List<ProjectClassifyData>>>> getProjectClassifyData() {
        return service.getProjectClassifyData();
    }


    public LiveData<ApiResponse<BaseResponse<ProjectListData>>> getProjectListData(int page, int cid) {
        return service.getProjectListData(page, cid);
    }


}
