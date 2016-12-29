package com.cst.growthprocess.recyclerViewDemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.recyclerViewDemo.bean.Notes;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/12/5 0005
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class WaterFallHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.masonry_item_img)
    ImageView mMasonryItemImg;
    @InjectView(R.id.masonry_item_title)
    TextView mMasonryItemTitle;


    public WaterFallHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    /**
     * 绑定数据
     */
    public void bindData(Notes.DataBean.BooksBean booksBean) {
        ImageLoader.getInstance().displayImage(booksBean.getHeadImage(), mMasonryItemImg);
        mMasonryItemTitle.setText(booksBean.getTitle());
    }
}
