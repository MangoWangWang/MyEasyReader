package com.piccjm.myeasyreader.presenter;


import com.piccjm.myeasyreader.bean.SectionChildListBean;
import com.piccjm.myeasyreader.bean.ThemeChildListBean;
import com.piccjm.myeasyreader.http.LifeSubscription;

/**
 * Created by quantan.liu on 2017/3/27.
 */

public interface ZhihuThemeDetailPresenter {

    interface View extends LifeSubscription {
        void refreshData(ThemeChildListBean data);
        void refreshSectionData(SectionChildListBean data);
    }

    interface Presenter{
        void fetchThemeChildList(int id);
        void fetchSectionChildList(int id);
    }
}
