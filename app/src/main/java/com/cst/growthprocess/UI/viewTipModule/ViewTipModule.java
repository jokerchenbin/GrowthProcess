package com.cst.growthprocess.UI.viewTipModule;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ViewUtils;


public class ViewTipModule {

    //空数据提示加载失败
    public final static String EMPTY_DATA_SUGGEST_LOADING_FAILURE = "1001";

    //空数据提示网络异常
    public final static String EMPTY_DATA_PROMPT_NETWORK_ANOMALIES = "1002";

    //手势密码被冻结
    public final static String EMPTY_DATA_PROMPT_GESTURE_FREZZEN = "1003";

    private Context mContext;

    private ViewGroup mMainLayout;

    private View mDataLayot;

    private EmptyHandler mHandler;
//    private CustomDataEmptyView mEmptyView;

    private Callback mCallBack;

    private EmptyViewClickCallback mEmptyViewClickCallback;

    private boolean mIsDefaultLoading = true;

    private boolean mIsEnableTouchClick = false;


    //点击之后是否显示转圈圈
    public boolean isClickShowProcess = true;


    public ViewTipModule(Context context, ViewGroup mianLayout, View dataLayot,
            Callback callBack) {
        this.mContext = context;
        this.mMainLayout = mianLayout;
        this.mDataLayot = dataLayot;
        this.mCallBack = callBack;
        init();
    }

    public ViewTipModule(Context context, ViewGroup mianLayout, View dataLayot,
            boolean isDefaultLoading, Callback callBack) {
        this.mContext = context;
        this.mMainLayout = mianLayout;
        this.mDataLayot = dataLayot;
        this.mCallBack = callBack;
        this.mIsDefaultLoading = isDefaultLoading;
        init();
    }

    public ViewTipModule(Context context, ViewGroup mianLayout, View dataLayot,
            boolean isDefaultLoading, boolean isEnableTouchClick, EmptyViewClickCallback callBack) {
        this.mContext = context;
        this.mMainLayout = mianLayout;
        this.mDataLayot = dataLayot;
        this.mEmptyViewClickCallback = callBack;
        this.mIsDefaultLoading = isDefaultLoading;
        this.mIsEnableTouchClick = isEnableTouchClick;
        init();
    }

    public ViewTipModule(Context context, ViewGroup mianLayout, View dataLayot,
            boolean isDefaultLoading, boolean isEnableTouchClick,
            EmptyViewClickCallback emptyCallback, Callback callBack) {
        this.mContext = context;
        this.mMainLayout = mianLayout;
        this.mDataLayot = dataLayot;
        this.mEmptyViewClickCallback = emptyCallback;
        this.mCallBack = callBack;
        this.mIsDefaultLoading = isDefaultLoading;
        this.mIsEnableTouchClick = isEnableTouchClick;
        init();
    }

    @Deprecated
    public ViewTipModule(Context context, ViewGroup mianLayout, Callback callBack) {
        this(context, mianLayout, null, callBack);
    }

    private void init() {
        mHandler = new CustomDataEmptyView(mContext);
        mHandler.initUILayout(mMainLayout, mIsEnableTouchClick, mEmptyViewClickCallback);
        if (mIsDefaultLoading) {
            // 默认显示加载状态
            showLodingState();
        }

    }

    /**
     * 数据加载成功视图状态
     */
    public void showLodingState() {
        // 切换到提示状态
        changeToEmpty();
        //开始显示加载视图
        showLoadingView();
    }

    /**
     * 数据加载成功视图状态
     */
    public void showSuccessState() {
        // 切换到数据状态
        changeToData();
    }

    /*************************************** 显示加载视图 ***********************************************/
    private void showLoadingView() {
        mHandler.showLoadingView(null, "加载中...");
    }

    /*************************************** 显示加载异常视图 ***********************************************/

    /**
     * 数据加载失败视图状态
     *
     * @param state 异常状态
     */
    public void showFailState(String state) {
        // 切换到提示状态
        changeToEmpty();
        if (state == null || state.isEmpty()) {
            loadDefaultFailed();
        } else {
            if (state.equals(EMPTY_DATA_SUGGEST_LOADING_FAILURE)) {
                //加载失败提示
                loadDefaultFailed();
            } else if (state.equals(EMPTY_DATA_PROMPT_NETWORK_ANOMALIES)) {
                //网络异常提示
                loadCustomFailed(R.mipmap.tip_without_network, R.string.empty_data_suggest11);
            } else if (state.equals(EMPTY_DATA_PROMPT_GESTURE_FREZZEN)) {
                loadFailedNoButton(R.mipmap.tip_no_data_cry, R.string.empty_data_suggest16);
            } else {
                //加载失败提示
                loadDefaultFailed();
            }
        }

    }

    /**
     * 加载失败提示-不带按钮
     */
    private void loadFailedNoButton(int imageId, int textId) {
        mHandler.showExceptionLayoutNoButton(imageId, textId);
    }

    /**
     * 加载失败提示-默认重新加载按钮
     */
    public void loadFailedWithButton(int imageId, int textId) {
        mHandler.showExceptionLayoutWithButton(imageId, textId, "重新加载", new OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始显示加载视图
                if (isClickShowProcess) {//为了让重新加载的时候不显示转圈 2016.11.16
                    showLoadingView();
                }
                if (mCallBack != null) {
                    // 加载数据
                    mCallBack.getData();
                }
            }
        });
    }

    /**
     * 加载失败默认提示-默认重新加载按钮
     */
    private void loadDefaultFailed() {
        loadFailedWithButton(R.mipmap.tip_netwoek_sleep, R.string.empty_data_suggest12);
    }

    /**
     * 加载异常自定义提示-默认重新加载按钮
     */
    private void loadCustomFailed(int imageId, int textId) {
        loadFailedWithButton(imageId, textId);
    }


    /**
     * 暂无数据视图状态
     */
    public void showNoDataState() {
        // 切换到提示状态
        changeToEmpty();
        mHandler.showTipLayout(R.mipmap.tip_no_data_cry, null, "暂无数据");
    }

    /**
     * 暂无数据视图状态
     *
     * @param text 提示文本
     */
    public void showNoDataState(String text) {
        changeToEmpty();
        mHandler.showTipLayout(R.mipmap.tip_no_data_cry, null, text);
    }

    /**
     * 暂无数据视图状态
     */
    public void showNoDataStateWithImgAndText(int drawableRes, String text) {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showTipLayout(drawableRes, null, text);
    }

    /**
     * 暂无数据视图状态
     * 两行文本
     */
    public void showNoDataStateWithImgAndTwoText(int drawableRes, String textBig, String text) {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showTipLayout(drawableRes, textBig, text);
    }

    /**
     * 只显示无数据文本
     */
    public void showNoDataText(String text) {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showTipLayout(0, null, text);
    }

    /**
     * 只默认图片的页面
     */
    public void showDefaultImage() {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showTipLayout(R.mipmap.tip_no_data_sweat, null, null);

    }

    /**
     * 隐藏所有视图
     */
    public void hideAllLayout() {
        // 隐藏数据视图
        if (mDataLayot != null) {
            mDataLayot.setVisibility(View.INVISIBLE);
        }
        // 隐藏提示视图
        mHandler.setView(View.GONE);
    }

    /**
     * 加载成功但是无数据,带一般按钮的
     *
     * @param imageId    图片id
     * @param textMain   主文本
     * @param textDeputy 副文本
     * @param buttonMain 重要按钮文本
     * @param callback   点击监听
     */
    public void showSuccessNoDataStatus(
            String textMain,
            String textDeputy,
            String buttonMain,
            int imageId,
            final BtnClickCallback callback) {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showResultLayout(imageId, textMain, textDeputy, null, null, null, buttonMain, null,
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callback != null) {
                            callback.btnClick();
                        }
                    }
                });
    }

    /**
     * 加载成功但是无数据,带重要按钮的
     *
     * @param imageId    图片id
     * @param textMain   主文本
     * @param textDeputy 副文本
     * @param buttonMain 重要按钮文本
     * @param callback   点击监听
     */
    public void showSuccessNoDataStatusWithMain(
            String textMain,
            String textDeputy,
            String buttonMain,
            int imageId,
            final BtnClickCallback callback) {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showResultLayout(imageId, textMain, textDeputy, null, null, null, null, buttonMain,
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callback != null) {
                            callback.btnClick();
                        }
                    }
                });
    }

    /**
     * 加载成功但是无数据,带一般按钮和重要按钮的
     *
     * @param imageId      图片id
     * @param textMain     主文本
     * @param textDeputy1  副文本1
     * @param textDeputy2  副文本2
     * @param textExtra    补充说明
     * @param buttonCommon 一般按钮文本
     * @param buttonMain   重要按钮文本
     * @param callback     点击监听
     */
    public void showSuccessStatusWithAllButton(int imageId,
            String textMain,
            String textDeputy1,
            String textDeputy2,
            String textExtra,
            String buttonCommon,
            String buttonMain,
            final BtnClickAllCallback callback) {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showResultLayout(imageId, textMain, textDeputy1, textDeputy2, textExtra,
                buttonCommon, null, buttonMain,
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (callback == null) {
                            return;
                        }
                        if (v.getId() == R.id.no_data_common_btn1) {
                            callback.commonBtnClick();
                        } else if (v.getId() == R.id.no_data_main_btn) {
                            callback.mainBtnClick();
                        }

                    }
                });
    }

    /**
     * 加载成功但是无数据,带2个一般按钮的
     *
     * @param imageId       图片id
     * @param textMain      主文本
     * @param textDeputy1   副文本1
     * @param textDeputy2   副文本2
     * @param textExtra     补充说明
     * @param buttonCommon1 一般按钮1文本
     * @param buttonCommon2 一般按钮2文本
     * @param callback      点击监听
     */
    public void showSuccessStatusWithTwoCommonButton(int imageId,
            String textMain,
            String textDeputy1,
            String textDeputy2,
            String textExtra,
            String buttonCommon1,
            String buttonCommon2,
            final BtnClickCommonCallback callback) {
        // 切换到暂无数据状态
        changeToEmpty();
        mHandler.showResultLayout(imageId, textMain, textDeputy1, textDeputy2, textExtra,
                buttonCommon1, buttonCommon2, null,
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (callback == null) {
                            return;
                        }
                        if (v.getId() == R.id.no_data_common_btn1) {
                            callback.commonBtnClick1();
                        } else if (v.getId() == R.id.no_data_common_btn2) {
                            callback.commonBtnClick2();
                        }

                    }
                });
    }


    /**
     * 显示自定义蒙版空视图
     */
    public void showEmptyStatus(int imageId, String textDeputy1) {
        changeToEmpty();
        mHandler.showTipLayout(imageId, null, textDeputy1);

    }

    /**
     * 显示默认蒙版空视图
     */
    public void showDefaultEmptyStatus() {
        changeToEmpty();
        mHandler.showTipLayout(R.mipmap.tip_no_data_cry, null, "这里什么都木有~");
    }


    /**
     * 显示一个自定义的空视图布局
     *
     * @param layoutId 布局id
     */
    public void showEmptyCustomLayout(int layoutId) {
        changeToEmpty();
        mHandler.showEmptyLayout(layoutId);
    }

    /**
     * 切换到数据状态
     */
    private void changeToData() {
        // 显示数据视图
        if (mDataLayot != null) {
            ViewUtils.visible(mDataLayot);
        }
        // 隐藏提示视图
        mHandler.setView(View.GONE);
    }

    /**
     * 切换到提示状态
     */
    public void changeToEmpty() {
        // 隐藏数据视图
        if (mDataLayot != null) {
            mDataLayot.setVisibility(View.INVISIBLE);
        }
        // 显示提示视图
        mHandler.setView(View.VISIBLE);

    }


    public interface Callback {

        public void getData();

    }


    public interface EmptyViewClickCallback {

        public void setEmptyViewClick();

    }

    public interface BtnClickCallback {

        void btnClick();
    }

    public interface BtnClickAllCallback {

        void commonBtnClick();

        void mainBtnClick();
    }

    public interface BtnClickCommonCallback {

        void commonBtnClick1();

        void commonBtnClick2();
    }

}
