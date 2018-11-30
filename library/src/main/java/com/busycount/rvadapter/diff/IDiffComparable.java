package com.busycount.rvadapter.diff;


/**
 * Use for DiffUtil.Callback
 *
 * @see IDiffCallback
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public interface IDiffComparable<T> {

    boolean areItemsTheSame(T other);

    boolean areContentsTheSame(T other);
}
