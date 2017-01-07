package com.cst.growthprocess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cst.growthprocess.customview.PassDemoActivity;
import com.cst.growthprocess.fileup_and_downlode.FileActivity;
import com.cst.growthprocess.jobscheduler.JobschedulerActivity;
import com.cst.growthprocess.pinyinpinyin4j.Pinyin4jActivity;
import com.cst.growthprocess.recyclerViewDemo.RecyclerActivity;
import com.cst.growthprocess.retrofit_okhttp.RetrofitActivity;
import com.cst.growthprocess.vitamio.VitamioActivity;
import com.cst.growthprocess.xUtils.XUtilsDemoActivity;
import com.cst.growthprocess.xfyun.XfyunActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.activity_main_listView)
    ListView mListView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mContext = this;
        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, App.Pro_List);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startItemProject(position);
            }
        });
    }

    /**
     * 分别进入不同的item小项目
     */
    private void startItemProject(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(mContext, PassDemoActivity.class));
                break;
            case 1:
                startActivity(new Intent(mContext, RecyclerActivity.class));
                break;
            case 9:
                startActivity(new Intent(mContext, RetrofitActivity.class));
                break;
            case 10:
                startActivity(new Intent(mContext, Pinyin4jActivity.class));
                break;
            case 11:
                startActivity(new Intent(mContext, VitamioActivity.class));
                break;
            case 12:
                startActivity(new Intent(mContext, XfyunActivity.class));
                break;
            case 13:
                startActivity(new Intent(mContext, JobschedulerActivity.class));
                break;
            case 14:
                startActivity(new Intent(mContext, FileActivity.class));
                break;
            case 15:
                startActivity(new Intent(mContext, XUtilsDemoActivity.class));
                break;
            default:
        }
    }
}
