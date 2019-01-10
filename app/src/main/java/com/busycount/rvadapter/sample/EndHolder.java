package com.busycount.rvadapter.sample;

import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busycount.rvadapter.BaseRvHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EndHolder extends BaseRvHolder<String> {
    @BindView(R.id.text)
    TextView tvUser;

    public EndHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_end, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindData(String hData) {

    }


    public void setSize(int a) {
        ViewGroup.LayoutParams params = tvUser.getLayoutParams();
        params.height += a;
        tvUser.requestLayout();
    }

    public void revert() {
//        ObjectAnimator objectAnimator=new ObjectAnimator();
    }

}
