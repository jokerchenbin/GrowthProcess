package com.cst.growthprocess.recyclerViewDemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.recyclerViewDemo.bean.Notes;
import com.cst.growthprocess.recyclerViewDemo.holder.ListViewHolder;
import com.cst.growthprocess.recyclerViewDemo.inter.OnItemListener;

import java.util.ArrayList;
import java.util.List;

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

public class ListAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<Notes.DataBean.BooksBean> mBooks = new ArrayList<>();
    private OnItemListener mOnItemListener;

    public ListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void setOnItemListener(OnItemListener onItemListener) {
        mOnItemListener = onItemListener;
    }


    /**
     * 设置数据项
     */
    public void setData(List<Notes.DataBean.BooksBean> books) {
        mBooks.addAll(books);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        mBooks.remove(mBooks.get(position));
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_listview_item, parent, false);
        ListViewHolder holder = new ListViewHolder(view,mOnItemListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Notes.DataBean.BooksBean booksBean = mBooks.get(position);
        ((ListViewHolder) holder).bindData(booksBean);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

}
