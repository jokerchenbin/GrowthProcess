package com.cst.growthprocess.recyclerViewDemo.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ToastDiy;
import com.cst.growthprocess.recyclerViewDemo.bean.Notes;
import com.cst.growthprocess.recyclerViewDemo.inter.OnItemListener;
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

public class ListViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.list_item_pic)
    ImageView mListItemPic;
    @InjectView(R.id.list_item_title)
    TextView mListItemTitle;
    @InjectView(R.id.list_item_content)
    TextView mListItemContent;

    private OnItemListener mListener;

    private View view;

    public ListViewHolder(View itemView, final OnItemListener mListener) {
        super(itemView);
        view = itemView;
        this.mListener = mListener;
        initViewClick();
        ButterKnife.inject(this, view);
    }

    /**
     * 对View设置监听
     */
    private void initViewClick() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("ListViewHolder", "onClick------------");
                if (null != mListener) {
                    mListener.OnItemClickListener(getAdapterPosition());
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.v("ListViewHolder", "setOnLongClickListener------------");
                if (null != mListener) {
                    mListener.OnItemLongClickListener(getAdapterPosition());
                }
                return true;
            }
        });
    }


    /**
     * 绑定数据
     */
    public void bindData(Notes.DataBean.BooksBean booksBean) {
        Log.v("ListViewHolder", booksBean.getBookUrl());
        ImageLoader.getInstance().displayImage(booksBean.getHeadImage(), mListItemPic);
        mListItemTitle.setText(booksBean.getTitle());
        mListItemContent.setText(booksBean.getText());
    }

}
