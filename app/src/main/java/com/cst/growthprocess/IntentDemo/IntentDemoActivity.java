package com.cst.growthprocess.IntentDemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cst.growthprocess.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class IntentDemoActivity extends AppCompatActivity {

    @InjectView(R.id.show_page)
    Button mShowPage;

    @InjectView(R.id.show_map)
    Button mShowMap;

    @InjectView(R.id.button3)
    Button mButton3;

    @InjectView(R.id.choose_image)
    Button mChooseImage;

    @InjectView(R.id.apk)
    Button mApk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.show_page, R.id.show_map, R.id.button3, R.id.choose_image, R.id.apk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_page:
                show_page();
                break;
            case R.id.show_map:
                show_map();
                break;
            case R.id.button3:
                button3();
                break;
            case R.id.choose_image:
                choose_image();
                break;
            case R.id.apk:
                apk();
                break;
        }
    }

    private void show_page() {
        Uri uri = Uri.parse("http://www.google.com");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    private void show_map() {
        Uri uri = Uri.parse("geo:38.899533,-77.036476");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    /**
     * 路径规划
     */
    private void button3() {
        Uri uri = Uri
                .parse("http://maps.google.com/maps?f=d&saddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }


    private void choose_image() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 11);
    }

    private void apk() {
        Intent intent = new Intent();

        String filepath = "/SDCard/XXX.apk";
        intent.setDataAndType(Uri.parse("file://" + filepath),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
