package com.cst.growthprocess.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cst.growthprocess.R;
import com.cst.growthprocess.customview.ui.MyPhotoView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PassDemoActivity extends AppCompatActivity {

    @InjectView(R.id.myPhotoView)
    MyPhotoView mMyPhotoView;

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
}
