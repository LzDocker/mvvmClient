package com.docker.moduleplayer.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.util.Log;
import android.view.View;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.base.BaseViewModel;
import com.docker.commonlibrary.bind.recycleviewbind.ItemViewArg;
import com.docker.commonlibrary.viewmodel.CommonVmCallBack;
import com.docker.commonlibrary.vo.Resource;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.repository.PlayerRepository;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;
import com.docker.moduleplayer.vo.NavigationListData;
import com.docker.moduleplayer.vo.ProjectClassifyData;
import com.docker.moduleplayer.vo.ProjectListData;
import com.docker.moduleplayer.vo.WxAuthor;
import com.docker.moduleplayer.BR;
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


    public final ObservableField<String> bannerUrl = new ObservableField<>();

    public void click(View view) {
        bannerUrl.set("111");
    }

    public final MutableLiveData<KnowledgeHierarchyData> knowEnterMessage = new MutableLiveData<>();

    public void knowItemClick(KnowledgeHierarchyData item) {
        Log.d("sss", "knowItemClick: ------------------"+item);
        knowEnterMessage.setValue(item);
    }

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

    private final MutableLiveData<Integer> bannerPm = new MutableLiveData<>();
    public final LiveData<Resource<List<BannerVo>>> bannerData = Transformations.switchMap(bannerPm, new Function<Integer, LiveData<Resource<List<BannerVo>>>>() {
        @Override
        public LiveData<Resource<List<BannerVo>>> apply(Integer input) {
            return playerRepository.getBanner();
        }
    });
//    public void getBanner(Integer integer){
//        bannerPm.setValue(integer);
//    }

    public final MediatorLiveData<Resource<List<BannerVo>>> bannerMData = new MediatorLiveData<>();
    public LiveData<Resource<List<BannerVo>>> bannerLLData = bannerMData;


    /*
     *
     * VM层 数据处理和提供给Livedata给UI层
     *
     * */
    public void getBanner() {
        LiveData<Resource<List<BannerVo>>> bannerLData = playerRepository.getBanner();
        bannerMData.addSource(bannerLData, newdata -> {

            new CommonVmCallBack<List<BannerVo>>() {

                @Override
                public void onBusinessError(Resource<List<BannerVo>> resource) {

                }

                @Override
                public void onNetworkError(Resource<List<BannerVo>> resource) {

                }

                @Override
                public void onLoading(Resource<List<BannerVo>> resource) {

                }

                @Override
                public void onComplete() {
                    super.onComplete();
                }

                @Override
                public void onComplete(Resource<List<BannerVo>> resource) {
                    super.onComplete(resource);
                    Log.d("sss", "getBanner: " + resource);
                    bannerUrl.set(resource.data.get(0).getUrl());
                }
            }.setResource(newdata);

            bannerMData.setValue(newdata);  // 此处setvalue activity中的数据才会刷新
        });
    }

    public LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getIndexList(int num) {
        return service.getIndexList(num);
    }

//    public LiveData<ApiResponse<BaseResponse<List<KnowledgeHierarchyData>>>> getKnowledgeHierarchyData() {
//        return service.getKnowledgeHierarchyData();
//    }

    public final MediatorLiveData<Resource<List<KnowledgeHierarchyData>>> KnowledgeHierarchyMData = new MediatorLiveData<>();
    public LiveData<Resource<List<KnowledgeHierarchyData>>> KnowledgeHierarchyLLData = KnowledgeHierarchyMData;


    public final ObservableList<KnowledgeHierarchyData> knowItems = new ObservableArrayList<>();
    public  ItemViewArg.ItemViewSelector<KnowledgeHierarchyData> KnowitemBinding = new ItemViewArg.ItemViewSelector<KnowledgeHierarchyData>() {
        @Override
        public void select(ItemViewArg.ItemView itemView, int position, KnowledgeHierarchyData item) {
            Log.d("sss", "select: ----------------");
            itemView.set(BR.item,R.layout.moduleplayer_konwledge_item);
            itemView.setEventBindingVariable(BR.viewModel,PlayerhomeViewModel.this);
        }
        @Override
        public int viewTypeCount() {
            return 1;
        }
    };
    public void getKnowledgeHierarchyData() {
        LiveData<Resource<List<KnowledgeHierarchyData>>> KnowledgeHierarchyDataLV = playerRepository.getKnowledgeHierarchyData();
        KnowledgeHierarchyMData.addSource(KnowledgeHierarchyDataLV, newdata -> {
            new CommonVmCallBack<List<KnowledgeHierarchyData>>() {

                @Override
                public void onBusinessError(Resource<List<KnowledgeHierarchyData>> resource) {

                }

                @Override
                public void onNetworkError(Resource<List<KnowledgeHierarchyData>> resource) {

                }

                @Override
                public void onLoading(Resource<List<KnowledgeHierarchyData>> resource) {

                }

                @Override
                public void onComplete(Resource<List<KnowledgeHierarchyData>> resource) {
                    super.onComplete(resource);
                    Log.d("sss", "onComplete: KnowledgeHierarchyData"+resource);
                    knowItems.addAll(resource.data);

                }
            }.setResource(newdata);
            KnowledgeHierarchyMData.setValue(newdata);
        });
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
