package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.projects.ProCardFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PlayerUiModule_ProviderProCardFragment.ProCardFragmentSubcomponent.class)
public abstract class PlayerUiModule_ProviderProCardFragment {
  private PlayerUiModule_ProviderProCardFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(ProCardFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      ProCardFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface ProCardFragmentSubcomponent extends AndroidInjector<ProCardFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProCardFragment> {}
  }
}
