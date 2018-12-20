package com.docker.moduleplayer.ui.subscription;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.docker.moduleplayer.databinding.ModuleplayerFragmentSubcodeBinding;
import com.docker.moduleplayer.ui.common.CommonFragment;
import com.docker.moduleplayer.ui.common.WebActivity;
import com.docker.moduleplayer.ui.subscription.adapter.SubPagerAdapter;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.WxAuthor;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class SubCardFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentCommonBinding> {


    private int cid;
    private int page = 0;
    private SimpleCommonRecyclerAdapter<FeedArticleData> mAdapter;
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_common;
    }

    public static SubCardFragment getInstance(int cid) {
        SubCardFragment subCardFragment = new SubCardFragment();
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
        mAdapter = new SimpleCommonRecyclerAdapter<>(R.layout.moduleplayer_common_item, BR.item);
        mBinding.get().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.get().recycle.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SimpleCommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                enterDetial(mAdapter.getmObjects().get(position).getLink());
            }
        });

        mBinding.get().refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 0;
                if (TextUtils.isEmpty(((SubFragment) getParentFragment()).getKeywords())) {
                    initData();
                } else {
                    queryData(((SubFragment) getParentFragment()).getKeywords(), false);
                }
            }
        });
        mBinding.get().refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (TextUtils.isEmpty(((SubFragment) getParentFragment()).getKeywords())) {
                    initData();
                } else {
                    queryData(((SubFragment) getParentFragment()).getKeywords(), false);
                }
            }
        });
        mBinding.get().emptyLayout.setEmptyButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(((SubFragment) getParentFragment()).getKeywords())) {
                    initData();
                } else {
                    queryData(((SubFragment) getParentFragment()).getKeywords(), false);
                }
            }
        });
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
        initData();
    }

    private void initData() {
        mViewModel.getWxSumData(cid, page).observe(this, new CommonObserver<>(new CommonCallback<FeedArticleListData>() {
            @Override
            public void onComplete(FeedArticleListData Result) {
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
                        mBinding.get().emptyLayout.showEmpty();
                    } else {
                        mBinding.get().refreshLayout.setNoMoreData(true);
                    }
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
                mBinding.get().refreshLayout.finishRefresh();
                mBinding.get().refreshLayout.finishLoadMore();
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


    public void queryData(String k, boolean isClick) {
        if (isClick) {
            page = 0;
        }
        mViewModel.getWxSearchSumData(cid, page, k).observe(this, new CommonObserver<>(new CommonCallback<FeedArticleListData>() {
            @Override
            public void onComplete(FeedArticleListData Result) {
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
                        mBinding.get().emptyLayout.showEmpty();
                    } else {
                        mBinding.get().refreshLayout.setNoMoreData(true);
                    }
                }
            }

            @Override
            public void onBusinessError(BaseResponse baseResponse) {

            }

            @Override
            public void onComplete() {
                super.onComplete();
                mBinding.get().refreshLayout.finishRefresh();
                mBinding.get().refreshLayout.finishLoadMore();
            }

            @Override
            public void onNetworkError(ApiResponse apiResponse) {

            }
        }));
    }
}
