package com.cst.growthprocess.fileup_and_downlode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cst.growthprocess.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class FileActivity extends AppCompatActivity {

    @InjectView(R.id.download)
    Button mDownload;

    @InjectView(R.id.upload)
    Button mUpload;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.inject(this);
        mContext = this;
    }

    @OnClick({R.id.download, R.id.upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download:
                downLoadFile();
                break;
            case R.id.upload:
                upLoadFile();
                break;
        }
    }

    /**
     * 上传文件
     */
    private void upLoadFile() {

        String url = "http://172.19.14.216/cb/upload_file.php";
        String path = Environment.getExternalStorageDirectory().getPath()
                + "/0_chenbin/uncaughtException"+File.separator+"chenbi.txt";
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            try {
                params.put("uploadfile", file);
            } catch (FileNotFoundException e) {
                Log.e("binaryData", "上传中 =FileNotFoundException= 》 " + e.getMessage());
                e.printStackTrace();
            }
            // 上传文件
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                        byte[] responseBody) {
                    // 上传成功后要做的工作
                    Toast.makeText(mContext, "上传成功", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                        byte[] responseBody, Throwable error) {
                    // 上传失败后要做到工作
                    Toast.makeText(mContext, "上传失败", Toast.LENGTH_LONG).show();
                    Log.e("binaryData", "上传中 == 》 " + error.getMessage());
                }


                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    super.onProgress(bytesWritten, totalSize);
                    Log.e("binaryData", "上传中 == 》 " + bytesWritten + " / " + totalSize);
                }

                @Override
                public void onRetry(int retryNo) {
                    // TODO Auto-generated method stub
                    super.onRetry(retryNo);
                    // 返回重试次数
                }

            });
        } else {
            Toast.makeText(mContext, "文件不存在", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 下载图片文件
     */
    private void downLoadFile() {

        String url = "http://172.19.14.216/cb/upload/chenbi.txt";

        AsyncHttpClient client = new AsyncHttpClient();

        // 指定文件类型
        String[] allowedContentTypes = new String[]{"text/plain"};
        // 获取二进制数据如图片和其他文件
        client.get(url, new BinaryHttpResponseHandler(allowedContentTypes) {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                    byte[] binaryData) {
                String tempPath = Environment.getExternalStorageDirectory()
                        .getPath() + "/0_chenbin/temp.txt";
                // TODO Auto-generated method stub
                // 下载成功后需要做的工作
                Log.e("binaryData:", "共下载了：" + binaryData.length);
                //
                Bitmap bmp = BitmapFactory.decodeByteArray(binaryData, 0,
                        binaryData.length);

                File file = new File(tempPath);
                // 压缩格式
                Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
                // 压缩比例
                int quality = 100;
                try {
                    // 若存在则删除
                    if (file.exists()) {
                        file.delete();
                    }
                    // 创建文件
                    file.createNewFile();
                    //
                    OutputStream stream = new FileOutputStream(file);
                    // 压缩输出
                    bmp.compress(format, quality, stream);
                    // 关闭
                    stream.close();
                    //
                    Toast.makeText(mContext, "下载成功\n" + tempPath,
                            Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                    byte[] binaryData, Throwable error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "下载失败", Toast.LENGTH_LONG).show();
                Log.e("binaryData", error.getMessage() + " / ");
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                // 下载进度显示
                Log.e("binaryData", bytesWritten + " / " + totalSize);
            }

            @Override
            public void onRetry(int retryNo) {
                // TODO Auto-generated method stub
                super.onRetry(retryNo);
                // 返回重试次数
            }

        });

    }
}
