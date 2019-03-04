package com.busycount.rvadapter.sample;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.ViewGroup;


import com.busycount.rvadapter.BaseRvHolder;
import com.busycount.rvadapter.sample.bean.User;
import com.busycount.rvadapter.sample.holder.UserHolder;

import java.util.List;


/**
 * SortRvAdapter
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class TestSortRvAdapter extends RecyclerView.Adapter<BaseRvHolder<User>> {

    protected SortedList<User> rvData;

    public TestSortRvAdapter() {
        rvData = new SortedList<>(User.class, new SortedListAdapterCallback<User>(this) {
            @Override
            public int compare(User o1, User o2) {
                return o1.id - o2.id;
            }

            @Override
            public boolean areContentsTheSame(User oldItem, User newItem) {
                return (oldItem.name != null && newItem.name != null) && oldItem.name.equals(newItem.name);
            }

            @Override
            public boolean areItemsTheSame(User item1, User item2) {
                return item1.id == item2.id;
            }
        });
    }

    @Override
    public BaseRvHolder<User> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseRvHolder<User> holder, int position) {
        holder.bindView(rvData.get(position));
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public void add(List<User> data) {
        rvData.beginBatchedUpdates();
        rvData.addAll(data);
        rvData.endBatchedUpdates();
    }

    public void remove(List<User> data) {
        rvData.beginBatchedUpdates();
        for (User temp : data) {
            rvData.remove(temp);
        }
        rvData.endBatchedUpdates();
    }
}
