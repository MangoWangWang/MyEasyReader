package com.piccjm.myeasyreader.http;

import rx.Subscription;

/**
 * Created by mangowangwang on 2017/10/23.
 */

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}
