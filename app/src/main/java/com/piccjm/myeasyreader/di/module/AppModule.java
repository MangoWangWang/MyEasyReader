package com.piccjm.myeasyreader.di.module;

import com.piccjm.myeasyreader.app.App;
import com.piccjm.myeasyreader.http.RetrofitUtils;
import com.piccjm.myeasyreader.http.service.ZhiHuService;

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

    @Provides
    @Singleton
    RetrofitUtils provideRetrofitHelper(ZhiHuService zhihuApiService) {
        return new RetrofitUtils(zhihuApiService);
    }
}
