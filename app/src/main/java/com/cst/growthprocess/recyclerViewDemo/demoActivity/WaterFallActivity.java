package com.cst.growthprocess.recyclerViewDemo.demoActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.cst.growthprocess.R;
import com.cst.growthprocess.recyclerViewDemo.adapter.WaterFallAdapter;
import com.cst.growthprocess.recyclerViewDemo.bean.Notes;
import com.cst.growthprocess.recyclerViewDemo.callback.BaiDuCallBack;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//recyclerView 实现瀑布流效果
public class WaterFallActivity extends AppCompatActivity {


    private static final String TAG = "WaterFallActivity";
    @InjectView(R.id.waterfall_recyclerView)
    RecyclerView mRecyclerView;
    private WaterFallAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_fall);
        ButterKnife.inject(this);
        mContext = this;
        initRecyclerView();
        getDataFromBaidu();
    }

    /**
     * 初始化recyclerView相关配置
     */
    private void initRecyclerView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new WaterFallAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }


    /**
     * 从百度获取数据
     */
    private void getDataFromBaidu() {
        String url = "http://apis.baidu.com/qunartravel/";
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
        BaiDuCallBack call = retrofit.create(BaiDuCallBack.class);
        Call<Notes> notes = call.getNotesList("0998d01daf5538f6dd52dfbe1d8fb419", "", 1);
        notes.enqueue(new Callback<Notes>() {
            @Override
            public void onResponse(Call<Notes> call, retrofit2.Response<Notes> response) {
                Log.v(TAG, response.headers().get("apikey") + "");
                Log.v(TAG, response.headers().toString() + "");
                Log.v(TAG, response.isSuccess() + "");
                Log.v(TAG, response.code() + "");
                Log.v(TAG, response.body().getData().getBooks().size() + "");
                Log.v(TAG, response.code() + "");
                List<Notes.DataBean.BooksBean> books = response.body().getData().getBooks();
                if (null == books || books.size() == 0) {
                    return;
                }
                mAdapter.setData(books);

            }

            @Override
            public void onFailure(Call<Notes> call, Throwable t) {
                Log.v(TAG, t.getMessage());
            }
        });
    }



}
