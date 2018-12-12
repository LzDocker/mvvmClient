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

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

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


    private int ScreenWidth;
    private int ScreenHeight;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    private void initView() {

        mBinding.tvAccount.setOnClickListener(v -> {

            ARouter.getInstance().build(ConstantsRouter.ModuleAccount.ACTIVITY_ACCOUNT).navigation();


            mViewModel.getData().observe(this, new CommonObserver<>(new CommonCallback<List<BannerVo>>() {
                @Override
                public void onComplete(List<BannerVo> Result) {
                    Log.d("ssss", "onComplete: -------------------------------");
                }

                @Override
                public void onBusinessError(ApiResponse apiResponse) {
                    Log.d("ssss", "onBusinessError: -------------------------------");
                }

                @Override
                public void onNetworkError(ApiResponse apiResponse) {
                    Log.d("ssss", "onNetworkError: -------------------------------");
                }
            }));

        });

        mBinding.tvGank.setOnClickListener(v -> {

            ARouter.getInstance().build(ConstantsRouter.ModuleGank.ACTIVITY_GANK).navigation();

        });




        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenWidth = dm.widthPixels;
        ScreenHeight = dm.heightPixels;
        anim();

    }





    private void Animation(View view,boolean flag) {
        TranslateAnimation translateAnimation;




    }



    private  void anim(){
        TranslateAnimation leftani  = new TranslateAnimation(-ScreenWidth, ScreenWidth, 0, 0);
        TranslateAnimation rightani  = new TranslateAnimation(ScreenWidth, -ScreenWidth, 0, 0);

        leftani.setDuration(2000);
        leftani.setRepeatCount(0);
        rightani.setDuration(2000);
        rightani.setRepeatCount(0);

        mBinding.image.setAnimation(leftani);
        leftani.startNow();


        leftani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                RotateAnimation rotate  = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                rotate.setDuration(0);//设置动画持续周期
//                rotate.setRepeatCount(0);//设置重复次数
//                rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
//                mBinding.image.setAnimation(rotate);
//                rotate.startNow();
            }

            @Override
            public void onAnimationEnd(Animation animation) {


                mBinding.image.setAnimation(rightani);
                rightani.startNow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rightani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                RotateAnimation rotate  = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(0);//设置动画持续周期
                rotate.setRepeatCount(0);//设置重复次数
                rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
                mBinding.image.setAnimation(rotate);
                rotate.startNow();
//
//                mBinding.image.setAnimation(rightani);
//                rightani.startNow();
            }

            @Override
            public void onAnimationEnd(Animation animation) {



                mBinding.image.setAnimation(leftani);
                leftani.startNow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}


