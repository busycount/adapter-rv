package com.busycount.rvadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * RecyclerView base holder
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public abstract class BaseRvHolder<T> extends RecyclerView.ViewHolder {

    private T hData;

    public BaseRvHolder(View itemView) {
        super(itemView);
    }

    public final void bindView(T data) {
        this.hData = data;
        onBindData(hData);
    }

    public abstract void onBindData(T hData);

    public Context getContext() {
        return itemView.getContext();
    }

    public T getData() {
        return hData;
    }
}
