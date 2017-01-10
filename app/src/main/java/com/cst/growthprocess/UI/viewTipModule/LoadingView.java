package com.cst.growthprocess.UI.viewTipModule;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.cst.growthprocess.R;

/**
 * Created by Xupeng on 2014/6/6.
 */
public class LoadingView extends ProgressBar {

    public LoadingView(Context context) {
        super(context);
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //indeterminateDuration
        setIndeterminate(true);
        setIndeterminateDrawable(getResources().getDrawable(R.drawable.load_icon));
    }
}
