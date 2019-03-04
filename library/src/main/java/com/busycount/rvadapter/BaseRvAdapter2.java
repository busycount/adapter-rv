package com.busycount.rvadapter;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busycount.rvadapter.holder.InnerHolder;

/**
 * BaseRvAdapter1 endHolder
 * <p>
 * 2019/3/3 | Count.C | Created
 */
public abstract class BaseRvAdapter2<T> extends BaseRvAdapter0<T> {

    private static final int ITEM_TYPE_EMPTY = -4000;

    private int layoutId = -1;


    public BaseRvAdapter2() {
    }

    public BaseRvAdapter2(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + getEmptyCount();
    }

    private int getEmptyCount() {
        return getDataCount() == 0 && layoutId != -1 ? 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (getEmptyCount() == 1) {
            return ITEM_TYPE_EMPTY;
        } else {
            return getExtItemViewType(position);
        }
    }


    @NonNull
    @Override
    public BaseRvHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_EMPTY) {
            return new InnerHolder<>(LayoutInflater.from(parent.getContext())
                    .inflate(layoutId, parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }
}
