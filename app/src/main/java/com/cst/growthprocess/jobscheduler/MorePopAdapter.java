package com.cst.growthprocess.jobscheduler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.UI.SmallDotView;
import com.cst.growthprocess.Utils.SharedPreferencesUtils;
import com.cst.growthprocess.Utils.ViewUtils;


/**
 * Copyright2012-2015  CST.All Rights Reserved
 *
 * Comments：驾图首页右上角更多PopAdapter
 *
 * @author liujl
 *
 *         Time: 2016/8/24
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class MorePopAdapter extends BaseAdapter {

    private Activity mActivity;

    private String[] mFunctions = {"发起聊天", "添加朋友", "扫  一  扫"};

    private int[] mImgResId = {R.drawable.more_initiate_chat_selector,
                               R.drawable.more_create_friend_selector,
                               R.drawable.more_sweep_selector};

    private ListPopupWindow mPopupWindow;

    private LayoutInflater inflater = null;

    public MorePopAdapter(Activity activity, ListPopupWindow popupWindow) {
        mActivity = activity;
        mPopupWindow = popupWindow;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return mFunctions.length;
    }

    @Override
    public String getItem(int position) {
        return mFunctions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.kplay_mine_popwin_item, null);
        }

        final RelativeLayout layout = ViewHolder.get(convertView, R.id.kplay_mine_layout);

        ImageView functionImg = ViewHolder.get(convertView, R.id.kplay_mine_item_img);
        TextView functionTv = ViewHolder.get(convertView, R.id.kplay_mine_item_tv);
       // ImageView tipIcon = ViewHolder.get(convertView, R.id.create_friend_tip);
        SmallDotView tipIcon = ViewHolder.get(convertView, R.id.create_friend_tip);

        //设置小红点显示隐藏
        if (position == 1) {
            if (!SharedPreferencesUtils.isClickedAddFriend(mActivity)) {
                ViewUtils.visible(tipIcon);
            } else {
                ViewUtils.gone(tipIcon);
            }
        } else {
            ViewUtils.gone(tipIcon);
        }

        functionImg.setImageResource(mImgResId[position]);
        functionTv.setText(getItem(position));

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                       /* KartorStatsCommonAgent
                                .onEvent(mActivity, UserEventConsts.APP_HOME_PLUS_POP_CHAT_CLICK);
                        ActivityNav.home()
                                .startChooseFriendActivity(mActivity,
                                        ((BaseActivity) mActivity).getPageInfo());*/
                        break;
                    case 1:
                       /* KartorStatsCommonAgent
                                .onEvent(mActivity,
                                        UserEventConsts.APP_HOME_PLUS_POP_ADD_FRIEND_CLICK);
                        ActivityNav.user().startAddFriend(mActivity,
                                ((BaseActivity) mActivity).getPageInfo(), true);*/
                        break;
                    case 2:
                       /* KartorStatsCommonAgent
                                .onEvent(mActivity, UserEventConsts.APP_HOME_PLUS_SCAN_CLICK);
                        ActivityNav.qrcode().startScanQRCodePage(mActivity,
                                ((BaseActivity) mActivity).getPageInfo());*/
                        break;
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        return convertView;
    }
}
