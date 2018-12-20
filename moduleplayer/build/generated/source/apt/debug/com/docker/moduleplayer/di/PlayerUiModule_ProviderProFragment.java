package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.projects.ProFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PlayerUiModule_ProviderProFragment.ProFragmentSubcomponent.class)
public abstract class PlayerUiModule_ProviderProFragment {
  private PlayerUiModule_ProviderProFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(ProFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      ProFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface ProFragmentSubcomponent extends AndroidInjector<ProFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProFragment> {}
  }
}
