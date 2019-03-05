package com.busycount.rvadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public abstract class BaseRvAdapter0<T> extends RecyclerView.Adapter<BaseRvHolder<T>> {

    protected List<T> rvData;

    private final RvClickHelper rvClickHelper;

    public BaseRvAdapter0() {
        rvClickHelper = new RvClickHelper();
    }


    @NonNull
    @Override
    public BaseRvHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseRvHolder<T> rvHolder;
        rvHolder = onExtCreateViewHolder(parent, viewType);
        rvClickHelper.setupClick(rvHolder);
        return rvHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return getExtItemViewType(0);
    }

    public int getExtItemViewType(int position) {
        return 0;
    }

    protected abstract BaseRvHolder<T> onExtCreateViewHolder(ViewGroup parent, int viewType);


    @Override
    public void onBindViewHolder(@NonNull BaseRvHolder<T> holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Log.d("-test-3", "onBindViewHolder position " + position);
        Log.d("-test-2", "holder " + holder + " position " + position + " payloads " + payloads.size());
    }


    @Override
    public void onBindViewHolder(@NonNull BaseRvHolder<T> holder, int position) {
        Log.d("-test-1", "onBindViewHolder " + holder + " position " + position);
        rvClickHelper.configClick(holder, position);
        holder.bindView(getData(position));
    }


    @Override
    public int getItemCount() {
        return getDataCount();
    }

    protected boolean isDataItem(int position) {
        return position >= findDataStart() && position < findDataEnd();
    }

    protected final int getDataCount() {
        return IList.getSize(rvData);
    }

    protected int findDataStart() {
        return 0;
    }

    protected int findDataEnd() {
        return findDataStart() + getDataCount();
    }

    public final void addData(List<T> data) {
        addData(data, -1);
    }

    /**
     * 指定位置添加数据
     *
     * @param data      data
     * @param dataIndex 数据位置，不包含header
     */
    public final void addData(List<T> data, int dataIndex) {
        if (IList.isEmpty(data)) {
            return;
        }
        if (IList.isEmpty(rvData)) {
            rvData = data;
            notifyDataSetChanged();
        } else {
            if (dataIndex < 0 || dataIndex > getDataCount()) {
                dataIndex = getDataCount();
            }
            rvData.addAll(dataIndex, data);
            dataIndex += findDataStart();
            notifyItemRangeInserted(dataIndex, data.size());
        }
    }

    public void remove(int position) {
        if (getDataCount() == 0) {
            return;
        }
        if (isDataItem(position)) {
            rvData.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 更新数据
     *
     * @param data data
     */
    public final void setData(List<T> data) {
        rvData = data;
        notifyDataSetChanged();
    }

    @Nullable
    public final List<T> getData() {
        return rvData;
    }


    public T getData(int position) {
        if (IList.isEmpty(rvData)) {
            return null;
        }
        if (isDataItem(position)) {
            return rvData.get(position - findDataStart());
        }
        return null;
    }


    public void setOnItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        rvClickHelper.setOnRvItemClickListener(onRvItemClickListener);
    }

    public void setOnItemLongClickListener(OnRvItemLongClickListener onRvItemLongClickListener) {
        rvClickHelper.setOnRvItemLongClickListener(onRvItemLongClickListener);
    }


}
