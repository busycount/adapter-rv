package com.busycount.rvadapter.sample.adpater;

import android.view.ViewGroup;

import com.busycount.rvadapter.BaseRvAdapter0;
import com.busycount.rvadapter.BaseRvHolder;
import com.busycount.rvadapter.sample.holder.StrHolder;
import com.busycount.rvadapter.sample.holder.UserHolder;
import com.busycount.rvadapter.sample.bean.User;

/**
 * ActionAdapter
 * <p>
 * 2019/3/5 | Count.C | Created
 */
public class ActionAdapter extends BaseRvAdapter0<String> {
    @Override
    protected BaseRvHolder<String> onExtCreateViewHolder(ViewGroup parent, int viewType) {
        return new StrHolder(parent);
    }
}
