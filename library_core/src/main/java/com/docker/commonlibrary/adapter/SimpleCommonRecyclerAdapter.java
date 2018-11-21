package com.docker.commonlibrary.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * RecyclerView通用Adapter，可直接使用
 * Created by zhangshaofang on 2017/8/17.
 */

public class SimpleCommonRecyclerAdapter<T> extends CommonRecyclerAdapter<T, CommonRecyclerAdapter.ViewHolder<ViewDataBinding>> {

    private OnItemClickListener onItemClickListener;

    public SimpleCommonRecyclerAdapter(int layoutId, int brId) {
        super(layoutId, brId);
    }

    /**
     * 设置监听事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder<ViewDataBinding> getHolder(View view) {
        return new ViewHolder<ViewDataBinding>(view, onItemClickListener);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
