package com.cst.growthprocess.customview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ToastDiy;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/11/26 0026
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class MyPhotoView extends FrameLayout {

    private final String TAG = "MyPhotoView";
    private final Context mContext;

    @InjectView(R.id.blue)
    ImageButton mBlue;
    @InjectView(R.id.green)
    ImageButton mGreen;
    @InjectView(R.id.orange)
    ImageButton mOrange;
    @InjectView(R.id.yellow)
    ImageButton mYellow;
    @InjectView(R.id.indigo)
    ImageButton mIndigo;
    /**
     * 最上层的按钮
     */
    @InjectView(R.id.red)
    ImageButton mRed;
    @InjectView(R.id.framelayout)
    FrameLayout mFramelayout;

    //判断是否为按钮是否为打开状态
    private boolean isOpen = false;
    //打开的半径
    private int mRadius;
    //动画执行的时间
    private long interval = 500;
    //日志提醒信息
    private String log_mess;
    //记录图片的宽度
    private int mCircleLenth;
    //相邻两个图片的角度
    private float eachAngle;
    private OnItemClickListener mClickListener;

    private ArrayList<ImageButton> image_list = new ArrayList<>();

    public MyPhotoView(Context context) {
        this(context, null);
    }

    public MyPhotoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPhotoView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_photo_view, this);
        ButterKnife.inject(view, this);
        mCircleLenth = mRed.getPaddingLeft() + mRed.getPaddingRight();
        mRadius = getScreenWidth(context) / 2 - mCircleLenth / 2;
        mContext = context;
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        image_list.add(mBlue);
        image_list.add(mGreen);
        image_list.add(mOrange);
        image_list.add(mYellow);
        image_list.add(mIndigo);
        eachAngle = 180.f / (image_list.size() - 1);
       // eachAngle = 90.f / (image_list.size() - 1);
    }

    /**
     * 设置动画播放的时间
     */
    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setRadius(int radius) {
        if (mRadius < radius) {
            //当设置的半径大于最大半径可取值时，默认最大半径值，打日志提醒
            Log.e(TAG, "Set the radius of the maximum values range");
            return;
        }
        if (radius < 0) {
            //设置的半径不能为负数
            Log.e(TAG, "Set the radius cannot be negative");
            return;
        }
        this.mRadius = radius;
    }

    @OnClick(R.id.red)
    void startAnimation() {
        if (!isOpen) {
            showState();
        } else {
            hideState();
        }
        isOpen = !isOpen;
    }

    /**
     * 隐藏的状态
     */
    private void hideState() {
        for (ImageButton button : image_list) {
            button.animate().translationX(0).translationY(0).setDuration(interval);
        }
    }

    /**
     * 打开的显示状态
     */
    private void showState() {
        float angleDeg = 180.f;
        for (int i = 0; i < image_list.size(); i++) {
            ImageButton button = image_list.get(i);
            final float angleRad = (float) ((angleDeg + eachAngle * i) * Math.PI / 180.f);
            final Float x = mRadius * (float) Math.cos(angleRad);
            final Float y = mRadius * (float) Math.sin(angleRad);
            Log.v(TAG, x + " ---------- " + y);
            button.animate()
                    .setDuration(interval)
                    .translationX(x)
                    .translationY(y)
                    .start();
        }

    }


    /**
     * 获取屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }


    @OnClick({R.id.blue, R.id.green, R.id.orange, R.id.yellow, R.id.indigo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blue:
                log_mess = "blue";
                break;
            case R.id.green:
                log_mess = "green";
                break;
            case R.id.orange:
                log_mess = "orange";
                break;
            case R.id.yellow:
                log_mess = "yellow";
                break;
            case R.id.indigo:
                log_mess = "indigo";
                break;
            default:
                log_mess = "";
        }
        ToastDiy.Show(mContext, log_mess);
        if (null != mClickListener) {
            mClickListener.onItemClick(view, log_mess);
        }
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    /***
     * 点击事件监听
     */
    public interface OnItemClickListener {
        void onItemClick(View view, String log_message);
    }
}
