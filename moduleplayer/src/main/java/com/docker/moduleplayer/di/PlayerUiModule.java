package com.docker.moduleplayer.di;

import com.docker.commonlibrary.di.component.BaseActivityComponent;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.common.CommonFragment;
import com.docker.moduleplayer.ui.common.KnowledgeDetialActivity;
import com.docker.moduleplayer.ui.index.PlayerIndexFragment;
import com.docker.moduleplayer.ui.index.PlayerMainActivity;
import com.docker.moduleplayer.ui.knowledge.KnowledgeFragment;
import com.docker.moduleplayer.ui.navigation.NavFragment;
import com.docker.moduleplayer.ui.projects.ProCardFragment;
import com.docker.moduleplayer.ui.projects.ProFragment;
import com.docker.moduleplayer.ui.subscription.SubCardFragment;
import com.docker.moduleplayer.ui.subscription.SubFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangxindang on 2018/10/29.
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class PlayerUiModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract PlayerIndexFragment providerPlayerIndexFragment();

    @ActivityScope
    @ContributesAndroidInjector
    abstract KnowledgeFragment providerKnowledgeFragment();

    @ActivityScope
    @ContributesAndroidInjector
    abstract SubFragment providerSubFragment();

    @ActivityScope
    @ContributesAndroidInjector
    abstract NavFragment providerNavFragment();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ProFragment providerProFragment();


    @ActivityScope
    @ContributesAndroidInjector
    abstract PlayerMainActivity providerPlayerHomeActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract KnowledgeDetialActivity providerKnowledgeDetialActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract CommonFragment providerCommonFragment();

    @ActivityScope
    @ContributesAndroidInjector
    abstract SubCardFragment providerSubCardFragment();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ProCardFragment providerProCardFragment();
//
//    @ContributesAndroidInjector(modules = FragmentModule.class)
//    abstract LogisticsInformationActivity provideLogisticsInformationActivity();

}
