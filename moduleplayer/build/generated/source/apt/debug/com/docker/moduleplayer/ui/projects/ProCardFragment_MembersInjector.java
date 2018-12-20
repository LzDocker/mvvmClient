package com.docker.moduleplayer.ui.projects;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ProCardFragment_MembersInjector implements MembersInjector<ProCardFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public ProCardFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<ProCardFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new ProCardFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(ProCardFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      ProCardFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
