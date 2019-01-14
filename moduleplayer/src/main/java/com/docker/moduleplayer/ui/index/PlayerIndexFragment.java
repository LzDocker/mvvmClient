package com.docker.moduleplayer.ui.index;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.docker.commonlibrary.adapter.SimpleCommonRecyclerAdapter;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.api.NetBoundCallback;
import com.docker.commonlibrary.api.NetBoundObserver;
import com.docker.commonlibrary.base.BaseFragment;
import com.docker.commonlibrary.vo.Resource;
import com.docker.moduleplayer.BR;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentIndexBinding;
import com.docker.moduleplayer.ui.common.WebActivity;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;
import com.docker.moduleplayer.widget.banner.BannerView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class PlayerIndexFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentIndexBinding> {

    private int page = 0;
    private SimpleCommonRecyclerAdapter<FeedArticleData> mAdapter;
    private FeedArticleListData cacheResult;
    private View header;
    @Inject
    ViewModelProvider.Factory factory;

    public static PlayerIndexFragment getInstance() {
        return new PlayerIndexFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_index;
    }

    @Override
    protected PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this.getActivity(), factory).get(PlayerhomeViewModel.class);
    }

    @Override
    protected void initView(View var) {
        mAdapter = new SimpleCommonRecyclerAdapter<>(R.layout.moduleplayer_item_article_index, BR.item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
//        pagerSnapHelper.attachToRecyclerView(mBinding.get().recycle);
        mBinding.get().recycle.setLayoutManager(linearLayoutManager);
        mBinding.get().recycle.setAdapter(mAdapter);
        mBinding.get().recycle.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mBinding.get().recycle.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        header = LayoutInflater.from(this.getActivity()).inflate(R.layout.moduleplayer_header_banner, (ViewGroup) this.getActivity().findViewById(android.R.id.content), false);
        mBinding.get().recycle.addHeaderView(header);
        mAdapter.setOnItemClickListener((view, position) -> enterDetial(mAdapter.getmObjects().get(position - 2).getLink()));
        mBinding.get().recycle.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mBinding.get().recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                mViewModel.getArticleIndex(page);
            }

            @Override
            public void onLoadMore() {
                mViewModel.getArticleIndex(page);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.get().setViewmodel(mViewModel);
        mBinding.get().recycle.refresh();
        mViewModel.getBanner();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mViewModel.bannerLLData.observe(this, new NetBoundObserver<>(new NetBoundCallback<List<BannerVo>>() {
            @Override
            public void onBusinessError(Resource<List<BannerVo>> resource) {

            }

            @Override
            public void onNetworkError(Resource<List<BannerVo>> resource) {

            }

            @Override
            public void onComplete(Resource<List<BannerVo>> resource) {
                super.onComplete(resource);

            }
        }));
        mViewModel.ArticleData.observe(this, new NetBoundObserver<>(new NetBoundCallback<FeedArticleListData>() {
            @Override
            public void onBusinessError(Resource<FeedArticleListData> resource) {
                cacheResult = null;
            }

            @Override
            public void onNetworkError(Resource<FeedArticleListData> resource) {
                cacheResult = null;
            }

            @Override
            public void onComplete() {
                super.onComplete();
                mBinding.get().recycle.refreshComplete();
                mBinding.get().recycle.loadMoreComplete();
                Log.d("sss", "onComplete: ----page -----" + page);
            }

            @Override
            public void onComplete(Resource<FeedArticleListData> resource) {
                super.onComplete(resource);
                if (cacheResult != null) {
                    mAdapter.getmObjects().removeAll(cacheResult.getDatas());
                    cacheResult = null;
                    page--;
                }
                if (resource.data != null) {
                    if (page == 0) {
                        mAdapter.replace(resource.data.getDatas());
                        mBinding.get().recycle.refreshComplete();
                    } else {
                        mBinding.get().recycle.loadMoreComplete();
                        mAdapter.getmObjects().addAll(resource.data.getDatas());
                        mAdapter.notifyDataSetChanged();
                    }
                    page++;
                }
            }

            @Override
            public void onCacheComplete(FeedArticleListData Result) {
                super.onCacheComplete(Result);
                if (Result != null) {
                    cacheResult = Result;
                    if (page == 0) {
                        mAdapter.replace(Result.getDatas());
                        mBinding.get().recycle.refreshComplete();
                    } else {
                        mBinding.get().recycle.loadMoreComplete();
                        mAdapter.getmObjects().addAll(Result.getDatas());
                        mAdapter.notifyDataSetChanged();
                    }
                    page++;
                }
            }
        }));
    }


    private void setupBanner(List<BannerVo> Result) {
        List<ImageView> viewList = new ArrayList<>();
        for (int i = 0; i < Result.size(); i++) {
            int index = i;
            ImageView imageView = new ImageView(this.getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this.getActivity()).load(Result.get(i).getImagePath()).into(imageView);
            imageView.setOnClickListener(v -> {
                enterDetial(Result.get(index).getUrl());
            });
            viewList.add(imageView);
        }
        BannerView bannerView = header.findViewById(R.id.banner_view);
        bannerView.setViewList(viewList);
        bannerView.startLoop(true);
    }

    private void enterDetial(String targetUrl) {
        Intent intent = new Intent(this.getActivity(), WebActivity.class);
        intent.putExtra("targetUrl", targetUrl);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinding != null && mBinding.get() != null && mBinding.get().recycle != null) {
            mBinding.get().recycle.destroy();
        }
    }
}

