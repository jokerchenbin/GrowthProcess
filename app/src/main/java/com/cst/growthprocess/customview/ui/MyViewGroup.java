package com.cst.growthprocess.customview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/11/30 0030
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class MyViewGroup extends ViewGroup {

    private static final String TAG = "MyViewGroup";

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取宽度和高度的测量模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int hightMode = MeasureSpec.getMode(heightMeasureSpec);
        int hightSize = MeasureSpec.getSize(heightMeasureSpec);

        //计算所有childrenView 的高度和宽度  -----  ？
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //记录测量模式为wrap_content时候的宽度和高度
        int width;
        int height;

        //获取children的数量
        int childNum = getChildCount();

        //记录children view 的宽度和高度
        int cWidth;
        int cHeight;

        MarginLayoutParams params = null;


        //记录左边两个view的高度
        int lHeight = 0;
        //记录右边两个view的高度
        int rHeight = 0;
        //记录上边两个view的宽度
        int tWidth = 0;
        //记录底部两个view的宽度
        int bWidth = 0;

        /**
         * 通过遍历来确定值
         */
        for (int i = 0; i < childNum; i++) {
            View view = getChildAt(i);
            cWidth = view.getMeasuredWidth();
            cHeight = view.getMeasuredHeight();
            Log.v(TAG, "--cWidth----" + cWidth + "--cHeight--  " + cHeight);
            params = (MarginLayoutParams) view.getLayoutParams();

            if (i == 0 || i == 2) {
                //记录左边两个view的高度
                lHeight = cHeight + params.topMargin + params.bottomMargin;
            }
            if (i == 1 || i == 3) {
                //记录右边两个view的高度
                rHeight = cHeight + params.topMargin + params.bottomMargin;
            }
            if (i == 0 || i == 1) {
                //记录上边两个view的宽度
                tWidth = cWidth + params.leftMargin + params.rightMargin;
            }
            if (i == 2 || i == 3) {
                //记录底部两个view的宽度
                bWidth = cWidth + params.leftMargin + params.rightMargin;
            }
        }

        //取最大值来设置为宽和高
        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        if (hightMode == MeasureSpec.EXACTLY) {
            height = hightSize;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childrenNum = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams params = null;

        for (int i = 0; i < childrenNum; i++) {
            View view = getChildAt(i);
            cWidth = view.getMeasuredWidth();
            cHeight = view.getMeasuredHeight();
            params = (MarginLayoutParams) view.getLayoutParams();


            //child view 的 left、top、right、button
            int cl, ct, cr, cb;// ---- ? 这个几个值对应哪个位置？

            cl = ct = cr = cb = 0;

            switch (i) {
                case 0:
                    cl = params.leftMargin;
                    ct = params.topMargin;
                    cr = cl + cWidth;
                    cb = ct + cHeight;
                    break;
                case 1:
                    cl = getWidth() - cWidth - params.rightMargin;
                    ct = params.topMargin;
                    cr = cl + cWidth;
                    cb = ct + cHeight;
                    break;
                case 2:
                    cl = params.leftMargin;
                    ct = getHeight() - cHeight - params.topMargin - params.bottomMargin;
                    cr = cl + cWidth;
                    cb = ct + cHeight;
                    break;
                case 3:
                    cl = getWidth() - cWidth - params.leftMargin - params.rightMargin;
                    ct = getHeight() - cHeight - params.topMargin - params.bottomMargin;
                    cr = cl + cWidth;
                    cb = ct + cHeight;
                    break;
                case 4:
                    cl = getWidth() / 2 - cWidth / 2;
                    ct = getHeight() / 2 - cHeight / 2;
                    cr = cl + cWidth;
                    cb = ct + cHeight;
                    break;
                default:
            }


            view.layout(cl, ct, cr, cb);
        }

    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
