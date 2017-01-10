package com.cst.growthprocess.UI.viewTipModule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.cst.growthprocess.Utils.ViewUtils;


/**
 * Copyright 2012-2014  CST.All Rights Reserved
 *
 * Comments：空白页视图（加载视图、空白异常、空白数据操作）
 *
 * @author dong
 * @Time: 2016/8/9
 *
 * Modified By: ***
 * Modified Date: ***
 * Why & What is modified:
 */
public class CustomDataEmptyView extends RelativeLayout implements EmptyHandler {

    private LoadingLayout viewLoading;

    private ExceptionLayout viewException;

    private ResultLayout viewResult;

    private ViewGroup customLayout;

    public CustomDataEmptyView(Context context) {
        super(context);
        init();
    }

    public CustomDataEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomDataEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewLoading = new LoadingLayout(getContext());
        viewException = new ExceptionLayout(getContext());
        viewResult = new ResultLayout(getContext());
        customLayout = new ResultLayout(getContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        viewLoading.setLayoutParams(lp);
        viewException.setLayoutParams(lp);
        viewResult.setLayoutParams(lp);
        addView(viewLoading);
        addView(viewException);
        addView(viewResult);
        addView(customLayout);
        ViewUtils.gone(viewLoading, viewException, viewResult,customLayout);
    }

    //============================================ 加载中显示============================================
    public void showLoading(String title1, String title2) {
        viewLoading.show(title1, title2);
        ViewUtils.gone(viewException, viewResult,customLayout);
        ViewUtils.visible(viewLoading);
    }

    //============================================ 失败异常显示============================================
    public void showException(int imageId, String text, String buttonText,
            OnClickListener onClickListener) {
        ViewUtils.gone(viewLoading, viewResult,customLayout);
        ViewUtils.visible(viewException);
        viewException.show(imageId, text, buttonText, onClickListener);
    }

    public void showException(int imageId, int textId, String buttonText,
            OnClickListener onClickListener) {
        ViewUtils.gone(viewLoading, viewResult,customLayout);
        ViewUtils.visible(viewException);
        showException(imageId, getContext().getResources().getText(textId).toString(),
                buttonText, onClickListener);
    }

    public void showException(int imageId, String text) {
        showException(imageId, text, null, null);
    }

    public void showException(int imageId, int textDeputyId) {
        showException(imageId, getContext().getResources().getText(textDeputyId).toString());
    }

    //============================================ 加载结果显示============================================
    public void showTip(int imageId, String textMain, String textDeputy) {
        viewResult.showTipLayout(imageId, textMain, textDeputy);
        ViewUtils.gone(viewLoading, viewException,customLayout);
        ViewUtils.visible(viewResult);
    }


    public void showTip(int imageId, int textMainId, int textDeputyId) {
        viewResult.showTipLayout(imageId,
                getContext().getResources().getText(textMainId).toString(),
                getContext().getResources().getText(textDeputyId).toString());
    }


    public void showResult(int imageId, String textMain, String textDeputy1,
            String textDeputy2,
            String textExtra,
            String buttonCommon1, String buttonCommon2, String buttonMain,
            OnClickListener onClickListener) {
        ViewUtils.gone(viewLoading, viewException);
        ViewUtils.visible(viewResult);
        viewResult.show(imageId, textMain, textDeputy1, textDeputy2, textExtra, buttonCommon1,
                buttonCommon2,
                buttonMain,
                onClickListener);
    }

    //============================================ 自定义布局显示============================================


    public void showResultLayout(int layoutId) {
        customLayout.removeAllViews();
        View view= LayoutInflater.from(getContext()).inflate(layoutId,null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        customLayout.addView(view);
        ViewUtils.gone(viewLoading, viewException,viewResult);
        ViewUtils.visible(customLayout);
    }

    // *************************************************实现方法****************************************

    @Override
    public void initUILayout(ViewGroup mainGroup, boolean isEnableTouchClick,
            final ViewTipModule.EmptyViewClickCallback callback) {
        // 添加视图到主布局文件
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        mainGroup.addView(this, lp);
        // 空白视图触摸事件
        if (isEnableTouchClick) {
            this.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (callback != null) {
                        callback.setEmptyViewClick();
                    }
                    return false;
                }
            });
        } else {
            this.setOnTouchListener(null);
        }

    }

    @Override
    public void setView(int type) {
        setVisibility(type);
    }

    @Override
    public void showLoadingView(String loadStr1, String loadStr2) {
        showLoading(loadStr1, loadStr2);
    }

    @Override
    public void showExceptionLayoutNoButton(int imageId, int textId) {
        showException(imageId, textId);
    }


    @Override
    public void showExceptionLayoutWithButton(int imageId, int textId, String buttonText,
            OnClickListener onClickListener) {
        showException(imageId, textId, buttonText, onClickListener);
    }

    @Override
    public void showTipLayout(int imageId, int textMain, int textDeputy) {
        showTip(imageId, textMain, textDeputy);
    }

    @Override
    public void showTipLayout(int imageId, String textMain, String textDeputy) {
        showTip(imageId, textMain, textDeputy);
    }

    @Override
    public void showResultLayout(int imageId, String textMain, String textDeputy1,
            String textDeputy2, String textExtra,
            String buttonCommon1, String buttonCommon2, String buttonMain,
            OnClickListener onClickListener) {
        showResult(imageId, textMain, textDeputy1, textDeputy2, textExtra, buttonCommon1,
                buttonCommon2, buttonMain,
                onClickListener);
    }

    @Override
    public void showEmptyLayout(int layoutId) {
        showResultLayout(layoutId);
    }
}
