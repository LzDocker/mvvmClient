package com.docker.mvvmclient.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.docker.commonlibrary.base.BaseActivity;
import com.docker.constantmodule.Constant.ConstantsRouter;
import com.docker.mvvmclient.R;
import com.docker.mvvmclient.databinding.ActivityMainBinding;
import com.docker.mvvmclient.viewmodel.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Inject
    ViewModelProvider.Factory factory;

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
    }

    private void initView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(ConstantsRouter.ModuleAccount.ACTIVITY_ACCOUNT).navigation();
//                finish();
            }
        }, 2000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}


