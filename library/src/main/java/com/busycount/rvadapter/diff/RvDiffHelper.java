package com.busycount.rvadapter.diff;

import android.support.v7.util.DiffUtil;


import com.busycount.rvadapter.BaseRvAdapter;
import com.busycount.rvadapter.BaseRvAdapter0;

import java.util.List;


/**
 * RecyclerView diff helper
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class RvDiffHelper {

    public static <T extends IDiffComparable<? super T>> void diffData(BaseRvAdapter0<T> adapter, List<T> data, boolean detectMove) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new IDiffCallback<>(adapter.getData(), data),
                detectMove);
        diffResult.dispatchUpdatesTo(adapter);
        adapter.setData(data);
    }

    public static <T extends IDiffComparable<? super T>> void diffData(BaseRvAdapter<T> adapter, List<T> data, boolean detectMove) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new IDiffCallback<>(adapter.getData(), data),
                detectMove);
        diffResult.dispatchUpdatesTo(adapter);
        adapter.setData(data);
    }

    public static <T> void diffData(BaseRvAdapter<T> adapter, List<T> data, IDiffComparator<T> comparator, boolean detectMove) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new IDiffCallback2<>(adapter.getData(),
                data,
                comparator), detectMove);
        diffResult.dispatchUpdatesTo(adapter);
        adapter.setData(data);
    }

}
