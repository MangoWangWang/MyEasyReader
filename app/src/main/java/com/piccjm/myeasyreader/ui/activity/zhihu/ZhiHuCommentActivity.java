package com.piccjm.myeasyreader.ui.activity.zhihu;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.piccjm.myeasyreader.R;
import com.piccjm.myeasyreader.adapter.HomeFragmentPageAdapter;
import com.piccjm.myeasyreader.ui.activity.base.BaseActivity;
import com.piccjm.myeasyreader.ui.fragment.home.child.zhihu.ZhiHuCommentFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuCommentActivity extends BaseActivity {

    @BindView(R.id.toolbar_zhihu_comment)
    Toolbar toolbarZhihuComment;
    @BindView(R.id.tab_zhihu_comment)
    TabLayout tabZhihuComment;
    @BindView(R.id.vp_zhihu_comment)
    ViewPager vpZhihuComment;

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    private HomeFragmentPageAdapter myAdapter;
    private int shortNum;
    private int longNum;
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int allNum = intent.getExtras().getInt("allNum");
        shortNum = intent.getExtras().getInt("shortNum");
        longNum = intent.getExtras().getInt("longNum");
        id = intent.getExtras().getInt("id");
        setToolBar(toolbarZhihuComment,String.format("%d条评论",allNum));
        initFragmentList();
        myAdapter = new HomeFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vpZhihuComment.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabZhihuComment.setTabMode(TabLayout.MODE_FIXED);
        tabZhihuComment.setupWithViewPager(vpZhihuComment);
    }

    private void initFragmentList() {
        if (mTitleList.size() != 0) {
            return;
        }
        mTitleList.add(String.format("短评论(%d)",shortNum));
        mTitleList.add(String.format("长评论(%d)", longNum));
        mFragments.add(new ZhiHuCommentFragment());
        mFragments.add(new ZhiHuCommentFragment());
    }

    public int getId() {
        return id;
    }

}
