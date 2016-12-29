package com.cst.growthprocess.UI;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.cst.growthprocess.R;


/**
 * CST.All Rights Reserved
 *
 * Comments：纯小红点，应用中统一小红点提示控件
 *
 * @author mabo
 *
 *         Time: 2016/12/10
 * @version 4.9.0
 */
public class SmallDotView extends View {

    public SmallDotView(Context context) {
        super(context);
        init();
    }

    public SmallDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmallDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.circle_red_dot);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int w = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, 8);
        int h = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, 8);

        setMeasuredDimension(w, h);
    }

    // 方法一
    public float getRawSize(int unit, float value) {

        // getRawSize(TypedValue.COMPLEX_UNIT_DIP, 100),
        Resources res = this.getResources();
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics());
    }

    // 方法2，需先在values中dimens的进行设置
    public int getIntFromDimens(int index) {

        // getIntFromDimens(R.dimen.small_red_dot_size)
        int result = this.getResources().getDimensionPixelSize(index);
        return result;
    }
}
