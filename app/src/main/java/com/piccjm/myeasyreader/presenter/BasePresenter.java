package com.piccjm.myeasyreader.presenter;

import com.piccjm.myeasyreader.app.AppConstants;
import com.piccjm.myeasyreader.http.LifeSubscription;
import com.piccjm.myeasyreader.http.Stateful;
import com.piccjm.myeasyreader.http.utils.Callback;
import com.piccjm.myeasyreader.http.utils.HttpUtils;


import java.util.List;

import rx.Observable;

/**
 * Created by mangowangwang on 2017/10/27.
 */

public class BasePresenter<T extends LifeSubscription> {

    protected T mLifeSubscription;

    public void setLifeSubscription(LifeSubscription mLifeSubscription)
    {
        this.mLifeSubscription = (T) mLifeSubscription;
    }

    protected <T> void invoke(Observable<T> observable, Callback<T> callback) {
        HttpUtils.invoke(mLifeSubscription, observable, callback);
    }

    /**
     * 给子类检查返回集合是否为空
     * 这样子做虽然耦合度高，但是接口都不是统一定的，我们没有什么更好的办法
     * @param list
     */
    public void checkState(List list) {
        if (list.size() == 0) {
            if (mLifeSubscription instanceof Stateful)
                ((Stateful) mLifeSubscription).setState(AppConstants.STATE_EMPTY);
            return;
        }
    }
}
