package com.cst.growthprocess.fragment_viewpager;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.cst.growthprocess.R;
import com.cst.growthprocess.UI.IndicatorView;
import com.cst.growthprocess.UI.ViewPagerIndicator;
import com.cst.growthprocess.fragment_viewpager.adapter.ViewPagerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FragmentDemoActivity extends AppCompatActivity {

    @InjectView(R.id.tab1)
    RadioButton mTab1;

    @InjectView(R.id.tab2)
    RadioButton mTab2;

    @InjectView(R.id.tab3)
    RadioButton mTab3;

    @InjectView(R.id.tab4)
    RadioButton mTab4;

    @InjectView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @InjectView(R.id.activity_fragment_demo)
    RelativeLayout mActivityFragmentDemo;

    @InjectView(R.id.viewpager)
    ViewPager mViewpager;


    @InjectView(R.id.ll)
    LinearLayout mLl;

    @InjectView(R.id.indicatorView)
    IndicatorView mIndicatorView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        ButterKnife.inject(this);
        mContext = this;
        initView();
    }

    /**
     * 初始化视图组件
     */
    private void initView() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        Tab1Fragment fragment = new Tab1Fragment();
        fragmentList.add(0, fragment);
        fragment = new Tab1Fragment();
        fragmentList.add(1, fragment);
        fragment = new Tab1Fragment();
        fragmentList.add(2, fragment);
        fragment = new Tab1Fragment();
        fragmentList.add(3, fragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.setFragments(fragmentList);
        mViewpager.setAdapter(adapter);

        ViewPagerIndicator indicator = new ViewPagerIndicator(mContext, mViewpager, mLl);
        mViewpager.addOnPageChangeListener(indicator);

        mIndicatorView.setViewPager(mViewpager);


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab1:
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.tab2:
                        mViewpager.setCurrentItem(1);
                        break;
                    case R.id.tab3:
                        mViewpager.setCurrentItem(2);
                        break;
                    case R.id.tab4:
                        mViewpager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }

            }
        });
    }


}
