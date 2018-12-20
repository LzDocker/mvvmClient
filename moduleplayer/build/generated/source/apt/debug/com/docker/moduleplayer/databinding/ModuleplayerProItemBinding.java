package com.docker.moduleplayer.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
@javax.annotation.Generated("Android Data Binding")
public abstract class ModuleplayerProItemBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.TextView itemProjectListAuthorTv;
    @NonNull
    public final android.widget.TextView itemProjectListContentTv;
    @NonNull
    public final android.widget.ImageView itemProjectListIv;
    @NonNull
    public final android.widget.TextView itemProjectListTimeTv;
    @NonNull
    public final android.widget.TextView itemProjectListTitleTv;
    @NonNull
    public final android.support.v7.widget.CardView itemSearchPagerGroup;
    // variables
    protected com.docker.moduleplayer.vo.FeedArticleData mItem;
    protected ModuleplayerProItemBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.TextView itemProjectListAuthorTv1
        , android.widget.TextView itemProjectListContentTv1
        , android.widget.ImageView itemProjectListIv1
        , android.widget.TextView itemProjectListTimeTv1
        , android.widget.TextView itemProjectListTitleTv1
        , android.support.v7.widget.CardView itemSearchPagerGroup1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.itemProjectListAuthorTv = itemProjectListAuthorTv1;
        this.itemProjectListContentTv = itemProjectListContentTv1;
        this.itemProjectListIv = itemProjectListIv1;
        this.itemProjectListTimeTv = itemProjectListTimeTv1;
        this.itemProjectListTitleTv = itemProjectListTitleTv1;
        this.itemSearchPagerGroup = itemSearchPagerGroup1;
    }
    //getters and abstract setters
    public abstract void setItem(@Nullable com.docker.moduleplayer.vo.FeedArticleData Item);
    @Nullable
    public com.docker.moduleplayer.vo.FeedArticleData getItem() {
        return mItem;
    }
    @NonNull
    public static ModuleplayerProItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerProItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerProItemBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ModuleplayerProItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerProItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerProItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}