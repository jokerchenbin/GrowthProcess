package com.cst.growthprocess.retrofit_okhttp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ToastDiy;
import com.cst.growthprocess.retrofit_okhttp.bean.User;
import com.cst.growthprocess.retrofit_okhttp.bean.Weather;
import com.cst.growthprocess.retrofit_okhttp.callback.RequestFromBaidu;
import com.cst.growthprocess.retrofit_okhttp.callback.RequestServes;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";
    @InjectView(R.id.activity_retrofit_result)
    TextView mResult;
    @InjectView(R.id.edit_cityname)
    EditText mEditCityname;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        mContext = this;
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void getData() {
        getDataFromBaiduApi();
    }


    /**
     * 从百度Api拿数据
     */
    private void getDataFromBaiduApi() {
        String cityName = mEditCityname.getText().toString().trim();
        if (TextUtils.isEmpty(cityName)) {
            ToastDiy.Show(mContext, "请输入城市名称");
            return;
        }
        //注意baseUrl 必须"/" 结尾  否则会崩溃
        String baseUrl = "http://apis.baidu.com/apistore/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        RequestFromBaidu baidu = retrofit.create(RequestFromBaidu.class);
        Call<Weather> call = baidu.getWeatherByCityName(cityName);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                String requestCode = response.body().getErrMsg();
                mResult.setText("0".equals(requestCode) ? response.message()
                                                        : response.body().getRetData().toString());

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                mResult.setText("请输入正确的城市名称");
                ToastDiy.Show(mContext, "请输入城市名称");
            }
        });
    }


    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("apikey", "0998d01daf5538f6dd52dfbe1d8fb419")
                        .build();
                return chain.proceed(request);
            }
        }).build();
        return client;
    }


    /**
     * 从本地服务器拿数据
     */
    private void getDataFromLocal() {
        String baseUrl = "http://172.19.14.216/cb/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServes requestServes = retrofit.create(RequestServes.class);//这里采用的是Java的动态代理模式

        Call<List<User>> call = requestServes.getUserList();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i(TAG, response.body().get(1).getRegisterTime());
                ToastDiy.Show(mContext, response.body().get(1).getRegisterTime());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }
}
