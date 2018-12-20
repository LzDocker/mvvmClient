package com.docker.moduleplayer.ui.common;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CommonFragment_MembersInjector implements MembersInjector<CommonFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public CommonFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<CommonFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new CommonFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(CommonFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      CommonFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
