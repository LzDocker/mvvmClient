// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.docker.moduleplayer.ui.subscription;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

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
