package com.busycount.rvadapter.sample;

import android.os.AsyncTask;
import android.view.ViewGroup;

import com.busycount.rvadapter.BaseRvAdapter;
import com.busycount.rvadapter.BaseRvAdapter0;
import com.busycount.rvadapter.BaseRvAdapter1;
import com.busycount.rvadapter.BaseRvHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * User adapter
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class UserAdapter0 extends BaseRvAdapter1<User> {
    @Override
    public BaseRvHolder<User> onExtCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(parent);
    }
}
