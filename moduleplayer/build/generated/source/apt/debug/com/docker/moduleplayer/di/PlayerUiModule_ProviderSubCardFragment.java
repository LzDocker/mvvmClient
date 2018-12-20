package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.subscription.SubCardFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PlayerUiModule_ProviderSubCardFragment.SubCardFragmentSubcomponent.class)
public abstract class PlayerUiModule_ProviderSubCardFragment {
  private PlayerUiModule_ProviderSubCardFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(SubCardFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      SubCardFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface SubCardFragmentSubcomponent extends AndroidInjector<SubCardFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SubCardFragment> {}
  }
}
