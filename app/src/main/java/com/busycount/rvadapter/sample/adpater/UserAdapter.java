package com.busycount.rvadapter.sample.adpater;

import android.os.AsyncTask;
import android.view.ViewGroup;

import com.busycount.rvadapter.BaseRvHolder;
import com.busycount.rvadapter.sample.BaseRvLoadAdapter;
import com.busycount.rvadapter.sample.bean.User;
import com.busycount.rvadapter.sample.holder.UserHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * User adapter
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class UserAdapter extends BaseRvLoadAdapter<User> {
    @Override
    public BaseRvHolder<User> onExtCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(parent);
    }

    int a = 20;

    @Override
    public void onLoadMore() {
        LoadTask task = new LoadTask(this);
        task.execute(a);
        a += 20;
    }

    static class LoadTask extends AsyncTask<Integer, Integer, List<User>> {
        private UserAdapter adapter;

        public LoadTask(UserAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected List<User> doInBackground(Integer... integers) {
            int a = integers[0];
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<User> userList = new ArrayList<>();
            for (int i = a, length = a + 20; i < length; i++) {
                userList.add(new User(i, "- " + i));
            }
            return userList;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            adapter.loadMoreSuccess(users);
        }
    }
}
