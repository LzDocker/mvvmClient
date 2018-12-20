package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.subscription.SubFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PlayerUiModule_ProviderSubFragment.SubFragmentSubcomponent.class)
public abstract class PlayerUiModule_ProviderSubFragment {
  private PlayerUiModule_ProviderSubFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(SubFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      SubFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface SubFragmentSubcomponent extends AndroidInjector<SubFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SubFragment> {}
  }
}
