package com.busycount.rvadapter;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.busycount.rvadapter.holder.InnerHolder;

/**
 * BaseRvAdapter1
 * <p>
 * 2019/3/3 | Count.C | Created
 */
public abstract class BaseRvAdapter1<T> extends BaseRvAdapter0<T> {

    private final SparseArrayCompat<View> headerArray = new SparseArrayCompat<>();
    private final SparseArrayCompat<View> footerArray = new SparseArrayCompat<>();
    private static final int ITEM_TYPE_HEADER = -10000;
    private static final int ITEM_TYPE_FOOTER = -20000;


    @Override
    public int getItemCount() {
        return super.getItemCount() + getHeaderCount() + getFooterCount();
    }

    private int getHeaderCount() {
        return headerArray.size();
    }

    private int getFooterCount() {
        return footerArray.size();
    }

    @Override
    protected int findDataStart() {
        return getHeaderCount();
    }


    @NonNull
    @Override
    public BaseRvHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (headerArray.get(viewType) != null) {
            return new InnerHolder<>(headerArray.get(viewType));
        } else if (footerArray.get(viewType) != null) {
            return new InnerHolder<>(footerArray.get(viewType));
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int footStart = getDataCount() + getHeaderCount();
        if (position < getHeaderCount()) {
            return ITEM_TYPE_HEADER - position;
        } else if (position >= footStart) {
            return ITEM_TYPE_FOOTER - position + footStart;
        } else {
            return getExtItemViewType(position);
        }
    }

    public void addHeaderView(View view) {
        int index = getHeaderCount();
        headerArray.put(ITEM_TYPE_HEADER - getHeaderCount(), view);
//        notifyItemInserted(index);
        notifyDataSetChanged();
    }

    public void addFooterView(View view) {
        int index = getHeaderCount() + getDataCount() + getFooterCount();
        footerArray.put(ITEM_TYPE_FOOTER - getFooterCount(), view);
        notifyItemInserted(index);
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isDataItem(position)) {
                        return spanSizeLookup.getSpanSize(position);
                    } else {
                        return gridLayoutManager.getSpanCount();
                    }
                }
            });
        }
    }


    @Override
    public void onViewAttachedToWindow(@NonNull BaseRvHolder<T> holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (!isDataItem(position)) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params != null) {
                if (params instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) params;
                    p.setFullSpan(true);
                }
            }
        }
    }

}
