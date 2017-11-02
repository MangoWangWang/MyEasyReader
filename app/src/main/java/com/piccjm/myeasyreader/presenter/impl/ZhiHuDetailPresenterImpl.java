package com.piccjm.myeasyreader.presenter.impl;


import com.piccjm.myeasyreader.bean.DetailExtraBean;
import com.piccjm.myeasyreader.bean.ZhihuDetailBean;
import com.piccjm.myeasyreader.http.utils.Callback;
import com.piccjm.myeasyreader.http.utils.RetrofitZhiHuUtils;
import com.piccjm.myeasyreader.presenter.BasePresenter;
import com.piccjm.myeasyreader.presenter.ZhiHuDetailPresenter;

import javax.inject.Inject;

/**
 * Created by quantan.liu on 2017/3/24.
 */

public class ZhiHuDetailPresenterImpl extends BasePresenter<ZhiHuDetailPresenter.View> implements ZhiHuDetailPresenter.Presenter {
    private RetrofitZhiHuUtils mRetrofitZhiHuUtils;

    @Inject
    public ZhiHuDetailPresenterImpl(RetrofitZhiHuUtils mRetrofitZhiHuUtils) {
        this.mRetrofitZhiHuUtils = mRetrofitZhiHuUtils;
    }

    public void fetchDetailInfo(int id){
        invoke(mRetrofitZhiHuUtils.fetchDetailInfo(id),new Callback<ZhihuDetailBean>(){
            @Override
            public void onResponse(ZhihuDetailBean data) {
                mLifeSubscription.showContent(data);
            }
        });
    }

    @Override
    public void fetchDetailExtraInfo(int id) {
        invoke(mRetrofitZhiHuUtils.fetchDetailExtraInfo(id),new Callback<DetailExtraBean>(){
            @Override
            public void onResponse(DetailExtraBean data) {
                mLifeSubscription.showExtraInfo(data);
            }
        });
    }

}
