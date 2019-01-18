package com.docker.moduleplayer.ui.navigation;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.docker.commonlibrary.adapter.SimpleCommonRecyclerAdapter;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseFragment;
import com.docker.moduleplayer.BR;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentKnowledgeBinding;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentNavBinding;
import com.docker.moduleplayer.ui.common.WebActivity;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.NavigationListData;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class NavFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentNavBinding> {

    private SimpleCommonRecyclerAdapter<FeedArticleData> mAdapter;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_nav;
    }

    @Override
    protected PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this.getActivity(), factory).get(PlayerhomeViewModel.class);
    }

    public static NavFragment getInstance() {
        return new NavFragment();
    }

    @Override
    protected void initView(View var1) {
        mAdapter = new SimpleCommonRecyclerAdapter<>(R.layout.moduleplayer_common_item, BR.item);
        mAdapter.setOnItemClickListener((var, position) -> {
            enterDetial(mAdapter.getmObjects().get(position).getLink());
        });
        mBinding.get().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.get().recycle.setAdapter(mAdapter);

        mBinding.get().recycle.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mBinding.get().recycle.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        mAdapter.setOnItemClickListener(new SimpleCommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                enterDetial(mAdapter.getmObjects().get(position-1).getLink());
            }
        });

        mBinding.get().recycle.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mBinding.get().recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.get().recycle.refresh();
    }

    private void initData() {

        mViewModel.getNavigationListData().observe(this, new CommonObserver<>(new CommonCallback<List<NavigationListData>>() {
            @Override
            public void onComplete(List<NavigationListData> Result) {
                if (Result != null) {
                    if (mAdapter.getmObjects() != null) {
                        mAdapter.replace(Result.get(0).getArticles());
                    } else {
                        mAdapter.add(Result.get(0).getArticles());
                    }
                } else {
//                    mBinding.get().emptyLayout.showEmpty();
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
//                mBinding.get().refreshLayout.finishRefresh();
                mBinding.get().recycle.refreshComplete();
            }

            @Override
            public void onBusinessError(BaseResponse baseResponse) {

            }

            @Override
            public void onNetworkError(ApiResponse apiResponse) {

            }
        }));
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
