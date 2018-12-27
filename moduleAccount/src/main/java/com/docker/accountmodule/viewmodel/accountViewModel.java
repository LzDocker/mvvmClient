package com.docker.accountmodule.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.repository.AccountRepository;
import com.docker.accountmodule.vo.LoginParam;
import com.docker.accountmodule.vo.LoginVo;
import com.docker.accountmodule.vo.RegisterVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.base.BaseViewModel;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.vo.Resource;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by zhangxindang on 2018/10/19.
 */

public class accountViewModel extends BaseViewModel {

    @Inject
    AccountService service;

    @Inject
    AppExecutors appExecutors;


    @Inject
    AccountRepository accountRepository;

    @Inject
    public accountViewModel() {

    }

    private final MutableLiveData<LoginParam> paramlv = new MutableLiveData();
    public final LiveData<Resource<LoginVo>> loginlv =
            Transformations.switchMap(paramlv, new Function<LoginParam, LiveData<Resource<LoginVo>>>() {
                @Override
                public LiveData<Resource<LoginVo>> apply(LoginParam param) {
                    return accountRepository.Login(param.name, param.pwd);
                }
            });


    public void Login(String username, String pwd) {
        paramlv.setValue(new LoginParam(username, pwd));
    }


    /*
    * 注册
    * */
    public LiveData<ApiResponse<BaseResponse<LoginVo>>> register(RegisterVo registerVo) {

        return service.register(registerVo.getUsername().toString().trim(),
                registerVo.getPassword().toString().trim(), registerVo.getRepassword().toString().trim());
    }

    public LiveData<ApiResponse<BaseResponse<LoginVo>>> login(RegisterVo registerVo) {

        return service.login(registerVo.getUsername().toString().trim(),
                registerVo.getPassword().toString().trim());
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
