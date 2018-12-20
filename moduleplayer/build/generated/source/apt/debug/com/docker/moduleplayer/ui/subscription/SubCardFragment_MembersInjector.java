package com.docker.moduleplayer.ui.subscription;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SubCardFragment_MembersInjector implements MembersInjector<SubCardFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public SubCardFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<SubCardFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new SubCardFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(SubCardFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      SubCardFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
