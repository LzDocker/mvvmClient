package com.docker.moduleplayer.di;

import android.app.Activity;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.index.PlayerMainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents = PlayerUiModule_ProviderPlayerHomeActivity.PlayerMainActivitySubcomponent.class
)
public abstract class PlayerUiModule_ProviderPlayerHomeActivity {
  private PlayerUiModule_ProviderPlayerHomeActivity() {}

  @Binds
  @IntoMap
  @ActivityKey(PlayerMainActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
      PlayerMainActivitySubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface PlayerMainActivitySubcomponent extends AndroidInjector<PlayerMainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PlayerMainActivity> {}
  }
}
