package com.piccjm.myeasyreader.presenter;

import com.piccjm.myeasyreader.bean.DailyListBean;
import com.piccjm.myeasyreader.http.RetrofitUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mangowangwang on 2017/10/17.
 */

public class DailyPresenter {
    private RetrofitUtils mRetrofitUtils;

    @Inject // 通过注解将这个类的实例化方法告诉 Dagger；
    public DailyPresenter(RetrofitUtils mRetrofitUtils) {
        this.mRetrofitUtils = mRetrofitUtils;
    }

    public void getDailyData(){
        mRetrofitUtils.fetchDailyListInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("aaaaThrowable:"+e);
                    }

                    @Override
                    public void onNext(DailyListBean dailyListBean) {

                        System.out.println("aaaa:"+dailyListBean);
                    }
                });
    }
}
