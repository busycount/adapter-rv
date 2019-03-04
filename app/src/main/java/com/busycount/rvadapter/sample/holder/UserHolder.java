package com.busycount.rvadapter.sample.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


import com.busycount.rvadapter.BaseRvHolder;
import com.busycount.rvadapter.sample.R;
import com.busycount.rvadapter.sample.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User holder
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class UserHolder extends BaseRvHolder<User> {

    @BindView(R.id.tv_list)
    TextView tvUser;

    public UserHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindData(User hData) {
        tvUser.setText(hData.name);
    }
}