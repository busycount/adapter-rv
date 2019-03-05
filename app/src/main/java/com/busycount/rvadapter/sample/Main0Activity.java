package com.busycount.rvadapter.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.busycount.rvadapter.click.OnRvItemClickListener;
import com.busycount.rvadapter.diff.RvDiffHelper;
import com.busycount.rvadapter.sample.adpater.ActionAdapter;
import com.busycount.rvadapter.sample.adpater.UserAdapter0;
import com.busycount.rvadapter.sample.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main0Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private UserAdapter0 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configRv();
        configRvAction();
    }

    private void configRv() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter0();
        recyclerView.setAdapter(adapter);
//        adapter.setData(getData());
//        adapter.setOnItemClickListener(new OnRvItemClickListener() {
//            @Override
//            public void onClick(View itemView, int position) {
//                Log.d("-test-4", itemView.toString());
//                show(adapter.getData(position));
//            }
//        });
    }

    private void configRvAction() {
        RecyclerView recyclerView = findViewById(R.id.rv_action);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActionAdapter adapter = new ActionAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addData(getAction());
        adapter.setOnItemClickListener(new OnRvItemClickListener() {
            @Override
            public void onClick(View itemView, int position) {
                setAction(position);
            }
        });
    }

    private List<String> getAction() {
        List<String> list = new ArrayList<>();
        list.add("add");
        list.add("insertData");
        list.add("diff");
        list.add("add header");
        list.add("add footer");
        list.add("remove");
        return list;
    }

    private void setAction(int position) {
        switch (position) {
            case 0:
                addData();
                break;
            case 1:
                insertData();
                break;
            case 2:
                diffData();
                break;
            case 3:
                addHeader();
                break;
            case 4:
                addFooter();
                break;
            case 5:
                removeData();
                break;
        }
    }


    private void addData() {
        adapter.addData(getData());
    }

    private void insertData() {
        Random random = new Random();
        int i = random.nextInt(10);
        Log.d("-test-", "insertData " + i);
        adapter.addData(getData(), i);
        adapter.notifyItemRangeChanged(3, adapter.getItemCount());
//        adapter.getData().add(3, new User(101, "101"));
//        adapter.notifyItemInserted(3);
    }

    private void diffData() {
        RvDiffHelper.diffData(adapter, getData(), false);
    }


    private List<User> getData() {
        List<User> list = new ArrayList<>();
        String name;
        for (int i = getInt(), length = i + 5; i < length; i++) {
            list.add(new User(i, "- " + i));
        }
        return list;
    }

    private int getInt() {
        Random random = new Random();
        int i = random.nextInt(10);
        Log.d("-test-", "random " + i);
        return i * 10;
    }

    public void removeData() {
        adapter.setData(null);
    }


    int[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.CYAN};
    int head, foot;


    private void addHeader() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        TextView tv = view.findViewById(R.id.tv_header);
        tv.setText(String.valueOf(head));
        tv.setBackground(new ColorDrawable(colors[head % 5]));
        adapter.addHeaderView(view);
    }

    private void addFooter() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        TextView tv = view.findViewById(R.id.tv_header);
        tv.setText(String.valueOf(foot));
        tv.setBackground(new ColorDrawable(colors[foot % 5]));
        adapter.addFooterView(view);
    }


    private void remove() {
        adapter.remove(getInt());
    }
}
