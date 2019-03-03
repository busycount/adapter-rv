package com.busycount.rvadapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.busycount.rvadapter.click.OnRvItemClickListener;
import com.busycount.rvadapter.diff.RvDiffHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    }

    private void configRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter0();
        addHeader();
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

    Toast toast;

    private void show(User user) {
        if (toast == null) {
            toast = Toast.makeText(this, user.name, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.setText(user.name);
        toast.show();
    }


    @OnClick({R.id.btn_add, R.id.btn_insert, R.id.btn_diff, R.id.btn_add_header, R.id.btn_add_footer})
    public void onViewClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_add:
                addData();
                break;
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_diff:
                diffData();
                break;
            case R.id.btn_add_header:
                addHeader();
                break;
            case R.id.btn_add_footer:
                addFooter();
                break;
            default:
                break;
        }
    }


    private void addData() {
        adapter.addData(getData());
    }

    private void insert() {
//        Random random = new Random();
//        int i = random.nextInt(10);
//        Log.d("-test-", "insert " + i);
//        adapter.addData(getData(), i);
        adapter.getData().add(3, new User(101, "101"));
        adapter.notifyItemInserted(3);
//        adapter.notifyItemRangeChanged(3, adapter.getItemCount());
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


    private void addHeader() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.bg1));
        adapter.addHeaderView(view);
    }


    private void addFooter() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.bg2));
        adapter.addFooterView(view);
    }
}
