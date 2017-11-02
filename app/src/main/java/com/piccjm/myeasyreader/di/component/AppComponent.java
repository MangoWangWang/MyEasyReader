package com.piccjm.myeasyreader.di.component;

import com.piccjm.myeasyreader.app.App;
import com.piccjm.myeasyreader.di.module.AppModule;
import com.piccjm.myeasyreader.di.module.HttpModule;
import com.piccjm.myeasyreader.http.utils.RetrofitGankIoUtils;
import com.piccjm.myeasyreader.http.utils.RetrofitZhiHuUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mangowangwang on 2017/10/17.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context
    /**
     * 提供http的帮助类
     * 更换链接的请求，需要添加如AppModule的provideRetrofitZhiHuUtils()方法 命名规则provideRetrofitXXXUtils()
     * HttpModule的provideZhiHuRetrofit()和provideZhihuService() 命名规则provideXXXService
     * 还有以下方法 命名规则retrofitXXXUtils  命名规则怎么开心怎么来。
     * @return
     */
    RetrofitZhiHuUtils retrofitZhiHuUtils();  //提供http的帮助类
    RetrofitGankIoUtils mRetrofitGankIoUtils();

}
