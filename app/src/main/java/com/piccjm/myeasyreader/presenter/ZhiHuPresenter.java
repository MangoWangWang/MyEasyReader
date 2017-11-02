package com.piccjm.myeasyreader.presenter;

import com.piccjm.myeasyreader.http.LifeSubscription;

/**
 * Created by mangowangwang on 2017/10/30.
 */

public interface ZhiHuPresenter {
    interface View extends LifeSubscription {
        void refresh();
    }

    interface Presenter{
        void fetchDailyData();
        void fetchThemeList();
        void fetchSectionList();
        void fetchHotList();
    }
}
