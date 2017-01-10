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
 * 数据选择视图
 */
public class ResultLayout extends LinearLayout {


    @InjectView(R.id.tip_img)
    public ImageView mTipView;

    @InjectView(R.id.no_data_main_prompt)
    public TextView mTextMain;

    @InjectView(R.id.no_data_deputy_prompt_one)
    public TextView mTextDeputy1;

    @InjectView(R.id.no_data_deputy_prompt_second)
    public TextView mTextDeputy2;

    @InjectView(R.id.no_data_extra_prompt)
    public TextView mTextExtra;

    @InjectView(R.id.no_data_common_btn1)
    public Button mCommonButton1;

    @InjectView(R.id.no_data_common_btn2)
    public Button mCommonButton2;

    @InjectView(R.id.no_data_main_btn)
    public Button mMainButton;

    public ResultLayout(Context context) {
        super(context);
        init();
    }

    public ResultLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResultLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.common_load_page_result, null);
        ButterKnife.inject(this, view);
        this.addView(view);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
    }

    public void showTipLayout(int imageId, String textMain, String textDeputy1) {
        show(imageId, textMain, textDeputy1, null, null, null, null,null, null);
    }


    public void show(int imageId, String textMain, String textDeputy1, String textDeputy2,
            String textExtra, String buttonCommon1, String buttonCommon2, String buttonMain,
            OnClickListener onClickListener) {
        if (imageId != 0) {
            mTipView.setImageResource(imageId);
            ViewUtils.visible(mTipView);
        } else {
            ViewUtils.invisible(mTipView);
        }
        if (MyTextUtils.isNotBlank(textMain)) {
            ViewUtils.visible(mTextMain);
            mTextMain.setText(textMain);
        } else {
            ViewUtils.gone(mTextMain);
        }
        if (MyTextUtils.isNotBlank(textDeputy1)) {
            ViewUtils.visible(mTextDeputy1);
            mTextDeputy1.setText(textDeputy1);
        } else {
            ViewUtils.gone(mTextDeputy1);
        }
        if (MyTextUtils.isNotBlank(textDeputy2)) {
            ViewUtils.visible(mTextDeputy2);
            mTextDeputy2.setText(textDeputy2);
        } else {
            ViewUtils.gone(mTextDeputy2);
        }
        if (MyTextUtils.isNotBlank(textExtra)) {
            ViewUtils.visible(mTextExtra);
            mTextExtra.setText(textExtra);
        } else {
            ViewUtils.gone(mTextExtra);
        }
        if (MyTextUtils.isNotBlank(buttonCommon1)) {
            ViewUtils.visible(mCommonButton1);
            mCommonButton1.setText(buttonCommon1);
        } else {
            ViewUtils.gone(mCommonButton1);
        }
        if (MyTextUtils.isNotBlank(buttonCommon2)) {
            ViewUtils.visible(mCommonButton2);
            mCommonButton2.setText(buttonCommon2);
        } else {
            ViewUtils.gone(mCommonButton2);
        }
        if (MyTextUtils.isNotBlank(buttonMain)) {
            ViewUtils.visible(mMainButton);
            mMainButton.setText(buttonMain);
        } else {
            ViewUtils.gone(mMainButton);
        }
        if (onClickListener != null) {
            mCommonButton1.setOnClickListener(onClickListener);
            mCommonButton2.setOnClickListener(onClickListener);
            mMainButton.setOnClickListener(onClickListener);
        }

    }
}
