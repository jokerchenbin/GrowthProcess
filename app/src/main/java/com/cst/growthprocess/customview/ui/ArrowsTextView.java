package com.cst.growthprocess.customview.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cst.growthprocess.R;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：自定义带箭头的状态提示
 *
 * @author chenb
 *
 *         Time: 2017/2/5 0005
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class ArrowsTextView extends RelativeLayout {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    /* 设置方向箭头的位置 */
    public static final short TOP = 0x113;

    public static final short RIGHT = TOP + 1;

    public static final short BUTTON = RIGHT + 1;

    public static final short LEFT = BUTTON + 1;

    //箭头图标的X、Y
    private float x, y;

    //箭头图标和内容区的距离
    private int distance;

    private ImageView mArrowView;

    private LinearLayout mLl;

    //关闭按钮监听
    private OnCloseButtonListener mLisener = null;

    public ArrowsTextView(Context context) {
        super(context);
        init();
    }

    public ArrowsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArrowsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(lp);
        mContext = getContext();

        //横向的布局
        mLl = new LinearLayout(mContext);
        LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mLl.setLayoutParams(params);
        mLl.setBackgroundResource(R.mipmap.cumston_bg);
        mLl.setOrientation(LinearLayout.HORIZONTAL);

        mArrowView = new ImageView(mContext);
        //添加横向布局
        addView(mLl);

        TextView textView = new TextView(mContext);
        textView.setText("请选择要发表的地方");
        textView.setTextColor(Color.parseColor("#333333"));
        textView.setTextSize(24);
        mLl.addView(textView);

        TextView textView1 = new TextView(mContext);
        textView1.setBackgroundResource(R.mipmap.close_delete);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params1.setMargins(14, 0, 0, 0);
        textView1.setLayoutParams(params1);

        textView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mLisener.onClick(v);
            }
        });

        mLl.addView(textView1);


    }


    /**
     * @param x        布局的坐标X
     * @param y        布局的坐标Y
     * @param location 箭头方向  ArrowsTextView.TOP
     */
    public void setData(float x, float y, short location, float distance) {

        //获取箭头图片的高度和宽度
        int mArrowWidth = mArrowView.getWidth();
        int mArrowHight = 12;

        //获取内容显示区高度和宽度
        float mLlX = mLl.getX();
        float mLlY = mLl.getY();

        this.x = x;
        this.y = y;
        mLl.setX(x);
        mLl.setY(y);

        float arrow_x = 0;
        float arrow_y = 0;

        int arrowRes;

        switch (location) {
            case TOP:
                arrowRes = R.mipmap.top;
                arrow_x = x + distance;
                arrow_y = y - mArrowHight;
                break;
            case RIGHT:
                arrowRes = R.mipmap.right;
                break;
            case BUTTON:
                arrowRes = R.mipmap.button;
                arrow_x = x + distance;
                arrow_y = y + 70 + mArrowHight;
                break;
            case LEFT:
                arrowRes = R.mipmap.left;
                break;
            default:
                throw new RuntimeException("ArrowsTextView must set direction");
        }

        //设置箭头
        mArrowView.setImageResource(arrowRes);
        mArrowView.setX(arrow_x);
        mArrowView.setY(arrow_y);

        //添加布局进去
        addView(mArrowView);
    }


    public void setOnCloseButtonListener(OnCloseButtonListener listener) {
        mLisener = listener;
    }

    public interface OnCloseButtonListener {

        void onClick(View view);
    }

}
