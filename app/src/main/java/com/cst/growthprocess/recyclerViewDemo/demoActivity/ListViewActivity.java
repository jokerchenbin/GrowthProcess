package com.cst.growthprocess.recyclerViewDemo.demoActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ToastDiy;
import com.cst.growthprocess.recyclerViewDemo.adapter.ListAdapter;
import com.cst.growthprocess.recyclerViewDemo.bean.Notes;
import com.cst.growthprocess.recyclerViewDemo.callback.BaiDuCallBack;
import com.cst.growthprocess.recyclerViewDemo.inter.OnItemListener;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//recyclerView 实现ListView
public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = "ListViewActivity";
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Context mContext;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.inject(this);
        mContext = this;
        initRecyclerView();
        getDataFromBaidu();
    }


    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ListAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemListener(new OnItemListener() {
            @Override
            public void OnItemClickListener(int position) {
                ToastDiy.Show(mContext, position + "");
            }

            @Override
            public void OnItemLongClickListener(int position) {
                Log.v("setListener", "OnItemLongClickListener + " + position);
                showDialogBox(position);
            }
        });

    }

    /**
     * 显示对话框
     */
    private void showDialogBox(final int position) {
        new AlertDialog.Builder(mContext).setTitle("请您确认？").setMessage("是否需要删除这个Item?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.deleteItem(position);
                    }
                }).setNegativeButton("取消", null).show();
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

//    /**
//     * 获取一个带header的client
//     */
//    private OkHttpClient getClient() {
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request().newBuilder().addHeader("apikey", "0998d01daf5538f6dd52dfbe1d8fb419").build();
//                return chain.proceed(request);
//            }
//        }).build();
//
//        return client;
//    }


}
