package com.docker.moduleplayer.viewmodel;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PlayerhomeViewModel_Factory implements Factory<PlayerhomeViewModel> {
  private final MembersInjector<PlayerhomeViewModel> playerhomeViewModelMembersInjector;

  public PlayerhomeViewModel_Factory(
      MembersInjector<PlayerhomeViewModel> playerhomeViewModelMembersInjector) {
    assert playerhomeViewModelMembersInjector != null;
    this.playerhomeViewModelMembersInjector = playerhomeViewModelMembersInjector;
  }

  @Override
  public PlayerhomeViewModel get() {
    return MembersInjectors.injectMembers(
        playerhomeViewModelMembersInjector, new PlayerhomeViewModel());
  }

  public static Factory<PlayerhomeViewModel> create(
      MembersInjector<PlayerhomeViewModel> playerhomeViewModelMembersInjector) {
    return new PlayerhomeViewModel_Factory(playerhomeViewModelMembersInjector);
  }
}
