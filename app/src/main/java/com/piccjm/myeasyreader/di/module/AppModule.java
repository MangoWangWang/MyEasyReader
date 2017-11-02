package com.piccjm.myeasyreader.di.module;

import com.piccjm.myeasyreader.app.App;
import com.piccjm.myeasyreader.http.service.GankIoService;
import com.piccjm.myeasyreader.http.service.ZhiHuService;
import com.piccjm.myeasyreader.http.utils.RetrofitGankIoUtils;
import com.piccjm.myeasyreader.http.utils.RetrofitZhiHuUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mangowangwang on 2017/10/17.
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }
//
    @Provides
    @Singleton
    RetrofitZhiHuUtils provideRetrofitZhiHuUtils(ZhiHuService zhihuApiService) {
        return new RetrofitZhiHuUtils(zhihuApiService);
    }

    @Provides
    @Singleton
    RetrofitGankIoUtils provideRetrofitGankIoUtils(GankIoService gankIoService) {
        return new RetrofitGankIoUtils(gankIoService);
    }
}
