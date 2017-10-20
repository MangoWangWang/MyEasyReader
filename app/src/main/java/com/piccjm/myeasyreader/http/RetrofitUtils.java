package com.piccjm.myeasyreader.http;

import com.piccjm.myeasyreader.bean.DailyListBean;
import com.piccjm.myeasyreader.http.service.ZhiHuService;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by mangowangwang on 2017/10/17.
 */

public class RetrofitUtils {
    private ZhiHuService mZhiHuService;

    public RetrofitUtils(ZhiHuService mZhiHuService){
        this.mZhiHuService = mZhiHuService;
    }

    public Observable<DailyListBean> fetchDailyListInfo() {
        return mZhiHuService.getDailyList();
    }
}
