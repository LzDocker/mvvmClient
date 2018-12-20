package com.docker.moduleplayer.di;

import android.support.v4.app.Fragment;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.moduleplayer.ui.knowledge.KnowledgeFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents = PlayerUiModule_ProviderKnowledgeFragment.KnowledgeFragmentSubcomponent.class
)
public abstract class PlayerUiModule_ProviderKnowledgeFragment {
  private PlayerUiModule_ProviderKnowledgeFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(KnowledgeFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      KnowledgeFragmentSubcomponent.Builder builder);

  @Subcomponent
  @ActivityScope
  public interface KnowledgeFragmentSubcomponent extends AndroidInjector<KnowledgeFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<KnowledgeFragment> {}
  }
}
