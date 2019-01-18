package com.docker.core.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.docker.core.R;
import com.docker.core.constant.Constant;
import com.docker.core.constant.Constant.VmtoUIconstant;


import static com.docker.core.constant.Constant.VmtoUIconstant.KEY_NETWORK_COMPLETE;


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

//    protected void showToast(String content) {
//        ToastTool.show(this, content);
//    }
//
//    protected void spSqve(String key, Object object) {
//        SpTool.save(this, key, object);
//    }



}
