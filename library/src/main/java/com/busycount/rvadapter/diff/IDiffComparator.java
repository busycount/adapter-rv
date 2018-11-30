package com.busycount.rvadapter.diff;


/**
 * Use for DiffUtil.Callback
 *
 * @see IDiffCallback2
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public interface IDiffComparator<T> {

    boolean areItemsTheSame(T o, T n);

    boolean areContentsTheSame(T o, T n);
}
