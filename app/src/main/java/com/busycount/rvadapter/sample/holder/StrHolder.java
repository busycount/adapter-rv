package com.busycount.rvadapter.sample.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busycount.rvadapter.BaseRvHolder;
import com.busycount.rvadapter.sample.R;
import com.busycount.rvadapter.sample.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * StrHolder
 * <p>
 * 2019/3/5 | Count.C | Created
 */
public class StrHolder extends BaseRvHolder<String> {


    @BindView(R.id.tv_list)
    TextView tvUser;


    public StrHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindData(String data) {
        tvUser.setText(data);
    }


}
