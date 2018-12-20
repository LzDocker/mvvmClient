package com.docker.moduleplayer.ui.subscription;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SubFragment_MembersInjector implements MembersInjector<SubFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public SubFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<SubFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new SubFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(SubFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      SubFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
