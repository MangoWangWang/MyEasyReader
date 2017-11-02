package com.piccjm.myeasyreader.ui.fragment.home.child.zhihu;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.SPUtils;
import com.piccjm.myeasyreader.R;
import com.piccjm.myeasyreader.adapter.ZhiHuAdapter;
import com.piccjm.myeasyreader.bean.DailyListBean;
import com.piccjm.myeasyreader.bean.HomeListBean;
import com.piccjm.myeasyreader.presenter.ZhiHuPresenter;
import com.piccjm.myeasyreader.presenter.impl.ZhiHuPresenterImpl;
import com.piccjm.myeasyreader.ui.activity.zhihu.AdjustmentHomeListActivity;
import com.piccjm.myeasyreader.ui.activity.zhihu.ZhiHuDetailActivity;
import com.piccjm.myeasyreader.ui.activity.zhihu.ZhihuThemeActivity;
import com.piccjm.myeasyreader.ui.fragment.BaseFragment;
import com.piccjm.myeasyreader.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by mangowangwang on 2017/10/30.
 */

public class ZhiHuHomeFragment extends BaseFragment<ZhiHuPresenterImpl> implements ZhiHuPresenter.View {

    @BindView(R.id.xrv_zhihu)
    RecyclerView rvZhihu;
    private Banner banner;
    private ZhiHuAdapter zhiHuAdapter;
    private List<HomeListBean> homeList;

    @Override
    protected void initView() {
    }

    @Override
    protected void loadData() {

        mPresenter.fetchData();
    }

    @Override
    public void onResume() {
        SPUtils spUtils = new SPUtils("home_list");
        if (spUtils.getBoolean("home_list_ischange")) {
            homeList = mPresenter.getHomeList();
            zhiHuAdapter.setNewData(homeList);
            zhiHuAdapter.notifyDataSetChanged();
            spUtils.putBoolean("home_list_ischange", false);
        }
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_everyday;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void refresh() {
        homeList = mPresenter.getHomeList();
        List<DailyListBean.TopStoriesBean> topStoriesList = mPresenter.getTopStoriesList();
        if (homeList.size() == 12) {
            zhiHuAdapter = new ZhiHuAdapter(homeList, getActivity());
            View footerView = getActivity().getLayoutInflater().inflate(R.layout.item_zhihu_footer, (ViewGroup) rvZhihu.getParent(), false);
            View headerView = getActivity().getLayoutInflater().inflate(R.layout.item_zhihu_header, (ViewGroup) rvZhihu.getParent(), false);
            banner = (Banner) headerView.findViewById(R.id.banner);
            TextView tvZhihuHomeFooter = (TextView) footerView.findViewById(R.id.tv_zhihu_home_footer);
            initBanner(topStoriesList);
            zhiHuAdapter.addFooterView(footerView);
            zhiHuAdapter.addHeaderView(headerView);
            rvZhihu.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvZhihu.setAdapter(zhiHuAdapter);
            tvZhihuHomeFooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), AdjustmentHomeListActivity.class));
                }
            });
            zhiHuAdapter.setOnItemClickListener(new ZhiHuAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int id, View view) {
                    startZhiHuDetailActivity(id, view);
                }

                @Override
                public void OnItemThemeClickListener(int id, View view) {
                    startZhihuThemeActivity("id", id);
                }

                @Override
                public void OnItemSectionClickListener(int id, View view) {
                    startZhihuThemeActivity("section_id", id);
                }
            });
        }
    }

    private void startZhihuThemeActivity(String name, int id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ZhihuThemeActivity.class);
        intent.putExtra(name, id);
        getActivity().startActivity(intent);
    }

    private void startZhiHuDetailActivity(int id, View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ZhiHuDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("isNotTransition", true);
        /**
         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
         *     <android.support.design.widget.AppBarLayout
         android:transitionName="zhihu_detail_title"
         android:fitsSystemWindows="true">
         */
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                view, getActivity().getResources().getString(R.string.zhihu_detail_title));
        getActivity().startActivity(intent, options.toBundle());
    }

    private void initBanner(final List<DailyListBean.TopStoriesBean> topStoriesList) {
        banner.startAutoPlay();
        banner.setDelayTime(3000);
        List<String> imageList = new ArrayList<>();
        for (DailyListBean.TopStoriesBean topStoriesBean : topStoriesList) {
            imageList.add(topStoriesBean.getImage());
        }
        banner.setImages(imageList).setImageLoader(new GlideImageLoader()).start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                int id = topStoriesList.get(position).getId();
                startZhiHuDetailActivity(id, banner);
            }
        });
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (banner != null) {
            banner.start();
        }
    }

    @Override
    protected void onInvisible() {
        // 不可见时轮播图停止滚动
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }
}