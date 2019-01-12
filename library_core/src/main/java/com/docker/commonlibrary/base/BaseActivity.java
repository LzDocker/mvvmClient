package com.docker.commonlibrary.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.docker.commonlibrary.R;
import com.docker.commonlibrary.constant.Constant;
import com.docker.commonlibrary.constant.Constant.VmtoUIconstant;
import com.docker.constantmodule.util.SpTool;
import com.docker.constantmodule.util.ToastTool;

import static com.docker.commonlibrary.constant.Constant.VmtoUIconstant.KEY_NETWORK_COMPLETE;


public abstract class BaseActivity<VM extends BaseViewModel, VB extends ViewDataBinding> extends BaseInjectActivity {


    protected VB mBinding;
    protected VM mViewModel;
    protected abstract int getLayoutId();
    public abstract VM getViewModel();
    /*
    *  是否要覆盖父布局
    * */
    public boolean isOverrideContentView = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        if (isOverrideContentView) {
            mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        } else {
            setContentView(R.layout.activity_base);
            LinearLayout rootView = (LinearLayout) findViewById(R.id.root_layout);
            mBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), rootView, false);
            initToolBar(rootView);
            rootView.addView(mBinding.getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        mViewModel = getViewModel();
        getLifecycle().addObserver(mViewModel);
        initCommonListener();
    }


    public void initCommonListener() {

        mViewModel.commonMLD.observe(this,newdata->{

            switch (newdata.first.intValue()){
                case VmtoUIconstant.KEY_NETWORK_COMPLETE:closeWaitingView();
                    break;
                case VmtoUIconstant.KEY_NETWORK_ERROR:showNetWorkErrorView();
                    break;
                case VmtoUIconstant.KEY_NETWORK_BUSSINESS_ERROR:showBussinessErrorView(newdata.second);
                    break;
                case VmtoUIconstant.KEY_TOAST:showToast(newdata.second);
                    break;
            }

        });
    }

    /*
    *
    * 未覆盖父布局的默认包含一个toolbar
    * */
    protected void initToolBar(ViewGroup rootView) {


    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);

    }

    protected void showToast(String content){
        ToastTool.show(this,content);
    }

    protected void spSqve(String key, Object object){
        SpTool.save(this,key,object);
    }

    /*
    * 获取到数据的时候 关闭相关弹窗 恢复请求状态等动作
    * */
    protected void closeWaitingView(){}

    /*
    * 业务返回失败提示
    * */
    protected void showBussinessErrorView(String bussinessError){}

    /*
    * 网络连接失败
    * */
    protected void showNetWorkErrorView(){}


}
