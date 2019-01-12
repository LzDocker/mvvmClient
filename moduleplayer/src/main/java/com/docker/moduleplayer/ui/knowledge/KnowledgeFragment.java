package com.docker.moduleplayer.ui.knowledge;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.docker.commonlibrary.adapter.SimpleCommonRecyclerAdapter;
import com.docker.commonlibrary.base.BaseFragment;
import com.docker.commonlibrary.vo.Resource;
import com.docker.moduleplayer.BR;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentKnowledgeBinding;
import com.docker.moduleplayer.ui.common.KnowledgeDetialActivity;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class KnowledgeFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentKnowledgeBinding> {

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_knowledge;
    }

    @Override
    protected PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this.getActivity(), factory).get(PlayerhomeViewModel.class);
    }

    public static KnowledgeFragment getInstance() {
        return new KnowledgeFragment();
    }


    @Override
    protected void initView(View var1) {
        mBinding.get().recycle.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mBinding.get().recycle.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        mBinding.get().recycle.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mBinding.get().recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mViewModel.getKnowledgeHierarchyData();
            }

            @Override
            public void onLoadMore() {
                mViewModel.getKnowledgeHierarchyData();
            }
        });

    }

    Observer<KnowledgeHierarchyData> observer = new Observer<KnowledgeHierarchyData>(){
        @Override
        public void onChanged(@Nullable KnowledgeHierarchyData knowledgeHierarchyData) {
            Log.d("sss", "onChanged: ------"+mViewModel.knowEnterMessage);
            KnowledgeFragment.this.enterDetial(knowledgeHierarchyData.getId(), knowledgeHierarchyData);
        }
    };

    Observer<Resource<List<KnowledgeHierarchyData>>> knowdataObserver = new Observer<Resource<List<KnowledgeHierarchyData>>>() {
        @Override
        public void onChanged(@Nullable Resource<List<KnowledgeHierarchyData>> listResource) {
            Log.d("sss", "onChanged:----KnowledgeHierarchyLLData- ");
            mBinding.get().recycle.refreshComplete();
            mBinding.get().recycle.loadMoreComplete();
        }
    };



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.get().setViewModel(mViewModel);
        mBinding.get().recycle.refresh();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mViewModel.KnowledgeHierarchyLLData.observe(this, new Observer<Resource<List<KnowledgeHierarchyData>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<KnowledgeHierarchyData>> listResource) {
                mBinding.get().recycle.refreshComplete();
                mBinding.get().recycle.loadMoreComplete();
            }
        });

        mViewModel.knowEnterMessage.observe(this, new Observer<KnowledgeHierarchyData>(){
            @Override
            public void onChanged(@Nullable KnowledgeHierarchyData knowledgeHierarchyData) {
                Log.d("sss", "onChanged: ------"+mViewModel.knowEnterMessage);
                KnowledgeFragment.this.enterDetial(knowledgeHierarchyData.getId(), knowledgeHierarchyData);
            }
        });
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
