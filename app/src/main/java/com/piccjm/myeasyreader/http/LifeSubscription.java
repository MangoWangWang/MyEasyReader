package com.piccjm.myeasyreader.http;

import rx.Subscription;

/**
 * Created by mangowangwang on 2017/10/23.
 *  用于绑定一个订阅者
 */

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}
