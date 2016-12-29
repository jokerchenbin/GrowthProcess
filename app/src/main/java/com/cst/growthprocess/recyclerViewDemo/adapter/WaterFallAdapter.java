package com.cst.growthprocess.recyclerViewDemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.recyclerViewDemo.bean.Notes;
import com.cst.growthprocess.recyclerViewDemo.holder.WaterFallHolder;

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

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallHolder> {


    private Context mContext;
    private LayoutInflater mInflater;
    List<Notes.DataBean.BooksBean> books = new ArrayList<>();

    public WaterFallAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<Notes.DataBean.BooksBean> books) {
        this.books.addAll(books);
        notifyDataSetChanged();
    }


    @Override
    public WaterFallHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WaterFallHolder holder = new WaterFallHolder(mInflater.inflate(R.layout.water_fall_item, parent, false));

//        mInflater.inflate(R.layout.water_fall_item, parent, false);
//        mInflater.inflate(R.layout.water_fall_item,null);
        return holder;
    }

    @Override
    public void onBindViewHolder(WaterFallHolder holder, int position) {
        holder.bindData(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
