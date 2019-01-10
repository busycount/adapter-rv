package com.busycount.rvadapter.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.busycount.rvadapter.BaseRvAdapter;
import com.busycount.rvadapter.BaseRvHolder;

import java.util.List;

public abstract class BaseRvLoadAdapter<T> extends BaseRvAdapter<T> {

    public void loadMoreSuccess(List<T> data) {
        loadMoreFlag = false;
        addData(data);
    }

    public void executeLoadMore() {
        loadMoreFlag = true;
        onLoadMore();
    }

    public abstract void onLoadMore();

    private boolean loadMoreFlag = false;

    public void attachTo(RecyclerView recyclerView) {
        recyclerView.setAdapter(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {

                } else {

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (loadMoreFlag) {
                    return;
                }
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter == null) {
                    return;
                }
                int lastIndex = adapter.getItemCount() - 1;
                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(lastIndex);
                if (holder != null) {
                    View view = holder.itemView;
                    originHeight = view.getHeight();
                    if (view.getBottom() <= recyclerView.getBottom()) {
                        ViewGroup.LayoutParams params = view.getLayoutParams();
                        params.height += dy;
                        Log.d("-test-", "item " + dy);
                        view.requestLayout();
                    }
//                    Log.d("-test-", "rv-bottom " + recyclerView.getBottom());
//                    executeLoadMore();
                }
            }

            int originHeight;

            public void setView(View view, int dy) {

            }

        });
    }

}
