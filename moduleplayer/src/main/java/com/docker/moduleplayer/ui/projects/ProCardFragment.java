package com.docker.moduleplayer.ui.projects;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.docker.commonlibrary.adapter.SimpleCommonRecyclerAdapter;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseFragment;
import com.docker.moduleplayer.BR;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentCommonBinding;
import com.docker.moduleplayer.ui.common.WebActivity;
import com.docker.moduleplayer.ui.subscription.SubFragment;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.ProjectListData;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class ProCardFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentCommonBinding> {


    private int cid;
    private int page = 0;
    private SimpleCommonRecyclerAdapter<FeedArticleData> mAdapter;
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_common;
    }

    public static ProCardFragment getInstance(int cid) {
        ProCardFragment subCardFragment = new ProCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        subCardFragment.setArguments(bundle);
        return subCardFragment;
    }

    @Override
    protected PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(getActivity(), factory).get(PlayerhomeViewModel.class);
    }

    @Override
    protected void initView(View var1) {
        mAdapter = new SimpleCommonRecyclerAdapter<>(R.layout.moduleplayer_pro_item, BR.item);
        mBinding.get().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.get().recycle.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SimpleCommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                enterDetial(mAdapter.getmObjects().get(position).getLink());
            }
        });


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
                page = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });

//        mBinding.get().refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
//                page = 0;
//                initData();
//
//            }
//        });
//        mBinding.get().refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
//                initData();
//            }
//        });
//        mBinding.get().emptyLayout.setEmptyButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initData();
//            }
//        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cid = (int) getArguments().getInt("cid");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        page = 0;
        mBinding.get().recycle.refresh();
    }

    private void initData() {
        mViewModel.getProjectListData(page,cid).observe(this, new CommonObserver<>(new CommonCallback<ProjectListData>() {
            @Override
            public void onComplete(ProjectListData Result) {
                if (Result != null) {
                    if (page == 0) {
                        mAdapter.replace(Result.getDatas());
                    } else {
                        mAdapter.getmObjects().addAll(Result.getDatas());
                        mAdapter.notifyDataSetChanged();
                    }
                    page++;
                } else {
                    if (page == 0) {
//                        mBinding.get().emptyLayout.showEmpty();
                    } else {
//                        mBinding.get().refreshLayout.setNoMoreData(true);
                    }
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
//                mBinding.get().refreshLayout.finishRefresh();
//                mBinding.get().refreshLayout.finishLoadMore();
                mBinding.get().recycle.refreshComplete();
                mBinding.get().recycle.loadMoreComplete();
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
