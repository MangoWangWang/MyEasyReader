package com.piccjm.myeasyreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by quantan.liu on 2017/3/22.
 */

public class HomeFragmentPageAdapter  extends FragmentPagerAdapter {

    private  List<Fragment> fragmentList;  // 用于4个子页面的fragment
    private List<String> mTitleList; // 标题列表

    public HomeFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public HomeFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitleList){
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitleList = mTitleList;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return (fragmentList == null ? 0 :fragmentList.size());
    }
    /**
     * 首页显示title，每日推荐等..
     * 若有问题，移到对应单独页面
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null) {
            return mTitleList.get(position);
        } else {
            return "";
        }
    }
}
