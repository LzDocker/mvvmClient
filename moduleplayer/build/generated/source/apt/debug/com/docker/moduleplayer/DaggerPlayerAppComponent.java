package com.docker.moduleplayer;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import com.docker.commonlibrary.BaseApplication;
import com.docker.commonlibrary.BaseApplication_MembersInjector;
import com.docker.commonlibrary.api.HttpRequestHandler;
import com.docker.commonlibrary.api.RequestInterceptor;
import com.docker.commonlibrary.api.RequestInterceptor_Factory;
import com.docker.commonlibrary.di.module.AppModule;
import com.docker.commonlibrary.di.module.AppModule_ProvideApplicationFactory;
import com.docker.commonlibrary.di.module.CacheModule;
import com.docker.commonlibrary.di.module.GlobalConfigModule;
import com.docker.commonlibrary.di.module.GlobalConfigModule_ProvideBaseUrlFactory;
import com.docker.commonlibrary.di.module.GlobalConfigModule_ProvideHttpRequestHandlerFactory;
import com.docker.commonlibrary.di.module.GlobalConfigModule_ProvideInterceptorsFactory;
import com.docker.commonlibrary.di.module.HttpClientModule;
import com.docker.commonlibrary.di.module.HttpClientModule_ProvideClientBuilderFactory;
import com.docker.commonlibrary.di.module.HttpClientModule_ProvideClientFactory;
import com.docker.commonlibrary.di.module.HttpClientModule_ProvideGsonFactory;
import com.docker.commonlibrary.di.module.HttpClientModule_ProvideInterceptFactory;
import com.docker.commonlibrary.di.module.HttpClientModule_ProvideRetrofitBuilderFactory;
import com.docker.commonlibrary.di.module.HttpClientModule_ProvideRetrofitFactory;
import com.docker.commonlibrary.di.module.HttpClientModule_ProviderCookieJarFactory;
import com.docker.commonlibrary.viewmodel.HivescmViewModelFactory;
import com.docker.commonlibrary.viewmodel.HivescmViewModelFactory_Factory;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.di.PlayerServiceModule;
import com.docker.moduleplayer.di.PlayerServiceModule_ProvidePlayerServiceModuleFactory;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderCommonFragment;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderKnowledgeDetialActivity;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderKnowledgeFragment;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderNavFragment;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderPlayerHomeActivity;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderPlayerIndexFragment;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderProCardFragment;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderProFragment;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderSubCardFragment;
import com.docker.moduleplayer.di.PlayerUiModule_ProviderSubFragment;
import com.docker.moduleplayer.ui.common.CommonFragment;
import com.docker.moduleplayer.ui.common.CommonFragment_MembersInjector;
import com.docker.moduleplayer.ui.common.KnowledgeDetialActivity;
import com.docker.moduleplayer.ui.common.KnowledgeDetialActivity_MembersInjector;
import com.docker.moduleplayer.ui.index.PlayerIndexFragment;
import com.docker.moduleplayer.ui.index.PlayerIndexFragment_MembersInjector;
import com.docker.moduleplayer.ui.index.PlayerMainActivity;
import com.docker.moduleplayer.ui.index.PlayerMainActivity_MembersInjector;
import com.docker.moduleplayer.ui.knowledge.KnowledgeFragment;
import com.docker.moduleplayer.ui.knowledge.KnowledgeFragment_MembersInjector;
import com.docker.moduleplayer.ui.navigation.NavFragment;
import com.docker.moduleplayer.ui.navigation.NavFragment_MembersInjector;
import com.docker.moduleplayer.ui.projects.ProCardFragment;
import com.docker.moduleplayer.ui.projects.ProCardFragment_MembersInjector;
import com.docker.moduleplayer.ui.projects.ProFragment;
import com.docker.moduleplayer.ui.projects.ProFragment_MembersInjector;
import com.docker.moduleplayer.ui.subscription.SubCardFragment;
import com.docker.moduleplayer.ui.subscription.SubCardFragment_MembersInjector;
import com.docker.moduleplayer.ui.subscription.SubFragment;
import com.docker.moduleplayer.ui.subscription.SubFragment_MembersInjector;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel_Factory;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel_MembersInjector;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapProviderFactory;
import dagger.internal.Preconditions;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerPlayerAppComponent implements PlayerAppComponent {
  private Provider<Gson> provideGsonProvider;

  private Provider<OkHttpClient.Builder> provideClientBuilderProvider;

  private Provider<HttpRequestHandler> provideHttpRequestHandlerProvider;

  private Provider<RequestInterceptor> requestInterceptorProvider;

  private Provider<Interceptor> provideInterceptProvider;

  private Provider<List<Interceptor>> provideInterceptorsProvider;

  private Provider<CookieJar> providerCookieJarProvider;

  private Provider<OkHttpClient> provideClientProvider;

  private Provider<BaseApplication> provideApplicationProvider;

  private Provider<PlayerUiModule_ProviderPlayerHomeActivity.PlayerMainActivitySubcomponent.Builder>
      playerMainActivitySubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends Activity>> bindAndroidInjectorFactoryProvider;

  private Provider<
          PlayerUiModule_ProviderKnowledgeDetialActivity.KnowledgeDetialActivitySubcomponent
              .Builder>
      knowledgeDetialActivitySubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends Activity>> bindAndroidInjectorFactoryProvider2;

  private Provider<
          Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>>>
      mapOfClassOfAndProviderOfFactoryOfProvider;

  private Provider<DispatchingAndroidInjector<Activity>> dispatchingAndroidInjectorProvider;

  private Provider<DispatchingAndroidInjector<Fragment>> dispatchingAndroidInjectorProvider2;

  private Provider<
          PlayerUiModule_ProviderPlayerIndexFragment.PlayerIndexFragmentSubcomponent.Builder>
      playerIndexFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider3;

  private Provider<PlayerUiModule_ProviderKnowledgeFragment.KnowledgeFragmentSubcomponent.Builder>
      knowledgeFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider4;

  private Provider<PlayerUiModule_ProviderSubFragment.SubFragmentSubcomponent.Builder>
      subFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider5;

  private Provider<PlayerUiModule_ProviderNavFragment.NavFragmentSubcomponent.Builder>
      navFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider6;

  private Provider<PlayerUiModule_ProviderProFragment.ProFragmentSubcomponent.Builder>
      proFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider7;

  private Provider<PlayerUiModule_ProviderCommonFragment.CommonFragmentSubcomponent.Builder>
      commonFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider8;

  private Provider<PlayerUiModule_ProviderSubCardFragment.SubCardFragmentSubcomponent.Builder>
      subCardFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider9;

  private Provider<PlayerUiModule_ProviderProCardFragment.ProCardFragmentSubcomponent.Builder>
      proCardFragmentSubcomponentBuilderProvider;

  private Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
      bindAndroidInjectorFactoryProvider10;

  private Provider<
          Map<
              Class<? extends android.support.v4.app.Fragment>,
              Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>>>
      mapOfClassOfAndProviderOfFactoryOfProvider2;

  private Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>>
      dispatchingAndroidInjectorProvider3;

  private Provider<DispatchingAndroidInjector<BroadcastReceiver>>
      dispatchingAndroidInjectorProvider4;

  private Provider<DispatchingAndroidInjector<Service>> dispatchingAndroidInjectorProvider5;

  private Provider<DispatchingAndroidInjector<ContentProvider>> dispatchingAndroidInjectorProvider6;

  private MembersInjector<BaseApplication> baseApplicationMembersInjector;

  private Provider<Retrofit.Builder> provideRetrofitBuilderProvider;

  private Provider<HttpUrl> provideBaseUrlProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<PlayerService> providePlayerServiceModuleProvider;

  private MembersInjector<PlayerhomeViewModel> playerhomeViewModelMembersInjector;

  private Provider<PlayerhomeViewModel> playerhomeViewModelProvider;

  private Provider<ViewModel> PlayerhomeViewModelProvider;

  private Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>>
      mapOfClassOfAndProviderOfViewModelProvider;

  private Provider<HivescmViewModelFactory> hivescmViewModelFactoryProvider;

  private DaggerPlayerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideGsonProvider =
        DoubleCheck.provider(HttpClientModule_ProvideGsonFactory.create(builder.httpClientModule));

    this.provideClientBuilderProvider =
        DoubleCheck.provider(
            HttpClientModule_ProvideClientBuilderFactory.create(builder.httpClientModule));

    this.provideHttpRequestHandlerProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideHttpRequestHandlerFactory.create(builder.globalConfigModule));

    this.requestInterceptorProvider =
        DoubleCheck.provider(RequestInterceptor_Factory.create(provideHttpRequestHandlerProvider));

    this.provideInterceptProvider =
        DoubleCheck.provider(
            HttpClientModule_ProvideInterceptFactory.create(
                builder.httpClientModule, requestInterceptorProvider));

    this.provideInterceptorsProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideInterceptorsFactory.create(builder.globalConfigModule));

    this.providerCookieJarProvider =
        DoubleCheck.provider(
            HttpClientModule_ProviderCookieJarFactory.create(builder.httpClientModule));

    this.provideClientProvider =
        DoubleCheck.provider(
            HttpClientModule_ProvideClientFactory.create(
                builder.httpClientModule,
                provideClientBuilderProvider,
                provideInterceptProvider,
                provideInterceptorsProvider,
                providerCookieJarProvider));

    this.provideApplicationProvider =
        DoubleCheck.provider(AppModule_ProvideApplicationFactory.create(builder.appModule));

    this.playerMainActivitySubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderPlayerHomeActivity.PlayerMainActivitySubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderPlayerHomeActivity.PlayerMainActivitySubcomponent.Builder
              get() {
            return new PlayerMainActivitySubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider =
        (Provider) playerMainActivitySubcomponentBuilderProvider;

    this.knowledgeDetialActivitySubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderKnowledgeDetialActivity.KnowledgeDetialActivitySubcomponent
                .Builder>() {
          @Override
          public PlayerUiModule_ProviderKnowledgeDetialActivity.KnowledgeDetialActivitySubcomponent
                  .Builder
              get() {
            return new KnowledgeDetialActivitySubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider2 =
        (Provider) knowledgeDetialActivitySubcomponentBuilderProvider;

    this.mapOfClassOfAndProviderOfFactoryOfProvider =
        MapProviderFactory
            .<Class<? extends Activity>, AndroidInjector.Factory<? extends Activity>>builder(2)
            .put(PlayerMainActivity.class, bindAndroidInjectorFactoryProvider)
            .put(KnowledgeDetialActivity.class, bindAndroidInjectorFactoryProvider2)
            .build();

    this.dispatchingAndroidInjectorProvider =
        DispatchingAndroidInjector_Factory.create(mapOfClassOfAndProviderOfFactoryOfProvider);

    this.dispatchingAndroidInjectorProvider2 =
        DispatchingAndroidInjector_Factory.create(
            MapProviderFactory
                .<Class<? extends Fragment>, AndroidInjector.Factory<? extends Fragment>>empty());

    this.playerIndexFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderPlayerIndexFragment.PlayerIndexFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderPlayerIndexFragment.PlayerIndexFragmentSubcomponent.Builder
              get() {
            return new PlayerIndexFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider3 =
        (Provider) playerIndexFragmentSubcomponentBuilderProvider;

    this.knowledgeFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderKnowledgeFragment.KnowledgeFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderKnowledgeFragment.KnowledgeFragmentSubcomponent.Builder
              get() {
            return new KnowledgeFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider4 =
        (Provider) knowledgeFragmentSubcomponentBuilderProvider;

    this.subFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderSubFragment.SubFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderSubFragment.SubFragmentSubcomponent.Builder get() {
            return new SubFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider5 = (Provider) subFragmentSubcomponentBuilderProvider;

    this.navFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderNavFragment.NavFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderNavFragment.NavFragmentSubcomponent.Builder get() {
            return new NavFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider6 = (Provider) navFragmentSubcomponentBuilderProvider;

    this.proFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderProFragment.ProFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderProFragment.ProFragmentSubcomponent.Builder get() {
            return new ProFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider7 = (Provider) proFragmentSubcomponentBuilderProvider;

    this.commonFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderCommonFragment.CommonFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderCommonFragment.CommonFragmentSubcomponent.Builder get() {
            return new CommonFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider8 = (Provider) commonFragmentSubcomponentBuilderProvider;

    this.subCardFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderSubCardFragment.SubCardFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderSubCardFragment.SubCardFragmentSubcomponent.Builder get() {
            return new SubCardFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider9 =
        (Provider) subCardFragmentSubcomponentBuilderProvider;

    this.proCardFragmentSubcomponentBuilderProvider =
        new dagger.internal.Factory<
            PlayerUiModule_ProviderProCardFragment.ProCardFragmentSubcomponent.Builder>() {
          @Override
          public PlayerUiModule_ProviderProCardFragment.ProCardFragmentSubcomponent.Builder get() {
            return new ProCardFragmentSubcomponentBuilder();
          }
        };

    this.bindAndroidInjectorFactoryProvider10 =
        (Provider) proCardFragmentSubcomponentBuilderProvider;

    this.mapOfClassOfAndProviderOfFactoryOfProvider2 =
        MapProviderFactory
            .<Class<? extends android.support.v4.app.Fragment>,
                AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>
                builder(8)
            .put(PlayerIndexFragment.class, bindAndroidInjectorFactoryProvider3)
            .put(KnowledgeFragment.class, bindAndroidInjectorFactoryProvider4)
            .put(SubFragment.class, bindAndroidInjectorFactoryProvider5)
            .put(NavFragment.class, bindAndroidInjectorFactoryProvider6)
            .put(ProFragment.class, bindAndroidInjectorFactoryProvider7)
            .put(CommonFragment.class, bindAndroidInjectorFactoryProvider8)
            .put(SubCardFragment.class, bindAndroidInjectorFactoryProvider9)
            .put(ProCardFragment.class, bindAndroidInjectorFactoryProvider10)
            .build();

    this.dispatchingAndroidInjectorProvider3 =
        DispatchingAndroidInjector_Factory.create(mapOfClassOfAndProviderOfFactoryOfProvider2);

    this.dispatchingAndroidInjectorProvider4 =
        DispatchingAndroidInjector_Factory.create(
            MapProviderFactory
                .<Class<? extends BroadcastReceiver>,
                    AndroidInjector.Factory<? extends BroadcastReceiver>>
                    empty());

    this.dispatchingAndroidInjectorProvider5 =
        DispatchingAndroidInjector_Factory.create(
            MapProviderFactory
                .<Class<? extends Service>, AndroidInjector.Factory<? extends Service>>empty());

    this.dispatchingAndroidInjectorProvider6 =
        DispatchingAndroidInjector_Factory.create(
            MapProviderFactory
                .<Class<? extends ContentProvider>,
                    AndroidInjector.Factory<? extends ContentProvider>>
                    empty());

    this.baseApplicationMembersInjector =
        BaseApplication_MembersInjector.create(
            dispatchingAndroidInjectorProvider,
            dispatchingAndroidInjectorProvider2,
            dispatchingAndroidInjectorProvider3,
            dispatchingAndroidInjectorProvider4,
            dispatchingAndroidInjectorProvider5,
            dispatchingAndroidInjectorProvider6);

    this.provideRetrofitBuilderProvider =
        DoubleCheck.provider(
            HttpClientModule_ProvideRetrofitBuilderFactory.create(builder.httpClientModule));

    this.provideBaseUrlProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideBaseUrlFactory.create(builder.globalConfigModule));

    this.provideRetrofitProvider =
        DoubleCheck.provider(
            HttpClientModule_ProvideRetrofitFactory.create(
                builder.httpClientModule,
                provideRetrofitBuilderProvider,
                provideClientProvider,
                provideBaseUrlProvider));

    this.providePlayerServiceModuleProvider =
        DoubleCheck.provider(
            PlayerServiceModule_ProvidePlayerServiceModuleFactory.create(
                builder.playerServiceModule, provideRetrofitProvider));

    this.playerhomeViewModelMembersInjector =
        PlayerhomeViewModel_MembersInjector.create(providePlayerServiceModuleProvider);

    this.playerhomeViewModelProvider =
        PlayerhomeViewModel_Factory.create(playerhomeViewModelMembersInjector);

    this.PlayerhomeViewModelProvider = (Provider) playerhomeViewModelProvider;

    this.mapOfClassOfAndProviderOfViewModelProvider =
        MapProviderFactory.<Class<? extends ViewModel>, ViewModel>builder(1)
            .put(PlayerhomeViewModel.class, PlayerhomeViewModelProvider)
            .build();

    this.hivescmViewModelFactoryProvider =
        DoubleCheck.provider(
            HivescmViewModelFactory_Factory.create(mapOfClassOfAndProviderOfViewModelProvider));
  }

  @Override
  public Gson gson() {
    return provideGsonProvider.get();
  }

  @Override
  public OkHttpClient okHttpClient() {
    return provideClientProvider.get();
  }

  @Override
  public BaseApplication baseApplication() {
    return provideApplicationProvider.get();
  }

  @Override
  public void inject(BaseApplication application) {
    baseApplicationMembersInjector.injectMembers(application);
  }

  public static final class Builder {
    private HttpClientModule httpClientModule;

    private GlobalConfigModule globalConfigModule;

    private AppModule appModule;

    private PlayerServiceModule playerServiceModule;

    private Builder() {}

    public PlayerAppComponent build() {
      if (httpClientModule == null) {
        throw new IllegalStateException(HttpClientModule.class.getCanonicalName() + " must be set");
      }
      if (globalConfigModule == null) {
        throw new IllegalStateException(
            GlobalConfigModule.class.getCanonicalName() + " must be set");
      }
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      if (playerServiceModule == null) {
        this.playerServiceModule = new PlayerServiceModule();
      }
      return new DaggerPlayerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder httpClientModule(HttpClientModule httpClientModule) {
      this.httpClientModule = Preconditions.checkNotNull(httpClientModule);
      return this;
    }

    public Builder globalConfigModule(GlobalConfigModule globalConfigModule) {
      this.globalConfigModule = Preconditions.checkNotNull(globalConfigModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder cacheModule(CacheModule cacheModule) {
      Preconditions.checkNotNull(cacheModule);
      return this;
    }

    public Builder playerServiceModule(PlayerServiceModule playerServiceModule) {
      this.playerServiceModule = Preconditions.checkNotNull(playerServiceModule);
      return this;
    }
  }

  private final class PlayerMainActivitySubcomponentBuilder
      extends PlayerUiModule_ProviderPlayerHomeActivity.PlayerMainActivitySubcomponent.Builder {
    private PlayerMainActivity seedInstance;

    @Override
    public PlayerUiModule_ProviderPlayerHomeActivity.PlayerMainActivitySubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(
            PlayerMainActivity.class.getCanonicalName() + " must be set");
      }
      return new PlayerMainActivitySubcomponentImpl(this);
    }

    @Override
    public void seedInstance(PlayerMainActivity arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class PlayerMainActivitySubcomponentImpl
      implements PlayerUiModule_ProviderPlayerHomeActivity.PlayerMainActivitySubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<PlayerMainActivity> playerMainActivityMembersInjector;

    private PlayerMainActivitySubcomponentImpl(PlayerMainActivitySubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final PlayerMainActivitySubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.playerMainActivityMembersInjector =
          PlayerMainActivity_MembersInjector.create(
              DaggerPlayerAppComponent.this.dispatchingAndroidInjectorProvider3,
              DaggerPlayerAppComponent.this.dispatchingAndroidInjectorProvider2,
              bindViewModelFactoryProvider);
    }

    @Override
    public void inject(PlayerMainActivity arg0) {
      playerMainActivityMembersInjector.injectMembers(arg0);
    }
  }

  private final class KnowledgeDetialActivitySubcomponentBuilder
      extends PlayerUiModule_ProviderKnowledgeDetialActivity.KnowledgeDetialActivitySubcomponent
          .Builder {
    private KnowledgeDetialActivity seedInstance;

    @Override
    public PlayerUiModule_ProviderKnowledgeDetialActivity.KnowledgeDetialActivitySubcomponent
        build() {
      if (seedInstance == null) {
        throw new IllegalStateException(
            KnowledgeDetialActivity.class.getCanonicalName() + " must be set");
      }
      return new KnowledgeDetialActivitySubcomponentImpl(this);
    }

    @Override
    public void seedInstance(KnowledgeDetialActivity arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class KnowledgeDetialActivitySubcomponentImpl
      implements PlayerUiModule_ProviderKnowledgeDetialActivity
          .KnowledgeDetialActivitySubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<KnowledgeDetialActivity> knowledgeDetialActivityMembersInjector;

    private KnowledgeDetialActivitySubcomponentImpl(
        KnowledgeDetialActivitySubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final KnowledgeDetialActivitySubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.knowledgeDetialActivityMembersInjector =
          KnowledgeDetialActivity_MembersInjector.create(
              DaggerPlayerAppComponent.this.dispatchingAndroidInjectorProvider3,
              DaggerPlayerAppComponent.this.dispatchingAndroidInjectorProvider2,
              bindViewModelFactoryProvider);
    }

    @Override
    public void inject(KnowledgeDetialActivity arg0) {
      knowledgeDetialActivityMembersInjector.injectMembers(arg0);
    }
  }

  private final class PlayerIndexFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderPlayerIndexFragment.PlayerIndexFragmentSubcomponent.Builder {
    private PlayerIndexFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderPlayerIndexFragment.PlayerIndexFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(
            PlayerIndexFragment.class.getCanonicalName() + " must be set");
      }
      return new PlayerIndexFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(PlayerIndexFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class PlayerIndexFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderPlayerIndexFragment.PlayerIndexFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<PlayerIndexFragment> playerIndexFragmentMembersInjector;

    private PlayerIndexFragmentSubcomponentImpl(PlayerIndexFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final PlayerIndexFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.playerIndexFragmentMembersInjector =
          PlayerIndexFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(PlayerIndexFragment arg0) {
      playerIndexFragmentMembersInjector.injectMembers(arg0);
    }
  }

  private final class KnowledgeFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderKnowledgeFragment.KnowledgeFragmentSubcomponent.Builder {
    private KnowledgeFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderKnowledgeFragment.KnowledgeFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(
            KnowledgeFragment.class.getCanonicalName() + " must be set");
      }
      return new KnowledgeFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(KnowledgeFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class KnowledgeFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderKnowledgeFragment.KnowledgeFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<KnowledgeFragment> knowledgeFragmentMembersInjector;

    private KnowledgeFragmentSubcomponentImpl(KnowledgeFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final KnowledgeFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.knowledgeFragmentMembersInjector =
          KnowledgeFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(KnowledgeFragment arg0) {
      knowledgeFragmentMembersInjector.injectMembers(arg0);
    }
  }

  private final class SubFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderSubFragment.SubFragmentSubcomponent.Builder {
    private SubFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderSubFragment.SubFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(SubFragment.class.getCanonicalName() + " must be set");
      }
      return new SubFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(SubFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class SubFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderSubFragment.SubFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<SubFragment> subFragmentMembersInjector;

    private SubFragmentSubcomponentImpl(SubFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SubFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.subFragmentMembersInjector =
          SubFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(SubFragment arg0) {
      subFragmentMembersInjector.injectMembers(arg0);
    }
  }

  private final class NavFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderNavFragment.NavFragmentSubcomponent.Builder {
    private NavFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderNavFragment.NavFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(NavFragment.class.getCanonicalName() + " must be set");
      }
      return new NavFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(NavFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class NavFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderNavFragment.NavFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<NavFragment> navFragmentMembersInjector;

    private NavFragmentSubcomponentImpl(NavFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final NavFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.navFragmentMembersInjector =
          NavFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(NavFragment arg0) {
      navFragmentMembersInjector.injectMembers(arg0);
    }
  }

  private final class ProFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderProFragment.ProFragmentSubcomponent.Builder {
    private ProFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderProFragment.ProFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(ProFragment.class.getCanonicalName() + " must be set");
      }
      return new ProFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(ProFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class ProFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderProFragment.ProFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<ProFragment> proFragmentMembersInjector;

    private ProFragmentSubcomponentImpl(ProFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ProFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.proFragmentMembersInjector =
          ProFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(ProFragment arg0) {
      proFragmentMembersInjector.injectMembers(arg0);
    }
  }

  private final class CommonFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderCommonFragment.CommonFragmentSubcomponent.Builder {
    private CommonFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderCommonFragment.CommonFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(CommonFragment.class.getCanonicalName() + " must be set");
      }
      return new CommonFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(CommonFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class CommonFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderCommonFragment.CommonFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<CommonFragment> commonFragmentMembersInjector;

    private CommonFragmentSubcomponentImpl(CommonFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final CommonFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.commonFragmentMembersInjector =
          CommonFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(CommonFragment arg0) {
      commonFragmentMembersInjector.injectMembers(arg0);
    }
  }

  private final class SubCardFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderSubCardFragment.SubCardFragmentSubcomponent.Builder {
    private SubCardFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderSubCardFragment.SubCardFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(SubCardFragment.class.getCanonicalName() + " must be set");
      }
      return new SubCardFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(SubCardFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class SubCardFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderSubCardFragment.SubCardFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<SubCardFragment> subCardFragmentMembersInjector;

    private SubCardFragmentSubcomponentImpl(SubCardFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SubCardFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.subCardFragmentMembersInjector =
          SubCardFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(SubCardFragment arg0) {
      subCardFragmentMembersInjector.injectMembers(arg0);
    }
  }

  private final class ProCardFragmentSubcomponentBuilder
      extends PlayerUiModule_ProviderProCardFragment.ProCardFragmentSubcomponent.Builder {
    private ProCardFragment seedInstance;

    @Override
    public PlayerUiModule_ProviderProCardFragment.ProCardFragmentSubcomponent build() {
      if (seedInstance == null) {
        throw new IllegalStateException(ProCardFragment.class.getCanonicalName() + " must be set");
      }
      return new ProCardFragmentSubcomponentImpl(this);
    }

    @Override
    public void seedInstance(ProCardFragment arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }
  }

  private final class ProCardFragmentSubcomponentImpl
      implements PlayerUiModule_ProviderProCardFragment.ProCardFragmentSubcomponent {
    private Provider<ViewModelProvider.Factory> bindViewModelFactoryProvider;

    private MembersInjector<ProCardFragment> proCardFragmentMembersInjector;

    private ProCardFragmentSubcomponentImpl(ProCardFragmentSubcomponentBuilder builder) {
      assert builder != null;
      initialize(builder);
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ProCardFragmentSubcomponentBuilder builder) {

      this.bindViewModelFactoryProvider =
          (Provider) DaggerPlayerAppComponent.this.hivescmViewModelFactoryProvider;

      this.proCardFragmentMembersInjector =
          ProCardFragment_MembersInjector.create(bindViewModelFactoryProvider);
    }

    @Override
    public void inject(ProCardFragment arg0) {
      proCardFragmentMembersInjector.injectMembers(arg0);
    }
  }
}
