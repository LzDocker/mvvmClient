// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.docker.moduleplayer.di;

import com.docker.moduleplayer.api.PlayerService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class PlayerServiceModule_ProvidePlayerServiceModuleFactory
    implements Factory<PlayerService> {
  private final PlayerServiceModule module;

  private final Provider<Retrofit> retrofitProvider;

  public PlayerServiceModule_ProvidePlayerServiceModuleFactory(
      PlayerServiceModule module, Provider<Retrofit> retrofitProvider) {
    assert module != null;
    this.module = module;
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PlayerService get() {
    return Preconditions.checkNotNull(
        module.providePlayerServiceModule(retrofitProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PlayerService> create(
      PlayerServiceModule module, Provider<Retrofit> retrofitProvider) {
    return new PlayerServiceModule_ProvidePlayerServiceModuleFactory(module, retrofitProvider);
  }

  /** Proxies {@link PlayerServiceModule#providePlayerServiceModule(Retrofit)}. */
  public static PlayerService proxyProvidePlayerServiceModule(
      PlayerServiceModule instance, Retrofit retrofit) {
    return instance.providePlayerServiceModule(retrofit);
  }
}
