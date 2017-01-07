package com.cst.growthprocess.xUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cst.growthprocess.R;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 研究Xutils相关使用功能(网络请求、文件上传、下载)
 */
public class XUtilsDemoActivity extends AppCompatActivity {


    @InjectView(R.id.button1)
    Button mButton1;

    @InjectView(R.id.button2)
    Button mButton2;

    @InjectView(R.id.button3)
    Button mButton3;

    @InjectView(R.id.button4)
    Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils_demo);
        ButterKnife.inject(this);
        x.view().inject(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1://网络通信
                linkServer();//TODO
                break;
            case R.id.button2://单个文件上传
                //TODO
                break;
            case R.id.button3://批量文件上传
                //TODO
                break;
            case R.id.button4://文件下载
                break;
        }
    }

    /**
     * 网络通信连接服务器
     */
    private void linkServer() {

    }
}
