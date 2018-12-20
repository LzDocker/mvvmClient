package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.common.CommonFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = PlayerUiModule_ProviderCommonFragment.CommonFragmentSubcomponent.class)
public abstract class PlayerUiModule_ProviderCommonFragment {
  private PlayerUiModule_ProviderCommonFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(CommonFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      CommonFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface CommonFragmentSubcomponent extends AndroidInjector<CommonFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CommonFragment> {}
  }
}
