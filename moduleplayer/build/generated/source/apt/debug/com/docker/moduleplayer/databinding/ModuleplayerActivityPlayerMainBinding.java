package com.docker.moduleplayer.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
@javax.annotation.Generated("Android Data Binding")
public abstract class ModuleplayerActivityPlayerMainBinding extends ViewDataBinding {
    @NonNull
    public final android.support.design.widget.AppBarLayout appbar;
    @NonNull
    public final com.docker.moduleplayer.widget.banner.BannerView bannerView;
    @NonNull
    public final android.support.design.widget.CollapsingToolbarLayout collapsingToolbar;
    @NonNull
    public final android.support.design.widget.FloatingActionButton floatingActionButton;
    @NonNull
    public final android.support.design.widget.CoordinatorLayout mainContent;
    @NonNull
    public final com.jcodecraeer.xrecyclerview.XRecyclerView recycle;
    // variables
    protected ModuleplayerActivityPlayerMainBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.support.design.widget.AppBarLayout appbar1
        , com.docker.moduleplayer.widget.banner.BannerView bannerView1
        , android.support.design.widget.CollapsingToolbarLayout collapsingToolbar1
        , android.support.design.widget.FloatingActionButton floatingActionButton1
        , android.support.design.widget.CoordinatorLayout mainContent1
        , com.jcodecraeer.xrecyclerview.XRecyclerView recycle1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.appbar = appbar1;
        this.bannerView = bannerView1;
        this.collapsingToolbar = collapsingToolbar1;
        this.floatingActionButton = floatingActionButton1;
        this.mainContent = mainContent1;
        this.recycle = recycle1;
    }
    //getters and abstract setters
    @NonNull
    public static ModuleplayerActivityPlayerMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerActivityPlayerMainBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerActivityPlayerMainBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ModuleplayerActivityPlayerMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerActivityPlayerMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerActivityPlayerMainBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}