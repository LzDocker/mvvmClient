package com.docker.moduleplayer.di;

import android.app.Activity;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.common.KnowledgeDetialActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      PlayerUiModule_ProviderKnowledgeDetialActivity.KnowledgeDetialActivitySubcomponent.class
)
public abstract class PlayerUiModule_ProviderKnowledgeDetialActivity {
  private PlayerUiModule_ProviderKnowledgeDetialActivity() {}

  @Binds
  @IntoMap
  @ActivityKey(KnowledgeDetialActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
      KnowledgeDetialActivitySubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface KnowledgeDetialActivitySubcomponent
      extends AndroidInjector<KnowledgeDetialActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<KnowledgeDetialActivity> {}
  }
}
