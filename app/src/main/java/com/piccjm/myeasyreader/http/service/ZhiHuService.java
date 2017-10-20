package com.piccjm.myeasyreader.http.service;

import com.piccjm.myeasyreader.bean.DailyListBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by mangowangwang on 2017/10/17.
 */

public interface ZhiHuService {
    String HOST = "http://news-at.zhihu.com/api/3/";
    /**
     * 最新日报
     */
    @GET("news/latest")
    Observable<DailyListBean> getDailyList();
}
