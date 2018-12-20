package com.docker.moduleplayer.ui.knowledge;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class KnowledgeFragment_MembersInjector implements MembersInjector<KnowledgeFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public KnowledgeFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<KnowledgeFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new KnowledgeFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(KnowledgeFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      KnowledgeFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
