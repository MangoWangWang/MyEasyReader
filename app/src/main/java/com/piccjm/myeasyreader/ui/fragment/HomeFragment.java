package com.piccjm.myeasyreader.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.piccjm.myeasyreader.R;
import com.piccjm.myeasyreader.adapter.HomeFragmentPageAdapter;
import com.piccjm.myeasyreader.ui.fragment.home.child.DouBanMovieLatestFragment;
import com.piccjm.myeasyreader.ui.fragment.home.child.DouBanMovieTopFragment;
import com.piccjm.myeasyreader.ui.fragment.home.child.WelfareFragment;
import com.piccjm.myeasyreader.ui.fragment.home.child.zhihu.ZhiHuHomeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quantan.liu on 2017/3/22.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.tab_gank) // 音乐图标下的
    TabLayout tabGank;
    @BindView(R.id.vp_gank)
    ViewPager vpGank;

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    private HomeFragmentPageAdapter myAdapter;
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_gank,null); // 加载布局文件
            ButterKnife.bind(this, inflate); // 实例化fragment控件
        }
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 准备数据
        initFragmentList();
        // 创建适配器
        myAdapter = new HomeFragmentPageAdapter(getChildFragmentManager(), mFragments, mTitleList);
        // 设置适配器和刷新界面
        vpGank.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        // 设置tab模式 fiexd 固定模式 scrollable是可以横行滚动
        tabGank.setTabMode(TabLayout.MODE_FIXED);
        tabGank.setupWithViewPager(vpGank);
    }

    private void initFragmentList() {
        if (mTitleList.size() != 0) {
            return;
        }
        mTitleList.add("知乎日报");
        mTitleList.add("福利");
        mTitleList.add("排行榜");
        mTitleList.add("最新电影");
        mFragments.add(new ZhiHuHomeFragment());
        mFragments.add(new WelfareFragment());
        mFragments.add(new DouBanMovieTopFragment());
        mFragments.add(new DouBanMovieLatestFragment());
    }
}
