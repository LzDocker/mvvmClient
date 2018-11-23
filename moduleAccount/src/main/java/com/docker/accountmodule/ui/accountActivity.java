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
import com.docker.accountmodule.vo.LoginVo;
import com.docker.accountmodule.vo.RegisterVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseActivity;
import com.docker.constantmodule.Constant.ConstantsRouter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@Route(path = ConstantsRouter.ModuleAccount.ACTIVITY_ACCOUNT)
public class accountActivity extends BaseActivity<accountViewModel, ModuleaccountActivityAccountBinding> {


    @Inject
    ViewModelProvider.Factory factory;


    @Inject
    AccountService service;

    RegisterVo registerVo;

    @Override
    protected int getLayoutId() {
        return R.layout.moduleaccount_activity_account;
    }

    @Override
    public accountViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(accountViewModel.class);
    }

    @Override
    protected void inject() {
        super.inject();
        ARouter.getInstance().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
    }

    private void initview() {
        registerVo = new RegisterVo();
        mBinding.setVo(registerVo);

        mBinding.tvRegister.setOnClickListener(v -> {
            mViewModel.register(mBinding.getVo()).observe(this, new CommonObserver<>(new CommonCallback<LoginVo>() {
                @Override
                public void onComplete(LoginVo Result) {
                       if(Result!=null){
                           finish();
                       }
                }
                @Override
                public void onBusinessError(ApiResponse apiResponse) {

                }
                @Override
                public void onNetworkError(ApiResponse apiResponse) {

                }
            }));

        });
    }


}
