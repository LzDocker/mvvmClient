package com.docker.mvvmclient.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.docker.accountmodule.vo.BannerVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseActivity;
import com.docker.constantmodule.Constant.ConstantsRouter;
import com.docker.modulegank.gankActivity;
import com.docker.moduleplayer.ui.index.PlayerIndexFragment;
import com.docker.mvvmclient.R;
import com.docker.mvvmclient.api.AccountService;
import com.docker.mvvmclient.databinding.ActivityMainBinding;
import com.docker.mvvmclient.viewmodel.MainViewModel;

import java.util.List;

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


