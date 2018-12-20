package com.docker.moduleplayer.viewmodel;

import com.docker.moduleplayer.api.PlayerService;
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

  public PlayerhomeViewModel_MembersInjector(Provider<PlayerService> serviceProvider) {
    assert serviceProvider != null;
    this.serviceProvider = serviceProvider;
  }

  public static MembersInjector<PlayerhomeViewModel> create(
      Provider<PlayerService> serviceProvider) {
    return new PlayerhomeViewModel_MembersInjector(serviceProvider);
  }

  @Override
  public void injectMembers(PlayerhomeViewModel instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.service = serviceProvider.get();
  }

  public static void injectService(
      PlayerhomeViewModel instance, Provider<PlayerService> serviceProvider) {
    instance.service = serviceProvider.get();
  }
}
