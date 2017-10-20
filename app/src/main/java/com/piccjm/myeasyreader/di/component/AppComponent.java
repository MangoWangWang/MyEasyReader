package com.piccjm.myeasyreader.di.component;

import com.piccjm.myeasyreader.app.App;
import com.piccjm.myeasyreader.di.module.AppModule;
import com.piccjm.myeasyreader.di.module.HttpModule;
import com.piccjm.myeasyreader.http.RetrofitUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mangowangwang on 2017/10/17.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

    RetrofitUtils retrofitUtils();  //提供http的帮助类

}
