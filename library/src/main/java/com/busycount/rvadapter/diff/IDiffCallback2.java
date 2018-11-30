package com.busycount.rvadapter.diff;

import android.support.v7.util.DiffUtil;


import com.busycount.rvadapter.IList;

import java.util.List;


/**
 * Use DiffUtil.Callback with IDiffComparator
 *
 * @see IDiffComparator
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class IDiffCallback2<T> extends DiffUtil.Callback {

    private List<T> oList;
    private List<T> nList;
    private IDiffComparator<T> comparator;

    public IDiffCallback2(List<T> oList, List<T> nList, IDiffComparator<T> comparator) {
        this.oList = oList;
        this.nList = nList;
        this.comparator = comparator;
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
        return comparator.areItemsTheSame(oList.get(oldItemPosition), nList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return comparator.areContentsTheSame(oList.get(oldItemPosition), nList.get(newItemPosition));
    }

}