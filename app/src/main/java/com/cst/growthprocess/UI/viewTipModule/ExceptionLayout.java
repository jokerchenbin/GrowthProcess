package com.cst.growthprocess.UI.viewTipModule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.MyTextUtils;
import com.cst.growthprocess.Utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 异常视图
 */
public class ExceptionLayout extends LinearLayout {


    @InjectView(R.id.tip_img)
    public ImageView mTipView;

    @InjectView(R.id.general_failure_prompt)
    public TextView mTextPrompt;

    @InjectView(R.id.load_fail_btn)
    public Button mButton;

    public ExceptionLayout(Context context) {
        super(context);
        init();
    }

    public ExceptionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExceptionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        View view = inflate(getContext(), R.layout.common_load_page_exception, null);
        ButterKnife.inject(this, view);
        this.addView(view);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
    }

    protected void show(int imageId, String text, String buttonText,
            OnClickListener onClickListener) {
        mTipView.setImageResource(imageId);
        mTextPrompt.setText(text);
        mButton.setText(buttonText);
        mButton.setOnClickListener(onClickListener);

        if (imageId != 0) {
            mTipView.setImageResource(imageId);
            ViewUtils.visible(mTipView);
        } else {
            ViewUtils.invisible(mTipView);
        }
        if (MyTextUtils.isNotBlank(text)) {
            ViewUtils.visible(mTextPrompt);
            mTextPrompt.setText(text);
        } else {
            ViewUtils.gone(mTextPrompt);
        }
        if (MyTextUtils.isNotBlank(buttonText)) {
            ViewUtils.visible(mButton);
            mButton.setText(buttonText);
        } else {
            ViewUtils.gone(mButton);
        }
    }

}
