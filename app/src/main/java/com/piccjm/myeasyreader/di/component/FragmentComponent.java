package com.piccjm.myeasyreader.di.component;

import android.app.Activity;

import com.piccjm.myeasyreader.di.module.FragmentModule;
import com.piccjm.myeasyreader.di.scope.FragmentScope;
import com.piccjm.myeasyreader.ui.fragment.home.child.zhihu.ZhiHuCommentFragment;
import com.piccjm.myeasyreader.ui.fragment.home.child.zhihu.ZhiHuHomeFragment;

import dagger.Component;

/**
 * Created by mangowangwang on 2017/10/30.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(ZhiHuHomeFragment zhiHuFragment);

    void inject(ZhiHuCommentFragment zhiHuCommentFragment);
}
