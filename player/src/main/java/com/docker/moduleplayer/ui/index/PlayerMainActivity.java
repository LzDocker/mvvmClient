package com.docker.moduleplayer.ui.index;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseActivity;
import com.docker.commonlibrary.util.cookie.PersistentCookieStore;
import com.docker.constantmodule.Constant.Api;
import com.docker.constantmodule.Constant.ConstantsRouter;
import com.docker.moduleplayer.R;
import com.docker.moduleplayer.databinding.ModuleplayerActivityPlayerHomeBinding;
import com.docker.moduleplayer.ui.index.adapter.IndexPagerAdapter;
import com.docker.moduleplayer.ui.knowledge.KnowledgeFragment;
import com.docker.moduleplayer.ui.navigation.NavFragment;
import com.docker.moduleplayer.ui.projects.ProFragment;
import com.docker.moduleplayer.ui.subscription.SubFragment;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.vo.BannerVo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

@Route(path = ConstantsRouter.ModulePlayer.ACTIVITY_MAIN)
public class PlayerMainActivity extends BaseActivity<PlayerhomeViewModel, ModuleplayerActivityPlayerHomeBinding> {

    @Inject
    ViewModelProvider.Factory factory;

    private IndexPagerAdapter indexPagerAdapter;

    private List<Fragment> fragments;


    @Override
    protected int getLayoutId() {
        return R.layout.moduleplayer_activity_player_home;
    }

    @Override
    public PlayerhomeViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(PlayerhomeViewModel.class);
    }

    @Override
    protected void inject() {
        super.inject();
        ARouter.getInstance().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        initPagerView();
    }


    /*
    * 获取一下cookie 玩玩
    * */
    private void getCookie(){

        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(this);
        try {
            List<Cookie>  cookies =   persistentCookieStore.get(HttpUrl.get(new URL(Api.BASE_API)));
            for (Cookie c :
                    cookies) {

                Log.d("sss", "initPagerView: "+c.name()+"=="+c.value());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void initPagerView() {

        getCookie();
        fragments = new ArrayList<>();
        fragments.add(PlayerIndexFragment.getInstance());
        fragments.add(KnowledgeFragment.getInstance());
        fragments.add(SubFragment.getInstance());
        fragments.add(NavFragment.getInstance());
        fragments.add(ProFragment.getInstance());

        indexPagerAdapter = new IndexPagerAdapter(getSupportFragmentManager(), fragments);
        mBinding.viewPager.setAdapter(indexPagerAdapter);
        mBinding.viewPager.setCurrentItem(0);
//        mBinding.viewPager.setOffscreenPageLimit(5);
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        mBinding.rbIndex.setChecked(true);
                        break;
                    case 1:
                        mBinding.rbKnowledge.setChecked(true);
                        break;
                    case 2:
                        mBinding.rbCode.setChecked(true);
                        break;
                    case 3:
                        mBinding.rbNav.setChecked(true);
                        break;
                    case 4:
                        mBinding.rbPro.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setUpViewPager();

    }

    private void setUpViewPager() {

        mBinding.rgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int curIndex = 0;
                if (checkedId == R.id.rb_index) {
                    curIndex = 0;
                }
                if (checkedId == R.id.rb_knowledge) {
                    curIndex = 1;
                }
                if (checkedId == R.id.rb_code) {
                    curIndex = 2;
                }
                if (checkedId == R.id.rb_nav) {
                    curIndex = 3;
                }
                if (checkedId == R.id.rb_pro) {
                    curIndex = 4;
                }
                mBinding.viewPager.setCurrentItem(curIndex, false);


            }
        });
    }


}
