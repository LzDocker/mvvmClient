package com.docker.moduleplayer.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
@javax.annotation.Generated("Android Data Binding")
public abstract class ModuleplayerItemNavBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.TextView itemSearchPagerAuthor;
    @NonNull
    public final android.widget.TextView itemSearchPagerChapterName;
    @NonNull
    public final android.support.v7.widget.CardView itemSearchPagerGroup;
    @NonNull
    public final android.widget.ImageView itemSearchPagerLikeIv;
    @NonNull
    public final android.widget.TextView itemSearchPagerNiceDate;
    @NonNull
    public final android.widget.TextView itemSearchPagerTagGreenTv;
    @NonNull
    public final android.widget.TextView itemSearchPagerTagRedTv;
    @NonNull
    public final android.widget.TextView itemSearchPagerTitle;
    @NonNull
    public final android.widget.LinearLayout itemSearchTagGroup;
    // variables
    protected com.docker.moduleplayer.vo.FeedArticleData mItem;
    protected ModuleplayerItemNavBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.TextView itemSearchPagerAuthor1
        , android.widget.TextView itemSearchPagerChapterName1
        , android.support.v7.widget.CardView itemSearchPagerGroup1
        , android.widget.ImageView itemSearchPagerLikeIv1
        , android.widget.TextView itemSearchPagerNiceDate1
        , android.widget.TextView itemSearchPagerTagGreenTv1
        , android.widget.TextView itemSearchPagerTagRedTv1
        , android.widget.TextView itemSearchPagerTitle1
        , android.widget.LinearLayout itemSearchTagGroup1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.itemSearchPagerAuthor = itemSearchPagerAuthor1;
        this.itemSearchPagerChapterName = itemSearchPagerChapterName1;
        this.itemSearchPagerGroup = itemSearchPagerGroup1;
        this.itemSearchPagerLikeIv = itemSearchPagerLikeIv1;
        this.itemSearchPagerNiceDate = itemSearchPagerNiceDate1;
        this.itemSearchPagerTagGreenTv = itemSearchPagerTagGreenTv1;
        this.itemSearchPagerTagRedTv = itemSearchPagerTagRedTv1;
        this.itemSearchPagerTitle = itemSearchPagerTitle1;
        this.itemSearchTagGroup = itemSearchTagGroup1;
    }
    //getters and abstract setters
    public abstract void setItem(@Nullable com.docker.moduleplayer.vo.FeedArticleData Item);
    @Nullable
    public com.docker.moduleplayer.vo.FeedArticleData getItem() {
        return mItem;
    }
    @NonNull
    public static ModuleplayerItemNavBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerItemNavBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerItemNavBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ModuleplayerItemNavBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerItemNavBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerItemNavBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}