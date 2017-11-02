package com.piccjm.myeasyreader.http.service;

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

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mangowangwang on 2017/10/17.
 */

public interface ZhiHuService {
    String HOST = "http://news-at.zhihu.com/api/4/";
    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 最新日报
     */
    @GET("news/latest")
    Observable<DailyListBean> getDailyList();
    /**
     * 主题日报
     */
    @GET("themes")
    Observable<ThemeListBean> getThemeList();

    /**
     * 主题日报详情
     */
    @GET("theme/{id}")
    Observable<ThemeChildListBean> getThemeChildList(@Path("id") int id);

    /**
     * 专栏日报
     */
    @GET("sections")
    Observable<SectionListBean> getSectionList();

    /**
     * 专栏日报详情
     */
    @GET("section/{id}")
    Observable<SectionChildListBean> getSectionChildList(@Path("id") int id);

    /**
     * 热门日报
     */
    @GET("news/hot")
    Observable<HotListBean> getHotList();

    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息
     */
    @GET("story-extra/{id}")
    Observable<DetailExtraBean> getDetailExtraInfo(@Path("id") int id);

    /**
     * 日报的长评论
     */
    @GET("story/{id}/long-comments")
    Observable<CommentBean> getLongCommentInfo(@Path("id") int id);

    /**
     * 日报的短评论
     */
    @GET("story/{id}/short-comments")
    Observable<CommentBean> getShortCommentInfo(@Path("id") int id);
}
