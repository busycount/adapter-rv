package com.busycount.rvadapter.sample;

import android.view.ViewGroup;

import com.busycount.rvadapter.BaseRvAdapter;
import com.busycount.rvadapter.BaseRvHolder;


/**
 * User adapter
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class UserAdapter extends BaseRvAdapter<User> {
    @Override
    public BaseRvHolder<User> onExtCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(parent);
    }
}
