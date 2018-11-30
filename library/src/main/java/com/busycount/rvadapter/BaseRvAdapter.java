package com.busycount.rvadapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;


import com.busycount.rvadapter.click.OnRvItemClickListener;
import com.busycount.rvadapter.click.OnRvItemLongClickListener;
import com.busycount.rvadapter.click.RvClickHelper;

import java.util.List;


/**
 * RecyclerView base adapter
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseRvHolder<T>> {

    protected List<T> rvData;

    private final SparseArrayCompat<View> headerArray = new SparseArrayCompat<>();
    private final SparseArrayCompat<View> footerArray = new SparseArrayCompat<>();
    private static final int ITEM_TYPE_HEADER = -10000;
    private static final int ITEM_TYPE_FOOTER = -20000;

    private final RvClickHelper rvClickHelper;

    public BaseRvAdapter() {
        rvClickHelper = new RvClickHelper();
    }


    @Override
    public final BaseRvHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRvHolder<T> rvHolder;
        if (headerArray.get(viewType) != null) {
            rvHolder = new InnerHolder(headerArray.get(viewType));
        } else if (footerArray.get(viewType) != null) {
            rvHolder = new InnerHolder(footerArray.get(viewType));
        } else {
            rvHolder = onExtCreateViewHolder(parent, viewType);
        }
        rvClickHelper.setupClick(rvHolder);
        return rvHolder;
    }

    public abstract BaseRvHolder<T> onExtCreateViewHolder(ViewGroup parent, int viewType);


    @Override
    public final int getItemViewType(int position) {
        int footStart = getDataCount() + getHeaderCount();
        if (position < getHeaderCount()) {
            return ITEM_TYPE_HEADER - position;
        } else if (position >= footStart) {
            return ITEM_TYPE_FOOTER - position + footStart;
        } else {
            return getExtItemViewType(position);
        }
    }

    public int getExtItemViewType(int position) {
        return 0;
    }

    @Override
    public final int getItemCount() {
        return getHeaderCount() + getDataCount() + getFooterCount();
    }

    @Override
    public void onBindViewHolder(BaseRvHolder<T> holder, int position, List<Object> payloads) {
        if (isDataItem(position)) {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public void onBindViewHolder(BaseRvHolder<T> holder, int position) {
        rvClickHelper.configClick(holder, position);
        if (isDataItem(position)) {
            holder.bindView(rvData.get(position - getHeaderCount()));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
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
    public void onViewAttachedToWindow(BaseRvHolder<T> holder) {
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

    public final void addData(List<T> data) {
        addData(data, -1);
    }

    /**
     * 指定位置添加数据
     *
     * @param data  data
     * @param index 位置
     */
    public final void addData(List<T> data, int index) {
        if (IList.isEmpty(data)) {
            return;
        }
        if (IList.isEmpty(rvData)) {
            rvData = data;
            notifyDataSetChanged();
        } else {
            index = index < 0 ? rvData.size() : index;
            rvData.addAll(index, data);
            index += getHeaderCount();
            notifyItemRangeInserted(index, data.size());
        }
    }

    /**
     * 更新数据
     *
     * @param data data
     */
    public final void updateData(List<T> data) {
        setData(data, true);
    }

    /**
     * 更新数据
     *
     * @param data     data
     * @param isNotify 是否主动刷新数据
     */
    public final void setData(List<T> data, boolean isNotify) {
        rvData = data;
        if (isNotify) {
            notifyDataSetChanged();
        }
    }

    /**
     * RecyclerView setAdapter 之后替换数据建议setData(data,true)
     *
     * @param aData data
     */
    public final void setData(List<T> aData) {
        setData(aData, false);
    }

    public final List<T> getData() {
        return rvData;
    }

    public T getItemData(int position) {
        if (isDataItem(position)) {
            return rvData.get(position - getHeaderCount());
        } else {
            return null;
        }
    }

    public final int getDataCount() {
        return IList.getSize(rvData);
    }

    public final int getHeaderCount() {
        return headerArray.size();
    }

    public final int getFooterCount() {
        return footerArray.size();
    }

    public final boolean isDataItem(int position) {
        return position >= getHeaderCount() && position < (getDataCount() + getHeaderCount());
    }

    public final void addHeaderView(View headerView) {
        headerArray.put(ITEM_TYPE_HEADER - getHeaderCount(), headerView);
    }


    public final void addFooterView(View footerView) {
        footerArray.put(ITEM_TYPE_FOOTER - getFooterCount(), footerView);
    }


    public final void addHeaderViewImmed(View headerView) {
        int p = getHeaderCount();
        addHeaderView(headerView);
        notifyItemInserted(p);
    }

    public final void addFooterViewImmed(View footerView) {
        int p = getHeaderCount() + getDataCount() + getFooterCount();
        addFooterView(footerView);
        notifyItemInserted(p);
    }

    /**
     * @param headPosition 相对位置
     * @return headerView
     */
    public final View getHeaderView(int headPosition) {
        return headerArray.get(ITEM_TYPE_HEADER - headPosition);
    }

    /**
     * @param footPosition 相对位置
     * @return footerView
     */
    public final View getFooterView(int footPosition) {
        return footerArray.get(ITEM_TYPE_FOOTER - footPosition);
    }

    public void setOnItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        rvClickHelper.setOnRvItemClickListener(onRvItemClickListener);
    }

    public void setOnItemLongClickListener(OnRvItemLongClickListener onRvItemLongClickListener) {
        rvClickHelper.setOnRvItemLongClickListener(onRvItemLongClickListener);
    }

    class InnerHolder extends BaseRvHolder<T> {

        InnerHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(T hData) {

        }
    }

}
