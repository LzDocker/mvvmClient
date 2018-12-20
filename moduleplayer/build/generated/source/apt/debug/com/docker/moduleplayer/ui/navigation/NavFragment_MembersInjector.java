package com.docker.moduleplayer.ui.navigation;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NavFragment_MembersInjector implements MembersInjector<NavFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public NavFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<NavFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new NavFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(NavFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      NavFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
