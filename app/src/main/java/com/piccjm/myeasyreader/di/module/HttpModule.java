package com.piccjm.myeasyreader.di.module;

import com.piccjm.myeasyreader.http.service.ZhiHuService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mangowangwang on 2017/10/17.
 */
@Module //一个生产实例的工厂，他掌握各个需要注入的类的实例化方法
public class HttpModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder  // 创建Retrofit对象
                .baseUrl(url) // 设置网络请求Url(Url必须以"/"结束,否则会报错)
                .client(client) //
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 通过RxJavaCallAdapterFactory为Retrofit添加RxJava支持：
                .addConverterFactory(GsonConverterFactory.create())  // 设置json解析器
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideZhiHuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhiHuService.HOST);
    }

    @Singleton
    @Provides
    ZhiHuService provideZhihuService(Retrofit retrofit) {
        return retrofit.create(ZhiHuService.class);
    }


}
