package com.busycount.rvadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.busycount.rvadapter.click.OnRvItemClickListener;


/**
 * RecyclerView base holder
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public abstract class BaseRvHolder<T> extends RecyclerView.ViewHolder {

    private T iData;

    public BaseRvHolder(View itemView) {
        super(itemView);
    }

    public final void bindView(T data) {
        this.iData = data;
        onBindData(iData);
    }

    public void setClick(final OnRvItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(itemView, getAdapterPosition());
            }
        });
    }

    public abstract void onBindData(T data);

    public Context getContext() {
        return itemView.getContext();
    }

    public T getData() {
        return iData;
    }
}
