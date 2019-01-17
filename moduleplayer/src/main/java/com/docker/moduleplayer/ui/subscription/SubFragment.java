package com.docker.moduleplayer.ui.subscription;

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
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentKnowledgeBinding;
import com.docker.moduleplayer.databinding.ModuleplayerFragmentSubcodeBinding;
import com.docker.moduleplayer.ui.subscription.adapter.SubPagerAdapter;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.WxAuthor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/12/17.
 */

public class SubFragment extends BaseFragment<PlayerhomeViewModel, ModuleplayerFragmentSubcodeBinding> {


    private SubPagerAdapter subPagerAdapter;
    private List<Fragment> fragments;
    private int currentPage = 0;

    public String Keywords;

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    @Inject
    ViewModelProvider.Factory factory;


    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_fragment_subcode;
    }

    @Override
    protected PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this.getActivity(), factory).get(PlayerhomeViewModel.class);
    }

    public static SubFragment getInstance() {
        return new SubFragment();
    }

    @Override
    protected void initView(View var1) {

        mBinding.get().editQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setKeywords(mBinding.get().editQuery.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.get().tvSearch.setOnClickListener(v -> {
            if (fragments != null) {
                setKeywords(mBinding.get().editQuery.getText().toString());
                currentPage = mBinding.get().tablayout.getSelectedTabPosition();
                ((SubCardFragment) fragments.get(currentPage)).queryData(getKeywords(), true);
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setKeywords(mBinding.get().editQuery.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        mViewModel.getWxAuthorListData().observe(this, new CommonObserver<>(new CommonCallback<List<WxAuthor>>() {
            @Override
            public void onComplete(List<WxAuthor> Result) {
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

    private void setupPagers(List<WxAuthor> Result) {
        fragments = new ArrayList<>();
        for (int i = 0; i < Result.size(); i++) {
            fragments.add(SubCardFragment.getInstance(Result.get(i).getId()));
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
