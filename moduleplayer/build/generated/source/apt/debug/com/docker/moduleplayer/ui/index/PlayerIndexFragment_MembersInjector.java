package com.docker.moduleplayer.ui.index;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PlayerIndexFragment_MembersInjector
    implements MembersInjector<PlayerIndexFragment> {
  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public PlayerIndexFragment_MembersInjector(Provider<ViewModelProvider.Factory> factoryProvider) {
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<PlayerIndexFragment> create(
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new PlayerIndexFragment_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(PlayerIndexFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      PlayerIndexFragment instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
