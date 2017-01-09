package com.cst.growthprocess.fragment_viewpager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cst.growthprocess.R;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2017/1/9 0009
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class Tab1Fragment extends Fragment {


    @InjectView(R.id.text)
    TextView mText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        super.onActivityCreated(savedInstanceState);
    }


    private String[] str = new String[]{"abc", "ABC", "ABCDDDDDD", "DAKJGKLADJGS","abc", "ABC", "ABCDDDDDD", "DAKJGKLADJGS"};

    /**
     * 初始化
     */
    private void initView() {
        int a = (int)(1+Math.random()*4);
        mText.setText(str[a]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
