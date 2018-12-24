package com.docker.moduleplayer.ui.index;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
import com.docker.commonlibrary.base.BaseFragment;
import com.docker.moduleplayer.BR;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentIndexBinding;
import com.docker.moduleplayer.ui.common.WebActivity;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.FeedArticleListData;
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
        return ViewModelProviders.of(this, factory).get(PlayerhomeViewModel.class);
    }

    @Override
    protected void initView(View var) {
        mAdapter = new SimpleCommonRecyclerAdapter<>(R.layout.moduleplayer_item_article_index, BR.item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.get().recycle.setLayoutManager(linearLayoutManager);
        mBinding.get().recycle.setAdapter(mAdapter);
        mBinding.get().recycle.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mBinding.get().recycle.setRefreshProgressStyle(ProgressStyle.BallGridPulse);

        mAdapter.setOnItemClickListener(new SimpleCommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                enterDetial(mAdapter.getmObjects().get(position-2).getLink());
            }
        });

        mBinding.get().recycle.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mBinding.get().recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                getArticleData();
            }

            @Override
            public void onLoadMore() {
                getArticleData();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBanner();
        mBinding.get().recycle.refresh();
    }


    private void  getBanner(){
        mViewModel.getBanner().observe(this, new CommonObserver<>(new CommonCallback<List<BannerVo>>() {
            @Override
            public void onComplete(List<BannerVo> Result) {
                if (Result != null) {
                    setupBanner(Result);
                }
            }

            @Override
            public void onBusinessError(BaseResponse baseResponse) {

            }

            @Override
            public void onNetworkError(ApiResponse apiResponse) {

            }
        }));
    }


    private void getArticleData() {
        mViewModel.getIndexList(page).observe(this, new CommonObserver<>(new CommonCallback<FeedArticleListData>() {
            @Override
            public void onComplete(FeedArticleListData Result) {
                if (Result != null) {
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

            @Override
            public void onBusinessError(BaseResponse baseResponse) {
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onNetworkError(ApiResponse apiResponse) {

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
        View header = LayoutInflater.from(this.getActivity()).inflate(R.layout.moduleplayer_header_banner, (ViewGroup) this.getActivity().findViewById(android.R.id.content), false);
        BannerView bannerView = header.findViewById(R.id.banner_view);
        bannerView.setViewList(viewList);
        bannerView.startLoop(true);
        mBinding.get().recycle.addHeaderView(header);
    }

    private void enterDetial(String targetUrl) {
        Intent intent = new Intent(this.getActivity(), WebActivity.class);
        intent.putExtra("targetUrl", targetUrl);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBinding != null && mBinding.get() != null && mBinding.get().recycle != null) {
            mBinding.get().recycle.destroy();
        }
    }
}

