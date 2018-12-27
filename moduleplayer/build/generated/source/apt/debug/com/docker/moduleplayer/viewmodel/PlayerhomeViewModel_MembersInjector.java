package com.docker.moduleplayer.viewmodel;

import com.docker.commonlibrary.util.AppExecutors;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.repository.PlayerRepository;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PlayerhomeViewModel_MembersInjector
    implements MembersInjector<PlayerhomeViewModel> {
  private final Provider<PlayerService> serviceProvider;

  private final Provider<PlayerRepository> playerRepositoryProvider;

  private final Provider<AppExecutors> appExecutorsProvider;

  public PlayerhomeViewModel_MembersInjector(
      Provider<PlayerService> serviceProvider,
      Provider<PlayerRepository> playerRepositoryProvider,
      Provider<AppExecutors> appExecutorsProvider) {
    assert serviceProvider != null;
    this.serviceProvider = serviceProvider;
    assert playerRepositoryProvider != null;
    this.playerRepositoryProvider = playerRepositoryProvider;
    assert appExecutorsProvider != null;
    this.appExecutorsProvider = appExecutorsProvider;
  }

  public static MembersInjector<PlayerhomeViewModel> create(
      Provider<PlayerService> serviceProvider,
      Provider<PlayerRepository> playerRepositoryProvider,
      Provider<AppExecutors> appExecutorsProvider) {
    return new PlayerhomeViewModel_MembersInjector(
        serviceProvider, playerRepositoryProvider, appExecutorsProvider);
  }

  @Override
  public void injectMembers(PlayerhomeViewModel instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.service = serviceProvider.get();
    instance.playerRepository = playerRepositoryProvider.get();
    instance.appExecutors = appExecutorsProvider.get();
  }

  public static void injectService(
      PlayerhomeViewModel instance, Provider<PlayerService> serviceProvider) {
    instance.service = serviceProvider.get();
  }

  public static void injectPlayerRepository(
      PlayerhomeViewModel instance, Provider<PlayerRepository> playerRepositoryProvider) {
    instance.playerRepository = playerRepositoryProvider.get();
  }

  public static void injectAppExecutors(
      PlayerhomeViewModel instance, Provider<AppExecutors> appExecutorsProvider) {
    instance.appExecutors = appExecutorsProvider.get();
  }
}
