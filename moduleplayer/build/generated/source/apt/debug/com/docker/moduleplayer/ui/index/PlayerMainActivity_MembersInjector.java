package com.docker.moduleplayer.ui.index;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PlayerMainActivity_MembersInjector
    implements MembersInjector<PlayerMainActivity> {
  private final Provider<DispatchingAndroidInjector<Fragment>> supportFragmentInjectorProvider;

  private final Provider<DispatchingAndroidInjector<android.app.Fragment>>
      frameworkFragmentInjectorProvider;

  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public PlayerMainActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Fragment>> supportFragmentInjectorProvider,
      Provider<DispatchingAndroidInjector<android.app.Fragment>> frameworkFragmentInjectorProvider,
      Provider<ViewModelProvider.Factory> factoryProvider) {
    assert supportFragmentInjectorProvider != null;
    this.supportFragmentInjectorProvider = supportFragmentInjectorProvider;
    assert frameworkFragmentInjectorProvider != null;
    this.frameworkFragmentInjectorProvider = frameworkFragmentInjectorProvider;
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<PlayerMainActivity> create(
      Provider<DispatchingAndroidInjector<Fragment>> supportFragmentInjectorProvider,
      Provider<DispatchingAndroidInjector<android.app.Fragment>> frameworkFragmentInjectorProvider,
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new PlayerMainActivity_MembersInjector(
        supportFragmentInjectorProvider, frameworkFragmentInjectorProvider, factoryProvider);
  }

  @Override
  public void injectMembers(PlayerMainActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    com.docker.commonlibrary.base.BaseInjectActivity_MembersInjector.injectSupportFragmentInjector(
        instance, supportFragmentInjectorProvider);
    com.docker.commonlibrary.base.BaseInjectActivity_MembersInjector
        .injectFrameworkFragmentInjector(instance, frameworkFragmentInjectorProvider);
    instance.factory = factoryProvider.get();
  }

  public static void injectFactory(
      PlayerMainActivity instance, Provider<ViewModelProvider.Factory> factoryProvider) {
    instance.factory = factoryProvider.get();
  }
}
