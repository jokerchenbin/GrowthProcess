package com.cst.growthprocess.jobscheduler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListPopupWindow;
import android.widget.ListView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class JobschedulerActivity extends AppCompatActivity {

    @InjectView(R.id.header_right_icon)
    ImageButton mHeaderRightIcon;
    @InjectView(R.id.header_right_icon_2)
    ImageButton mHeaderRightIcon2;

    private Context mActivity;

    /**
     * 右上角弹框
     */
    private ListPopupWindow mMorePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobscheduler);
        ButterKnife.inject(this);
        Intent startServiceIntent = new Intent(this, MyJobService.class);
        startService(startServiceIntent);
        mActivity = this;
        initMoreMenuPopup();
    }


    @OnClick({R.id.header_right_icon, R.id.header_right_icon_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_right_icon:
                showMoreMenuPopup(mHeaderRightIcon);
                Log.v("JobschedulerActivity", "---------------");
                break;
            case R.id.header_right_icon_2:
                showMoreMenuPopup(mHeaderRightIcon2);
                break;
        }
    }


    /**
     * PopupWindow悬浮窗初始化
     */
    private void initMoreMenuPopup() {
        mMorePopup = new ListPopupWindow(this);
        MorePopAdapter morePopAdapter = new MorePopAdapter(this, mMorePopup);
        mMorePopup.setAdapter(morePopAdapter);
        mMorePopup.setBackgroundDrawable(this.getResources()
                .getDrawable(R.drawable.kplay_mine_bg));
        mMorePopup.setModal(true);
        mMorePopup.setWidth(ViewUtils.dip2px(this, 126));
        mMorePopup.setHeight(ViewUtils.dip2px(this, 150));
        mMorePopup.setInputMethodMode(ListPopupWindow.INPUT_METHOD_NOT_NEEDED);
    }

    /**
     * PopupWindow悬浮窗显示设置
     */
    private void showMoreMenuPopup(View anchorView) {
        if (mMorePopup == null) {
            initMoreMenuPopup();
        }
        mMorePopup.setAnchorView(anchorView);
        mMorePopup.setHorizontalOffset(ViewUtils
                .dip2px(mActivity, -121 + (px2dip(mActivity, anchorView.getWidth()))));
        mMorePopup.setVerticalOffset(-180);

        mMorePopup.show();

        ListView popListView = mMorePopup.getListView();

        popListView.setDivider(
                mActivity.getResources().getDrawable(R.drawable.kplay_item_line));

        popListView.setDividerHeight(ViewUtils.dip2px(mActivity, 0.5f));
        popListView.setVerticalScrollBarEnabled(false);
        popListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mMorePopup.show();
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
