package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.navigation.NavFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PlayerUiModule_ProviderNavFragment.NavFragmentSubcomponent.class)
public abstract class PlayerUiModule_ProviderNavFragment {
  private PlayerUiModule_ProviderNavFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(NavFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      NavFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface NavFragmentSubcomponent extends AndroidInjector<NavFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NavFragment> {}
  }
}
