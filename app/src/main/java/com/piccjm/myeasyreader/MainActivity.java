package com.piccjm.myeasyreader;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.piccjm.myeasyreader.app.App;
import com.piccjm.myeasyreader.di.component.ActivityComponent;
import com.piccjm.myeasyreader.di.component.DaggerActivityComponent;
import com.piccjm.myeasyreader.di.module.ActivityModule;
import com.piccjm.myeasyreader.presenter.DailyPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // 左上角的点开图表(打开侧滑)
    @BindView(R.id.fl_title_menu)
    FrameLayout nvMenu;

    // 这一个DrawerLayout
    @BindView(R.id.dl_layout)
    DrawerLayout dlLayout;

    // 上方工具栏
    @BindView(R.id.toolbar)
    Toolbar tbToolbar;

    // 设置点开图标的click事件
    @OnClick(R.id.fl_title_menu)
    public void flTitleMenu() {
        dlLayout.openDrawer(GravityCompat.START);
    }

    @Inject // 告诉调用者这个被注解的d额实例由Dagger负责实例化
    DailyPresenter mDailyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);  // 绑定activity

        setSupportActionBar(tbToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(false);//不显示返回键
        supportActionBar.setDisplayShowTitleEnabled(false);//去除默认标题
        getActivityComponent().inject(this); // 创造activityComponent

        // 实例化过程
        // 1.先调用 public DailyPresenter(RetrofitUtils mRetrofitUtils)
        // 2.根据appComponent提供的 RetrofitUtils retrofitUtils()方法,
        // 调用 AppModule中  RetrofitUtils provideRetrofitHelper(ZhiHuService zhihuApiService)
        // 3.根据参数类型 ZhiHuService ,调用HttpModule中的  ZhiHuService provideZhihuService(Retrofit retrofit)
        // 4. 根据参数Retrofit retrofit 调用 Retrofit provideZhiHuRetrofit(Retrofit.Builder builder, OkHttpClient client) 和 createRetrofit()
        mDailyPresenter.getDailyData(); // 从网络上获取数据

    }


    // 添加搜索菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    // 添加搜索点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 按下回退键的操作
    @Override
    public void onBackPressed() {
        if (dlLayout.isDrawerOpen(GravityCompat.START)) {
            dlLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // 进行注入器的绑定
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent()) // 单例实现appComponent
                .activityModule(getActivityModule()) // 实现activityModule
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
