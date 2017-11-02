package com.piccjm.myeasyreader.http.service;


import com.piccjm.myeasyreader.bean.gankio.GankIoDayBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by quantan.liu on 2017/3/22.
 */

public interface GankIoService {

     String API_GANKIO = "http://gank.io/api/";
    /**
     * 每日数据： http://gank.io/api/day/年/月/日
     * eg:http://gank.io/api/day/2015/08/06
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankIoDayBean> getGankIoDay(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
