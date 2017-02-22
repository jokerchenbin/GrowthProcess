package com.cst.growthprocess.android_file_path;

import android.content.IntentFilter;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cst.growthprocess.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import cz.msebera.android.httpclient.message.BasicNameValuePair;

import static android.os.Environment.MEDIA_MOUNTED;

public class FilePathActivity extends AppCompatActivity {

    private final static String TAG = FilePathActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_path);
        Log.v(TAG, getCacheDir().getPath());

        Log.v(TAG, getFilesDir().getPath());

        if (!Environment.getExternalStorageState().equals(MEDIA_MOUNTED)) {
            Log.v(TAG, "没有内存卡");
            return;
        }

        Log.v(TAG, Environment.getExternalStorageDirectory().getPath() + "");
        File file = new File(Environment.getExternalStorageDirectory().getPath() ,"abc.txt");
        //createSDCardDir();
        if (file.exists()) {
            Log.v(TAG, "创建文件成功");
        } else {
            Log.v(TAG, "创建文件失败");
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction("");

        Log.v(TAG, getCacheDir().getPath());

        Log.v(TAG, getCacheDir().getPath());

        Collection collection = new ArrayList();


    }


    //在SD卡上创建一个文件夹
    public void createSDCardDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            //得到一个路径，内容是sdcard的文件夹路径和名字
            String path = sdcardDir.getPath() + "/cardImages";
            File path1 = new File(path);
            if (!path1.exists()) {
                //若不存在，创建目录，可以在应用启动的时候创建
                path1.mkdirs();
                setTitle("paht ok,path:" + path);
            }
        } else {
            setTitle("false");
            return;
        }
    }
}
