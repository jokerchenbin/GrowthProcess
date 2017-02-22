package com.cst.growthprocess.UI;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cst.growthprocess.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：Viewpager圆点指示器
 *
 * @author chenb
 *
 *         Time: 2017/2/18 0018
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class IndicatorView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private Context mContext;


    private int size;

    private List<ImageView> dotViewLists = new ArrayList<>();

    private int imgSize = 15;

    //选中和未选中的shape
    private GradientDrawable mNotSelect,mSelected;

    //填充大小
    private float mStrokeWidth;
    //shape圆的半径
    private float mRoundRadius;
    //填充的颜色
    private int mStrokeColor;
    //选中的颜色
    private int mFillColor;
    //未选中的颜色
    private int mPaintPageFill;


    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = getContext();

        //Load defaults from resources
        final Resources res = getResources();
        final int defaultPageColor = res.getColor(R.color.default_circle_indicator_page_color);
        final int defaultFillColor = res.getColor(R.color.default_circle_indicator_fill_color);
        final int defaultOrientation = res.getInteger(R.integer.default_circle_indicator_orientation);
        final int defaultStrokeColor = res.getColor(R.color.default_circle_indicator_stroke_color);
        final float defaultStrokeWidth = res.getDimension(R.dimen.default_circle_indicator_stroke_width);
        final float defaultRadius = res.getDimension(R.dimen.default_circle_indicator_radius);
        final boolean defaultCentered = res.getBoolean(R.bool.default_circle_indicator_centered);
        final boolean defaultSnap = res.getBoolean(R.bool.default_circle_indicator_snap);


        //获取属性值
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CirclePageIndicator);
        //获取自定义属性的个数
        int N = arr.getIndexCount();

        for (int i = 0; i < N; i++) {
            int arrts = arr.getIndex(i);
            switch (arrts) {
                case R.styleable.CirclePageIndicator_centered:
                    break;
                case R.styleable.CirclePageIndicator_fillColor:
                    mFillColor = arr.getColor(arrts,defaultFillColor);
                    break;
                case R.styleable.CirclePageIndicator_pageColor:
                    mPaintPageFill = arr.getColor(arrts,defaultPageColor);
                    break;
                case R.styleable.CirclePageIndicator_radius:
                    mRoundRadius = arr.getDimension(R.styleable.CirclePageIndicator_radius, defaultRadius);
                    break;
                case R.styleable.CirclePageIndicator_snap:
                    break;
                case R.styleable.CirclePageIndicator_strokeColor:
                    mStrokeColor = arr.getColor(arrts,defaultStrokeColor);
                    break;
                case R.styleable.CirclePageIndicator_strokeWidth:
                    mStrokeWidth = arr.getDimension(arrts,defaultStrokeWidth);
                    break;
                case R.styleable.CirclePageIndicator_itemGap:
                    break;

            }
        }

        //回收
        arr.recycle();
        initDrawableShape();


    }

    /**
     *初始化
     */
    private void initDrawableShape() {
        mNotSelect = new GradientDrawable();
        mNotSelect.setColor(mPaintPageFill);
        mNotSelect.setStroke(mStrokeColor,(int)mStrokeWidth);
        mNotSelect.setCornerRadius(mRoundRadius);

        mSelected = new GradientDrawable();
        mSelected.setColor(mFillColor);
        mSelected.setStroke(mStrokeColor,(int)mStrokeWidth);
        mSelected.setCornerRadius(mRoundRadius);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < size; i++) {
            //选中的页面改变小圆点为选中状态，反之为未选中
            if ((position % size) == i) {
                dotViewLists.get(i).setImageDrawable(mSelected);
            } else {
                dotViewLists.get(i).setImageDrawable(mNotSelect);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public  void  setViewPager(ViewPager pager){
        this.size = pager.getAdapter().getCount();

        pager.addOnPageChangeListener(this);

        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params
                    = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

            //为小圆点左右添加间距
            params.leftMargin = 10;
            params.rightMargin = 10;
            //手动给小圆点一个大小
            params.height = imgSize;
            params.width = imgSize;
            if (i == 0) {
                imageView.setImageDrawable(mSelected);
            } else {
                imageView.setImageDrawable(mNotSelect);
            }
            //为LinearLayout添加ImageView
            this.addView(imageView, params);
            dotViewLists.add(imageView);
        }

        this.setGravity(Gravity.CENTER);

    }

}
