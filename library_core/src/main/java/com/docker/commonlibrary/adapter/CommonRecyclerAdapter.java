package com.docker.commonlibrary.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * RecyclerView通用Adapter，可继承扩展
 * Created by zhangshaofang on 2017/7/5.
 */

public abstract class CommonRecyclerAdapter<T, H extends CommonRecyclerAdapter.ViewHolder> extends RecyclerView.Adapter<H> {
    private Context context;
    private List<T> mObjects;
    private boolean mNotifyOnChange = true;
    private int mLayoutId;
    protected int mBrId;

    public CommonRecyclerAdapter(int layoutId, int brId) {
        this(layoutId, brId, null);
    }

    public CommonRecyclerAdapter(Context context, int layoutId, int brId, Collection<T> objects) {
        this(layoutId, brId, objects);
        this.context = context;
    }

    public CommonRecyclerAdapter(int layoutId, int brId, Collection<T> objects) {
        this.mLayoutId = layoutId;
        this.mBrId = brId;
        mObjects = new ArrayList<>();
        if (objects != null) {
            mObjects.addAll(objects);
        }
    }


    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, mLayoutId, parent, false);
        H viewHolder = getHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerAdapter.ViewHolder holder, int position) {
        holder.getBinding().setVariable(mBrId, getItem(position));
        holder.getBinding().executePendingBindings();
    }

    public abstract H getHolder(View view);

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public T getItem(int position) {
        return mObjects.get(position);
    }

    public int getPosition(T item) {
        return mObjects.indexOf(item);
    }

    public static class ViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private SimpleCommonRecyclerAdapter.OnItemClickListener onItemClickListener;

        private B binding;

        public B getBinding() {
            return binding;
        }


        public void setBinding(B binding) {
            this.binding = binding;
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(View itemView, SimpleCommonRecyclerAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            if (onItemClickListener != null) {
                itemView.setOnClickListener(v -> {
                    onItemClickListener.onItemClick(v, getAdapterPosition());
                });
            }
        }
    }

    public List<T> getmObjects() {
        return mObjects;
    }

    public void add(Collection<T> objects) {
        mObjects.addAll(objects);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void replace(Collection<T> objects) {
        mObjects.clear();
        mObjects.addAll(objects);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void add(T[] objects) {
        mObjects.addAll(Arrays.asList(objects));
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void add(T object) {
        mObjects.add(object);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void insert(T object, int index) {
        mObjects.add(index, object);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     */
    public void remove(T object) {

        mObjects.remove(object);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public T remove(int position) {
        T t = mObjects.remove(position);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
        return t;
    }

    public void remove(Collection<T> collection) {

        mObjects.removeAll(collection);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    /**
     * Remove all elements from the list.
     */
    public void clear() {
        mObjects.clear();
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void sort(Comparator<? super T> comparator) {
        Collections.sort(mObjects, comparator);
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    public Context getContext() {
        if (context != null) {
            return context;
        } else {
            throw new IllegalArgumentException("请实现带Context的构造方法");
        }
    }

    public int getmLayoutId() {
        return mLayoutId;
    }
}
