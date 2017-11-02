package com.piccjm.myeasyreader.presenter;


import com.piccjm.myeasyreader.bean.DetailExtraBean;
import com.piccjm.myeasyreader.bean.ZhihuDetailBean;
import com.piccjm.myeasyreader.http.LifeSubscription;

/**
 * Created by quantan.liu on 2017/3/24.
 */

public interface ZhiHuDetailPresenter {

    interface View extends LifeSubscription {
        void showExtraInfo(DetailExtraBean detailExtraBean);
        void showContent(ZhihuDetailBean zhihuDetailBean);
    }

    interface Presenter{
        void fetchDetailInfo(int id);
        void fetchDetailExtraInfo(int id);
    }
}
