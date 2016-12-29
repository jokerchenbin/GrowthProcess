package com.cst.growthprocess.recyclerViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cst.growthprocess.R;
import com.cst.growthprocess.recyclerViewDemo.demoActivity.GridViewActivity;
import com.cst.growthprocess.recyclerViewDemo.demoActivity.ListViewActivity;
import com.cst.growthprocess.recyclerViewDemo.demoActivity.WaterFallActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RecyclerActivity extends AppCompatActivity {

    @InjectView(R.id.button_listView)
    Button mButtonListView;
    @InjectView(R.id.button_gridView)
    Button mButtonGridView;
    @InjectView(R.id.button_waterFall)
    Button mButtonWaterFall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.button_listView, R.id.button_gridView, R.id.button_waterFall})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_listView:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.button_gridView:
                startActivity(new Intent(this, GridViewActivity.class));
                break;
            case R.id.button_waterFall:
                startActivity(new Intent(this, WaterFallActivity.class));
                break;
        }
    }
}
