package com.docker.moduleplayer.ui.projects;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseFragment;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentKnowledgeBinding;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentProBinding;
import com.docker.moduleplayer.ui.subscription.SubCardFragment;
import com.docker.moduleplayer.ui.subscription.SubFragment;
import com.docker.moduleplayer.ui.subscription.adapter.SubPagerAdapter;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.ProjectClassifyData;
import com.docker.moduleplayer.vo.WxAuthor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class ProFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentProBinding> {

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_pro;
    }

    @Override
    protected PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(PlayerhomeViewModel.class);
    }

    public static ProFragment getInstance() {
        return new ProFragment();
    }


    private SubPagerAdapter subPagerAdapter;
    private List<Fragment> fragments;


    @Override
    protected void initView(View var1) {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        mViewModel.getProjectClassifyData().observe(this, new CommonObserver<>(new CommonCallback<List<ProjectClassifyData>>() {
            @Override
            public void onComplete(List<ProjectClassifyData> Result) {
                if (Result != null) {
                    setupPagers(Result);
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

    private void setupPagers(List<ProjectClassifyData> Result) {
        fragments = new ArrayList<>();
        for (int i = 0; i < Result.size(); i++) {
            fragments.add(ProCardFragment.getInstance(Result.get(i).getId()));
            mBinding.get().tablayout.addTab(mBinding.get().tablayout.newTab());
        }
        subPagerAdapter = new SubPagerAdapter(getChildFragmentManager(), fragments);
        mBinding.get().viewpager.setAdapter(subPagerAdapter);
        mBinding.get().tablayout.setupWithViewPager(mBinding.get().viewpager, true);
        for (int i = 0; i < Result.size(); i++) {
            mBinding.get().tablayout.getTabAt(i).setText(Result.get(i).getName());
        }

    }


}
