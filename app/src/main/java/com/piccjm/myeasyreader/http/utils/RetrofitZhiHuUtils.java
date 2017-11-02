package com.piccjm.myeasyreader.http.utils;

import com.piccjm.myeasyreader.bean.CommentBean;
import com.piccjm.myeasyreader.bean.DailyListBean;
import com.piccjm.myeasyreader.bean.DetailExtraBean;
import com.piccjm.myeasyreader.bean.HotListBean;
import com.piccjm.myeasyreader.bean.SectionChildListBean;
import com.piccjm.myeasyreader.bean.SectionListBean;
import com.piccjm.myeasyreader.bean.ThemeChildListBean;
import com.piccjm.myeasyreader.bean.ThemeListBean;
import com.piccjm.myeasyreader.bean.WelcomeBean;
import com.piccjm.myeasyreader.bean.ZhihuDetailBean;
import com.piccjm.myeasyreader.http.service.ZhiHuService;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by mangowangwang on 2017/10/30.
 */

public class RetrofitZhiHuUtils extends HttpUtils {

    private ZhiHuService mZhiHuService;

    public RetrofitZhiHuUtils(ZhiHuService mZhiHuService) {
        this.mZhiHuService = mZhiHuService;
    }

    public Observable<DailyListBean> fetchDailyListInfo() {
        return mZhiHuService.getDailyList();
    }

    public Observable<ThemeListBean> fetchThemeList() {
        return mZhiHuService.getThemeList();
    }

    public Observable<SectionListBean> fetchSectionList() {
        return mZhiHuService.getSectionList();
    }

    public Observable<HotListBean> fetchHotList() {
        return mZhiHuService.getHotList();
    }

    public Observable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhiHuService.getWelcomeInfo(res);
    }

    public Observable<ThemeChildListBean> fetchThemeChildList(int id) {
        return mZhiHuService.getThemeChildList(id);
    }

    public Observable<SectionChildListBean> fetchSectionChildList(int id) {
        return mZhiHuService.getSectionChildList(id);
    }

    public Observable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mZhiHuService.getDetailInfo(id);
    }

    public Observable<DetailExtraBean> fetchDetailExtraInfo(int id) {
        return mZhiHuService.getDetailExtraInfo(id);
    }

    public Observable<CommentBean> fetchLongCommentInfo(int id) {
        return mZhiHuService.getLongCommentInfo(id);
    }

    public Observable<CommentBean> fetchShortCommentInfo(int id) {
        return mZhiHuService.getShortCommentInfo(id);
    }
}
