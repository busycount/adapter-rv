package com.busycount.rvadapter.sample;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.busycount.rvadapter.divider.LineDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configRv();
    }

    private void configRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        LineDivider.Builder builder = new LineDivider.Builder();
//        builder.setColor(Color.RED)
//               .setDividerSize(10)
//               .setLeftPadding(10)
//               .setRightPadding(20)
//               .setSpecialItem(1, new LineDivider.SpecialItem(Color.YELLOW, 20, 0, 0));
//        LineDivider lineDivider = new LineDivider(builder);
//        recyclerView.addItemDecoration(lineDivider);
        adapter = new UserAdapter();
        adapter.attachTo(recyclerView);
//        recyclerView.setAdapter(adapter);
        addData();

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (loadMoreFlag) {
//                    return;
//                }
//                RecyclerView.Adapter adapter = recyclerView.getAdapter();
//                if (adapter == null) {
//                    return;
//                }
//                int lastIndex = adapter.getItemCount() - 1;
//                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(lastIndex);
//                if (holder != null) {
//                    Log.d("-test-", "最后一个holder已经加载");
//                    loadmore();
//                }
//            }
//        });
    }


    private boolean loadMoreFlag = false;

    private void loadmore() {
        loadMoreFlag = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    Log.d("-test-", "loadmore");
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            addData();
                        }
                    });
                    loadMoreFlag = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private int a;

    private void addData() {
        List<User> userList = new ArrayList<>();
        for (int i = a, length = a + 20; i < length; i++) {
            userList.add(new User(i, "- " + i));
        }
        a += 20;
        adapter.addData(userList);
    }
}
