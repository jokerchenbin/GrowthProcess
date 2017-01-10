package com.cst.growthprocess.UI.viewTipModule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cst.growthprocess.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 加载视图
 */
public class LoadingLayout extends LinearLayout {

    @InjectView(R.id.loading_v)
    public LoadingView mLoadingLayout;

    @InjectView(R.id.tip_text_big)
    public TextView mTextBigView;

    @InjectView(R.id.tip_text)
    public TextView mTextView;

    public LoadingLayout(Context context) {
        super(context);
        init();
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.common_load_page_loading, null);
        ButterKnife.inject(this, view);
        this.addView(view);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
    }

    protected void show(String titleMain, String titleSecond) {
        mTextBigView.setText(titleMain);
        mTextView.setText(titleSecond);
    }


}
