package com.docker.mvvmclient.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.docker.accountmodule.vo.BannerVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseActivity;
import com.docker.constantmodule.Constant.ConstantsRouter;
import com.docker.modulegank.gankActivity;
import com.docker.mvvmclient.R;
import com.docker.mvvmclient.api.AccountService;
import com.docker.mvvmclient.databinding.ActivityMainBinding;
import com.docker.mvvmclient.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel,ActivityMainBinding> {

    @Inject
    ViewModelProvider.Factory factory;

    @Inject
    AccountService service;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          initView();

            mViewModel.getData().observe(this,new CommonObserver<>(new CommonCallback<List<BannerVo>>() {
                @Override
                public void onComplete(List<BannerVo> Result) {
                    Log.d("ssss", "onComplete: -------------------------------");
                }

                @Override
                public void onBusinessError() {
                    Log.d("ssss", "onBusinessError: -------------------------------");
                }

                @Override
                public void onNetworkError(ApiResponse apiResponse) {
                    Log.d("ssss", "onNetworkError: -------------------------------");
                }
            }));

    }


    private void initView(){

        mBinding.tvAccount.setOnClickListener(v -> {

            ARouter.getInstance().build(ConstantsRouter.ModuleAccount.ACTIVITY_ACCOUNT).navigation();

        });

        mBinding.tvGank.setOnClickListener(v -> {

            ARouter.getInstance().build(ConstantsRouter.ModuleGank.ACTIVITY_GANK).navigation();

        });
    }



}
