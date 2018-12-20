package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.index.PlayerIndexFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents = PlayerUiModule_ProviderPlayerIndexFragment.PlayerIndexFragmentSubcomponent.class
)
public abstract class PlayerUiModule_ProviderPlayerIndexFragment {
  private PlayerUiModule_ProviderPlayerIndexFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(PlayerIndexFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      PlayerIndexFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface PlayerIndexFragmentSubcomponent extends AndroidInjector<PlayerIndexFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PlayerIndexFragment> {}
  }
}
