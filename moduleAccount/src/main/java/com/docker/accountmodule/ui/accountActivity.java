package com.docker.accountmodule.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.docker.accountmodule.R;
import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.databinding.ModuleaccountActivityAccountBinding;
import com.docker.accountmodule.viewmodel.accountViewModel;
import com.docker.accountmodule.vo.BannerVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseActivity;
import com.docker.constantmodule.Constant.ConstantsRouter;

import java.util.List;

import javax.inject.Inject;

@Route(path = ConstantsRouter.ModuleAccount.ACTIVITY_ACCOUNT)
public class accountActivity extends BaseActivity<accountViewModel,ModuleaccountActivityAccountBinding> {


    @Inject
    ViewModelProvider.Factory factory;


    @Inject
    AccountService service;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleaccount_activity_account;
    }

    @Override
    public accountViewModel getViewModel() {
        return ViewModelProviders.of(this,factory).get(accountViewModel.class);
    }
    @Override
    protected void inject() {
        super.inject();
        ARouter.getInstance().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service.getBanners().observe(this, new CommonObserver<List<BannerVo>>(new CommonCallback<List<BannerVo>>() {
            @Override
            public void onComplete(List<BannerVo> Result) {
                Log.d("comm", "onComplete: "+Result.get(0).getDesc());
            }

            @Override
            public void onBusinessError() {
                Log.d("comm", "onBusinessError: ");
            }

            @Override
            public void onNetworkError(ApiResponse apiResponse) {
                Log.d("comm", "onNetworkError: ");
            }

            @Override
            public void onComplete(BaseResponse<List<BannerVo>> baseResponse) {
                super.onComplete(baseResponse);
                Log.d("comm", "BaseResponse: "+baseResponse.getData());
            }
        }));

    }




}
