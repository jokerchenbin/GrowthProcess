package com.cst.growthprocess.customview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ToastDiy;
import com.cst.growthprocess.customview.ui.ArrowGroup;
import com.cst.growthprocess.customview.ui.MyPhotoView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PassDemoActivity extends AppCompatActivity {

    @InjectView(R.id.myPhotoView)
    MyPhotoView mMyPhotoView;


    @InjectView(R.id.arrowGroup)
    ArrowGroup mArrow;

    @InjectView(R.id.activity_pass_button)
    Button mButton;


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mArrow.setVisibility(View.VISIBLE);
            mArrow.setAnimation();
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_demo);
        ButterKnife.inject(this);
        mMyPhotoView.setOnItemClickListener(new MyPhotoView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String log_message) {
                chooseToStartActivity(view);
            }
        });

        mArrow.setOnCloseButtonListener(new ArrowGroup.OnCloseButtonListener() {
            @Override
            public void onClick(View view) {
                ToastDiy.Show(PassDemoActivity.this, "点击了按钮");
                mArrow.setVisibility(View.GONE);
            }
        });
        mArrow.setVisibility(View.INVISIBLE);
        handler.sendEmptyMessageDelayed(0, 500);


    }

    /**
     * 选择启动的Activity
     */
    private void chooseToStartActivity(View view) {
        switch (view.getId()) {
            case R.id.blue:
                startActivity(new Intent(this, SquareActivity.class));
            default:
        }
    }


    @OnClick(R.id.activity_pass_button)
    public void onClick() {
        mArrow.setAnimation();
        mArrow.setVisibility(View.VISIBLE);
    }
}
