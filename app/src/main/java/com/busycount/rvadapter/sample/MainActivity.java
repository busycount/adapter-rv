package com.busycount.rvadapter.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        LineDivider.Builder builder = new LineDivider.Builder();
        builder.setColor(Color.RED)
               .setDividerSize(10)
               .setLeftPadding(10)
               .setRightPadding(20)
               .setSpecialItem(1, new LineDivider.SpecialItem(Color.YELLOW, 20, 0, 0));
        LineDivider lineDivider = new LineDivider(builder);
        recyclerView.addItemDecoration(lineDivider);
        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);
        addData();
    }

    private void addData() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            userList.add(new User(i, "- " + i));
        }
        adapter.addData(userList);
    }
}
