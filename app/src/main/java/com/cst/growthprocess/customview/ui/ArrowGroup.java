package com.cst.growthprocess.customview.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2017/2/6 0006
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class ArrowGroup extends RelativeLayout {


    //四种类型:分别为左上、右上、左下、右下四个方向
    public static final int DIRCTION_TOP = 0;

    public static final int DIRCTION_BUTTON = 1;

    private Context mContext;

    //上下箭头View.
    @InjectView(R.id.arrow_top)
    public TextView arrow_top;

    @InjectView(R.id.arrow_button)
    public TextView arrow_button;

    //内容layout
    @InjectView(R.id.arrow_layout_linearLayout)
    public LinearLayout content_layout;

    //文字显示区域
    @InjectView(R.id.arrow_layout_content)
    public TextView contentView;

    //关闭按钮
    @InjectView(R.id.arrow_layout_delete)
    public TextView colseView;

    //箭头显示的位置
    private int mLocation = 0;

    //距离内容条的距离
    private float mDistance = 0;


    //关闭按钮的监听
    private OnCloseButtonListener mListener = null;

    public ArrowGroup(Context context) {
        this(context, null);
    }

    public ArrowGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = getContext();
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.ArrowGroup);

        mLocation = array.getInt(R.styleable.ArrowGroup_mLocation, 0);
        mDistance = array.getDimension(R.styleable.ArrowGroup_mDistance, 0);
        array.recycle();  //注意回收
        initView();
    }


    private void initView() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.arrow_layout, this, true);
        ButterKnife.inject(this, view);
        colseView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v);
            }
        });

        //setLocation();
    }


    public void setOnCloseButtonListener(OnCloseButtonListener listener) {
        mListener = listener;
    }

    public interface OnCloseButtonListener {

        void onClick(View view);
    }


    /**
     * 设置 动画开始的方向
     */
    public void setAnimation() {

        int a = getHeight();
        Log.v("MMMMMM", a + "");
        setLocation();
        ScaleAnimation anim;

        //便宜量
        float offset = mDistance / getWidth();

        switch (mLocation) {
            case DIRCTION_TOP:
                anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, offset,
                        Animation.RELATIVE_TO_SELF, 0);
                break;

            case DIRCTION_BUTTON:
                anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, offset,
                        Animation.RELATIVE_TO_SELF, 1);
                break;
            default:
                return;
        }
        anim.setDuration(1000);
        this.startAnimation(anim);
    }

    /**
     * 设置箭头的属性参数
     */
    private void setLocation() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                15);

        //确定箭头显示的位置
        //确定箭头距离左边距的位置
        if (mLocation == 0) {
            ViewUtils.visible(arrow_top);
            ViewUtils.gone(arrow_button);
            params.setMargins((int) mDistance, 0, 0, ViewUtils.dip2px(mContext, -2));
            arrow_top.setLayoutParams(params);
        } else {
            ViewUtils.visible(arrow_button);
            ViewUtils.gone(arrow_top);
            //ViewUtils.dip2px(mContext, 34) getHeight()拿到为0，暂时代替
            params.setMargins((int) mDistance, ViewUtils.dip2px(mContext, 34) - 4, 0, 0);
            arrow_button.setLayoutParams(params);
        }

        int a = getHeight();
        Log.v("MMMMMMMM", a + "-----getHeight");
       // Log.v("MMMMMMMM", ViewUtils.dip2px(mContext, 34) + "------dip2px");
    }


}
