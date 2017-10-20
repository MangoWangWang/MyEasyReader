package com.piccjm.myeasyreader.app;

import android.app.Application;

import com.piccjm.myeasyreader.di.component.AppComponent;
import com.piccjm.myeasyreader.di.component.DaggerAppComponent;
import com.piccjm.myeasyreader.di.module.AppModule;
import com.piccjm.myeasyreader.di.module.HttpModule;


/**
 * Created by mangowangwang on 2017/10/17.
 */

public class App extends Application {

    private static App instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
