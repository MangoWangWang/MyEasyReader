package com.piccjm.myeasyreader.presenter.impl;

/**
 * Created by mangowangwang on 2017/10/30.
 */

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.blankj.utilcode.utils.SPUtils;
import com.piccjm.myeasyreader.bean.DailyListBean;
import com.piccjm.myeasyreader.bean.HomeListBean;
import com.piccjm.myeasyreader.bean.HotListBean;
import com.piccjm.myeasyreader.bean.SectionListBean;
import com.piccjm.myeasyreader.bean.ThemeListBean;
import com.piccjm.myeasyreader.http.utils.Callback;
import com.piccjm.myeasyreader.http.utils.RetrofitZhiHuUtils;
import com.piccjm.myeasyreader.presenter.BasePresenter;
import com.piccjm.myeasyreader.presenter.ZhiHuPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by quantan.liu on 2017/3/22.
 * 首页数据根据多个接口的返回，自己进行拼接。
 * 模板public class XXXPresenterImpl extends BasePresenter<XXXPresenter.View> implements XXXPresenter.Presenter
 * 除了XXX以外的都是一样的，方法按照实现来。
 * 构造方法要注意按照用到的RetrofitXXXUtils来完成。
 */

public class ZhiHuPresenterImpl extends BasePresenter<ZhiHuPresenter.View> implements ZhiHuPresenter.Presenter {
    private RetrofitZhiHuUtils mRetrofitZhiHuUtils;
    private final SPUtils spHomeList;

    @Inject
    public ZhiHuPresenterImpl(RetrofitZhiHuUtils mRetrofitZhiHuUtils) {
        spHomeList = new SPUtils("home_list");
        this.mRetrofitZhiHuUtils = mRetrofitZhiHuUtils;
    }

    private List<HomeListBean> homeList = new ArrayList<>();

    public List<HomeListBean> getHomeList() {
        List<HomeListBean> newHomeList = new ArrayList<>();
        int daily = 0;
        int hot = 0;
        int theme = 0;
        int section = 0;
        SPUtils spUtils = new SPUtils("home_list");
        String homeListString = spUtils.getString("home_list");
        String[] split = homeListString.split("&&");
        for (int i = 0; i < split.length; i++) {
            if ("知乎日报".equals(split[i])) {
                daily = i + 1;
                continue;
            }
            if ("知乎热门".equals(split[i])) {
                hot = i + 1;
                continue;
            }
            if ("知乎主题".equals(split[i])) {
                theme = i + 1;
                continue;
            }
            if ("知乎专栏".equals(split[i])) {
                section = i + 1;
                continue;
            }
        }
        for (int y = 1; y <= 4; y++) {
            if (daily == y) {
                checkAddToNewHomeList("知乎日报", 1, newHomeList);
                continue;
            }
            if (hot == y) {
                checkAddToNewHomeList("知乎热门", 2, newHomeList);
                continue;
            }
            if (theme == y) {
                checkAddToNewHomeList("知乎主题", 3, newHomeList);
                continue;
            }
            if (section == y) {
                checkAddToNewHomeList("知乎专栏", 4, newHomeList);
                continue;
            }
        }
        return newHomeList;
    }


    private void checkAddToNewHomeList(String title, int type, List<HomeListBean> newHomeList) {
        for (int i = 1; i <= homeList.size(); i++) {
            if (!TextUtils.isEmpty(title) &&
                    title.equals(homeList.get(i - 1).getTitle())) {
                newHomeList.add(homeList.get(i - 1));
            }

            if (homeList.get(i - 1).getType() == type) {
                newHomeList.add(homeList.get(i - 1));
            }
        }
    }

    private List<DailyListBean.TopStoriesBean> topStoriesList = new ArrayList<>();

    public List<DailyListBean.TopStoriesBean> getTopStoriesList() {
        return topStoriesList;
    }

    public void fetchData() {
        fetchDailyData();
    }

    public void fetchDailyData() {
        invoke(mRetrofitZhiHuUtils.fetchDailyListInfo(), new Callback<DailyListBean>() {
            @Override
            public void onResponse(DailyListBean data) {
                topStoriesList = data.getTop_stories();
                List<DailyListBean.StoriesBean> storiesBeanList = data.getStories();
                settitle("知乎日报");
                for (int i = 0; i < 3; i++) {
                    HomeListBean homeListBean = settype(1);
                    homeListBean.setDailyList(storiesBeanList.get(i));
                    homeList.add(homeListBean);
                }
                fetchHotList();
            }
        });
    }

    public void fetchHotList() {
        invoke(mRetrofitZhiHuUtils.fetchHotList(), new Callback<HotListBean>() {
            @Override
            public void onResponse(HotListBean data) {
                List<HotListBean.RecentBean> recent = data.getRecent();
                settitle("知乎热门");
                List<HotListBean.RecentBean> hotList = new ArrayList<>();
                List<HotListBean.RecentBean> hotList2 = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    if (i < 3) {
                        hotList.add(recent.get(i));
                    } else {
                        hotList2.add(recent.get(i));
                    }
                }
                HomeListBean homeListBean = settype(2);
                homeListBean.setHotList(hotList);
                homeList.add(homeListBean);
                HomeListBean homeListBean2 = settype(2);
                homeListBean2.setHotList(hotList2);
                homeList.add(homeListBean2);
                fetchThemeList();
            }
        });
    }


    public void fetchThemeList() {
        invoke(mRetrofitZhiHuUtils.fetchThemeList(), new Callback<ThemeListBean>() {
            @Override
            public void onResponse(ThemeListBean data) {
                List<ThemeListBean.OthersBean> others = data.getOthers();
                settitle("知乎主题");
                List<ThemeListBean.OthersBean> themeList = new ArrayList<>();
                List<ThemeListBean.OthersBean> themeList2 = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    if (i < 2) {
                        themeList.add(others.get(i));
                    } else {
                        themeList2.add(others.get(i));
                    }
                }
                HomeListBean homeListBean = settype(3);
                homeListBean.setThemeList(themeList);
                homeList.add(homeListBean);
                HomeListBean homeListBean2 = settype(3);
                homeListBean2.setThemeList(themeList2);
                homeList.add(homeListBean2);
                fetchSectionList();
            }
        });
    }

    public void fetchSectionList() {
        invoke(mRetrofitZhiHuUtils.fetchSectionList(), new Callback<SectionListBean>() {
            @Override
            public void onResponse(SectionListBean data) {
                List<SectionListBean.DataBean> data1 = data.getData();
                settitle("知乎专栏");
                List<SectionListBean.DataBean> sectionList = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    sectionList.add(data1.get(i));
                }
                HomeListBean homeListBean = settype(4);
                homeListBean.setSectionList(sectionList);
                homeList.add(homeListBean);
                mLifeSubscription.refresh();
            }
        });
    }

    private void settitle(String title) {
        HomeListBean homeListBean = settype(0);
        homeListBean.setTitle(title);
        homeList.add(homeListBean);
    }

    // @NonNull注解能被用来标注给定的参数或者返回值不能为null
    @NonNull
    private HomeListBean settype(int type) {
        HomeListBean homeListBean = new HomeListBean();
        homeListBean.setType(type);
        return homeListBean;
    }
}