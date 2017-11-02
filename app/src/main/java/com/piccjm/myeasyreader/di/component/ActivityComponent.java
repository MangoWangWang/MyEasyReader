package com.piccjm.myeasyreader.di.component;

import android.app.Activity;

import com.piccjm.myeasyreader.ui.activity.MainActivity;
import com.piccjm.myeasyreader.di.module.ActivityModule;
import com.piccjm.myeasyreader.di.scope.ActivityScope;
import com.piccjm.myeasyreader.ui.activity.zhihu.ZhiHuDetailActivity;
import com.piccjm.myeasyreader.ui.activity.zhihu.ZhihuThemeActivity;

import dagger.Component;

/**
 * Created by mangowangwang on 2017/10/17.
 */
@ActivityScope
//用来注解一个接口，在编译的时候会生成 Dagger+文件名 的新Java文件。Component可以理解为注射器，它是连接被注入的类与需要被注入的类之间的桥梁。
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);
    void inject(ZhiHuDetailActivity zhiHuDetailActivity);
    void inject(ZhihuThemeActivity zhihuThemeActivity);
}
