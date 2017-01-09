package com.cst.growthprocess.fragment_viewpager.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

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

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> mFragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public void setFragments(ArrayList<Fragment> list) {
        mFragments.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }


    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }
}
