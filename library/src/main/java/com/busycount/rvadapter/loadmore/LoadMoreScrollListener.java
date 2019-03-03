package com.busycount.rvadapter.loadmore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * LoadMoreScrollListener
 * <p>
 * 2019/3/3 | Count.C | Created
 */
public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {


    private boolean isIdle;

    private boolean isLoading;
    private boolean isEnding;


    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        isIdle = newState == RecyclerView.SCROLL_STATE_IDLE;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (isIdle || isLoading || isEnding) {
            return;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }


    }
}
