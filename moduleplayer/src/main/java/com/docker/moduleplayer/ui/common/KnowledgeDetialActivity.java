package com.docker.moduleplayer.ui.common;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.docker.commonlibrary.base.BaseActivity;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerActivityKnowledgeDetialBinding;
import com.docker.moduleplayer.ui.common.adapter.KnowledgePagerAdapter;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class KnowledgeDetialActivity extends BaseActivity<PlayerhomeViewModel, ModuleplayerActivityKnowledgeDetialBinding> {

    @Inject
    ViewModelProvider.Factory factory;
    private List<Fragment> fragments;
    private KnowledgeHierarchyData knowledgeHierarchyData;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_activity_knowledge_detial;
    }

    @Override
    public PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(PlayerhomeViewModel.class);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        getSupportActionBar().hide();
        initView();
    }


    private void initView() {
        fragments = new ArrayList<>();
        knowledgeHierarchyData = (KnowledgeHierarchyData) getIntent().getExtras().get("Data");
        for (int i = 0; i < knowledgeHierarchyData.getChildren().size(); i++) {
            fragments.add(CommonFragment.getInstance(knowledgeHierarchyData.getChildren().get(i).getId()));
            mBinding.tablayout.addTab(mBinding.tablayout.newTab());
        }
        mBinding.viewpager.setAdapter(new KnowledgePagerAdapter(getSupportFragmentManager(), fragments));
        mBinding.tablayout.setupWithViewPager(mBinding.viewpager, true);
        for (int i = 0; i < knowledgeHierarchyData.getChildren().size(); i++) {
            mBinding.tablayout.getTabAt(i).setText(knowledgeHierarchyData.getChildren().get(i).getName());
        }
    }


}

