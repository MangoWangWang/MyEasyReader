package com.piccjm.myeasyreader.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by mangowangwang on 2017/10/30.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
