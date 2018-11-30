package com.busycount.rvadapter.diff;

import android.support.v7.util.DiffUtil;


import com.busycount.rvadapter.IList;

import java.util.List;


/**
 * Use DiffUtil.Callback with IDiffComparable
 *
 * @see IDiffComparable
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class IDiffCallback<T extends IDiffComparable<? super T>> extends DiffUtil.Callback {

    private List<T> oList;
    private List<T> nList;

    public IDiffCallback(List<T> oList, List<T> nList) {
        this.oList = oList;
        this.nList = nList;
    }

    @Override
    public int getOldListSize() {
        return IList.getSize(oList);
    }

    @Override
    public int getNewListSize() {
        return IList.getSize(nList);
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oList.get(oldItemPosition).areItemsTheSame(nList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oList.get(oldItemPosition).areContentsTheSame(nList.get(newItemPosition));
    }

}