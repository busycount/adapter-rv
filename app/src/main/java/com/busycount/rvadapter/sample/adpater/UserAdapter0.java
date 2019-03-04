package com.busycount.rvadapter.sample.adpater;

import android.view.ViewGroup;

import com.busycount.rvadapter.BaseRvAdapter2;
import com.busycount.rvadapter.BaseRvHolder;
import com.busycount.rvadapter.sample.bean.User;
import com.busycount.rvadapter.sample.holder.UserHolder;


/**
 * User adapter
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class UserAdapter0 extends BaseRvAdapter2<User> {

    public UserAdapter0() {
    }

    public UserAdapter0(int layoutId) {
        super(layoutId);
    }

    @Override
    public BaseRvHolder<User> onExtCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(parent);
    }
}
