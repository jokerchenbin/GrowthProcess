package com.cst.growthprocess.UI.viewTipModule;

import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright 2012-2014  CST.All Rights Reserved
 *
 * Comments：功能描述
 *
 * @author dong
 * @Time: 2016/8/13
 *
 * Modified By: ***
 * Modified Date: ***
 * Why & What is modified:
 */
public interface EmptyHandler {

    /**
     * init UI初始化蒙版UI
     */
    public void initUILayout(ViewGroup mainGroup, boolean isEnableTouchClick,
            ViewTipModule.EmptyViewClickCallback callback);

    /**
     * 设置布局显示隐藏
     */
    public void setView(int type);

    /**
     * 显示加载布局
     *
     * @param loadStr1 主文本
     * @param loadStr2 副文本
     */
    public void showLoadingView(String loadStr1, String loadStr2);

    //异常状态

    /**
     * 显示请求异常--不带链接按钮
     *
     * @param imageId 图片id
     * @param textId  文本Id
     */
    public void showExceptionLayoutNoButton(int imageId, int textId);

    /**
     * 显示请求异常--带链接按钮
     *
     * @param imageId         图片id
     * @param textId          主文本Id
     * @param buttonText      链接按钮文本
     * @param onClickListener 监听回调
     */
    public void showExceptionLayoutWithButton(int imageId, int textId, String buttonText,
            View.OnClickListener onClickListener);

    //数据提示状态

    /**
     * 显示结果提醒布局
     *
     * @param imageId    图片id
     * @param textMain   主文本Id
     * @param textDeputy 副文本Id
     */
    public void showTipLayout(int imageId, int textMain, int textDeputy);

    /**
     * 显示结果提醒布局
     *
     * @param imageId    图片id
     * @param textMain   主文本
     * @param textDeputy 副文本
     */
    public void showTipLayout(int imageId, String textMain, String textDeputy);

    /**
     * 显示加载结果处理页面
     *
     * @param imageId         图片id
     * @param textMain        主文本
     * @param textDeputy1     副文本1
     * @param textDeputy2     副文本2
     * @param textExtra       补充说明
     * @param buttonCommon1   一般按钮文本
     * @param buttonCommon2   一般按钮文本
     * @param buttonMain      重要按钮文本
     * @param onClickListener 点击监听
     */
    public void showResultLayout(int imageId,
            String textMain,
            String textDeputy1,
            String textDeputy2,
            String textExtra,
            String buttonCommon1,
            String buttonCommon2,
            String buttonMain,
            View.OnClickListener onClickListener);

    /**
     * 显示一个布局空视图
     * @param layoutId 布局Id
     */
    public void showEmptyLayout(int layoutId);

}
