package com.docker.moduleplayer.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
@javax.annotation.Generated("Android Data Binding")
public abstract class ModuleplayerKonwledgeItemBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.TextView itemSearchPagerAuthor;
    @NonNull
    public final android.support.v7.widget.CardView itemSearchPagerGroup;
    // variables
    protected com.docker.moduleplayer.vo.KnowledgeHierarchyData mItem;
    protected ModuleplayerKonwledgeItemBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.TextView itemSearchPagerAuthor1
        , android.support.v7.widget.CardView itemSearchPagerGroup1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.itemSearchPagerAuthor = itemSearchPagerAuthor1;
        this.itemSearchPagerGroup = itemSearchPagerGroup1;
    }
    //getters and abstract setters
    public abstract void setItem(@Nullable com.docker.moduleplayer.vo.KnowledgeHierarchyData Item);
    @Nullable
    public com.docker.moduleplayer.vo.KnowledgeHierarchyData getItem() {
        return mItem;
    }
    @NonNull
    public static ModuleplayerKonwledgeItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerKonwledgeItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ModuleplayerKonwledgeItemBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ModuleplayerKonwledgeItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerKonwledgeItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ModuleplayerKonwledgeItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}