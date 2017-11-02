package com.piccjm.myeasyreader.http.utils;


import com.piccjm.myeasyreader.bean.gankio.GankIoDayBean;
import com.piccjm.myeasyreader.http.service.GankIoService;

import rx.Observable;

/**
 * Created by quantan.liu on 2017/3/22.
 */

public class RetrofitGankIoUtils extends HttpUtils {

    private GankIoService mGankIoService;

    public RetrofitGankIoUtils(GankIoService mGankIoService) {
        this.mGankIoService = mGankIoService;
    }

    public Observable<GankIoDayBean> fetchGankIoDay(String year, String month, String day) {
        return mGankIoService.getGankIoDay(year, month, day);
    }


}