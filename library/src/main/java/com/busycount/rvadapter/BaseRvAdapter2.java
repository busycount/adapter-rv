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
public abstract class BaseRvAdapter2<T> extends BaseRvAdapter1<T> {

    private static final int ITEM_TYPE_EMPTY = -30000;
    private static final int ITEM_TYPE_LOAD_MORE = -40000;

    @Override
    public int getItemCount() {
        return super.getItemCount() + getEmptyCount() + getLoadMoreCount();
    }

    private int getEmptyCount() {
        return getDataCount() == 0 && hasEmptyHolder() ? 1 : 0;
    }

    private int getLoadMoreCount() {
        return getDataCount() != 0 && hasLoadMoreHolder() ? 1 : 0;
    }

    @Override
    public final int getItemViewType(int position) {
        int footStart = getDataCount() + getHeaderCount() + getEmptyCount();
        int footEnd = footStart + getFooterCount();
        if (position < getHeaderCount()) {
            return ITEM_TYPE_HEADER - position;
        } else if (position >= footEnd) {
            return ITEM_TYPE_LOAD_MORE;
        } else if (position >= footStart) {
            return ITEM_TYPE_FOOTER - position + footStart;
        } else {
            if (getDataCount() == 0) {
                return ITEM_TYPE_EMPTY;
            } else {
                return getExtItemViewType(0);
            }
        }
    }


    @NonNull
    @Override
    public BaseRvHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_EMPTY) {
            return onCreateEmptyHolder(parent);
        } else if (viewType == ITEM_TYPE_LOAD_MORE) {
            return onCreateLoadMoreHolder(parent);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    protected boolean hasEmptyHolder() {
        return false;
    }

    protected boolean hasLoadMoreHolder() {
        return false;
    }


    protected BaseRvHolder<T> onCreateEmptyHolder(@NonNull ViewGroup parent) {
        return null;
    }

    protected BaseRvHolder<T> onCreateLoadMoreHolder(@NonNull ViewGroup parent) {
        return null;
    }
}
