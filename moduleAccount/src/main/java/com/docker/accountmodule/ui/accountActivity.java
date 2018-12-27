package com.docker.accountmodule.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.docker.accountmodule.R;
import com.docker.accountmodule.databinding.ModuleaccountActivityAccountBinding;
import com.docker.accountmodule.viewmodel.accountViewModel;
import com.docker.accountmodule.vo.LoginVo;
import com.docker.accountmodule.vo.RegisterVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.api.NetBoundCallback;
import com.docker.commonlibrary.api.NetBoundObserver;
import com.docker.commonlibrary.base.BaseActivity;
import com.docker.commonlibrary.vo.Resource;
import com.docker.commonlibrary.vo.Status;
import com.docker.constantmodule.Constant.ConstantsRouter;
import com.docker.constantmodule.util.SpTool;

import javax.inject.Inject;


/*
*
* 123456799
*
* 123456
*
*
* */


@Route(path = ConstantsRouter.ModuleAccount.ACTIVITY_ACCOUNT)
public class accountActivity extends BaseActivity<accountViewModel, ModuleaccountActivityAccountBinding> {

    @Inject
    ViewModelProvider.Factory factory;

    private static final int RegisterFlag = 1001;
    private static final int LoginFlag = 1002;
    private static int Flag = RegisterFlag;
    private RegisterVo registerVo;

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
        boolean islogin = (boolean) SpTool.get(this, "LOGIN_FLAG", false);
        if (islogin) {
            toHome(null);
            finish();
            return;
        }
        registerVo = new RegisterVo();
        mBinding.setVo(registerVo);
        Flag = getIntent().getIntExtra("FLAG", RegisterFlag);
        if (Flag == LoginFlag) {
            mBinding.llVerCoutainer.setVisibility(View.GONE);
            mBinding.tvRegister.setText("登录");
            mBinding.tvLogin.setVisibility(View.GONE);
        }
        mBinding.tvRegister.setOnClickListener(v -> {
            if (chechParam()) {
                if (Flag == RegisterFlag) {
                    register();
                } else {
                    login();
                }
            }

        });
        mBinding.tvLogin.setOnClickListener(v -> {
            toLogin();
        });

//        mViewModel.loginlv.observe(this, loginVo -> {
//            Log.d("sss", "initview: -----------loginVo----------" + loginVo.status);
//            if (loginVo.status == Status.BUSSINESSERROR) {
//                Log.d("sss", "initview: -----------loginVo----------" + loginVo.message);
//            }
//        });

        mViewModel.loginlv.observe(this, new NetBoundObserver<>(new NetBoundCallback<LoginVo>() {
            @Override
            public void onComplete(LoginVo Result) {
                spSqve("LOGIN_FLAG",true);
                toHome(null);

            }
            @Override
            public void onBusinessError(Resource<LoginVo> resource) {
            }

            @Override
            public void onNetworkError(Resource<LoginVo> resource) {
            }

            @Override
            public void onCacheComplete(LoginVo Result) {
                super.onCacheComplete(Result);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        }));
    }


    private boolean chechParam() {
        registerVo = mBinding.getVo();
        if (TextUtils.isEmpty(registerVo.getUsername())) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(registerVo.getPassword())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Flag == LoginFlag) {
            return true;
        }
        if (TextUtils.isEmpty(registerVo.getRepassword())) {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!registerVo.getRepassword().equals(registerVo.getPassword())) {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void register() {

        mViewModel.register(registerVo).observe(this, new CommonObserver<>(new CommonCallback<LoginVo>() {
            @Override
            public void onComplete(LoginVo Result) {
                toLogin();
            }

            @Override
            public void onBusinessError(BaseResponse baseResponse) {
                showToast(baseResponse.getErrorMsg());
            }

            @Override
            public void onNetworkError(ApiResponse apiResponse) {
                showToast(apiResponse.errorMessage);
            }
        }));
    }


    private void login() {
        chechParam();
        mViewModel.Login(registerVo.getUsername(), registerVo.getPassword());
    }

    private void toLogin() {

        Intent intent = new Intent(this, accountActivity.class);
        intent.putExtra("FLAG", LoginFlag);
        startActivity(intent);
        finish();
    }


    private void toHome(LoginVo Result) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantsRouter.ModuleAccount.LOGINVO, Result);
        ARouter.getInstance().build(ConstantsRouter.ModulePlayer.ACTIVITY_MAIN).withBundle(ConstantsRouter.ModuleAccount.LOGINVO, bundle).navigation();

    }


}
