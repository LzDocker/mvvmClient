package com.docker.moduleplayer.ui.knowledge;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.docker.commonlibrary.adapter.SimpleCommonRecyclerAdapter;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseFragment;
import com.docker.commonlibrary.base.BaseViewModel;
import com.docker.moduleplayer.BR;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentKnowledgeBinding;
import com.docker.moduleplayer.ui.common.KnowledgeDetialActivity;
import com.docker.moduleplayer.ui.common.WebActivity;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;
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

public class KnowledgeFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentKnowledgeBinding> {

    private int page = 0;

    private SimpleCommonRecyclerAdapter<KnowledgeHierarchyData> mAdapter;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_knowledge;
    }

    @Override
    protected PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(PlayerhomeViewModel.class);
    }

    public static KnowledgeFragment getInstance() {
        return new KnowledgeFragment();
    }

    @Override
    protected void initView(View var1) {
        mAdapter = new SimpleCommonRecyclerAdapter<>(R.layout.moduleplayer_konwledge_item, BR.item);
        mBinding.get().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.get().recycle.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SimpleCommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                enterDetial(mAdapter.getItem(position-1).getId(), mAdapter.getItem(position-1));
            }
        });
        mBinding.get().recycle.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mBinding.get().recycle.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
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

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        mViewModel.getKnowledgeHierarchyData().observe(this, new CommonObserver<>(new CommonCallback<List<KnowledgeHierarchyData>>() {
            @Override
            public void onComplete(List<KnowledgeHierarchyData> Result) {
                if (Result != null) {
                    for (KnowledgeHierarchyData item : Result) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (KnowledgeHierarchyData itemo : item.getChildren()) {
                            stringBuilder.append(itemo.getName() + "  ");
                            item.setInfoGrow(stringBuilder.toString());
                        }
                    }

                    if (page == 0) {
                        mAdapter.replace(Result);
                    } else {
                        mAdapter.getmObjects().addAll(Result);
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
                mBinding.get().recycle.refreshComplete();
                mBinding.get().recycle.loadMoreComplete();
            }

            @Override
            public void onNetworkError(ApiResponse apiResponse) {

            }
        }));
    }


    private void enterDetial(int cid, KnowledgeHierarchyData knowledgeHierarchyData) {
        Intent intent = new Intent(this.getActivity(), KnowledgeDetialActivity.class);
        intent.putExtra("cid", cid);
        intent.putExtra("Data", knowledgeHierarchyData);
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
