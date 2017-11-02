package com.piccjm.myeasyreader.app;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.piccjm.myeasyreader.di.component.AppComponent;
import com.piccjm.myeasyreader.di.component.DaggerAppComponent;
import com.piccjm.myeasyreader.di.module.AppModule;
import com.piccjm.myeasyreader.di.module.HttpModule;


/**
 * Created by mangowangwang on 2017/10/17.
 * 用于实现整个程序的单例模式
 */

public class App extends Application {

    //现在只完成了dagger2和Retrofit配合完成网络请求
    private static App instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);//一个utils库的初始化 https://github.com/Blankj/AndroidUtilCode/blob/master/README-CN.md
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }


}
