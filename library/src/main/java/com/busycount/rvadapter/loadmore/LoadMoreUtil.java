package com.busycount.rvadapter.loadmore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * LoadMoreUtil
 * <p>
 * 2019/3/3 | Count.C | Created
 */
public class LoadMoreUtil {

    public void setLoad(RecyclerView recyclerView){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
