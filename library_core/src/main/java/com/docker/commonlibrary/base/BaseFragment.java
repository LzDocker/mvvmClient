package com.docker.commonlibrary.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.docker.commonlibrary.R;
import com.docker.commonlibrary.util.AutoClearedValue;


/**
 * Created by zhangxindang on 2018/8/31.
 */

public abstract class BaseFragment <VM extends BaseViewModel,VB extends ViewDataBinding> extends BaseInjectFragment {

    protected AutoClearedValue<VB> mBinding;
    protected VM mViewModel;
    private LinearLayout rootView;

    public BaseFragment() {
    }

    protected abstract int getLayoutId();

    protected abstract VM getViewModel();

    protected abstract void initView(View var1);

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = (LinearLayout)inflater.inflate(R.layout.fragment_base, (ViewGroup)null);
        VB dataBinding = DataBindingUtil.inflate(inflater, this.getLayoutId(), container, false);
        this.mBinding = new AutoClearedValue(this, dataBinding);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
        (this.mBinding.get()).getRoot().setLayoutParams(params);
        LinearLayout rlContainer = this.rootView.findViewById(R.id.container);
        rlContainer.addView((this.mBinding.get()).getRoot());
        this.initView(this.rootView);
        return this.rootView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mViewModel = this.getViewModel();
        this.getLifecycle().addObserver(this.mViewModel);
    }

    protected BaseActivity getHoldingActivity() {
        return (BaseActivity)this.getActivity();
    }

    protected LinearLayout getRootView() {
        return this.rootView;
    }
}
