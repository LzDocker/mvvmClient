package com.docker.moduleplayer.ui.projects;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ProFragment_MembersInjector implements MembersInjector<ProFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public ProFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<ProFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new ProFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(ProFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      ProFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
