package com.piccjm.myeasyreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.blankj.utilcode.utils.SPUtils;
import com.piccjm.myeasyreader.R;
import com.piccjm.myeasyreader.adapter.HomeFragmentPageAdapter;
import com.piccjm.myeasyreader.app.App;
import com.piccjm.myeasyreader.di.component.ActivityComponent;
import com.piccjm.myeasyreader.di.component.DaggerActivityComponent;
import com.piccjm.myeasyreader.di.module.ActivityModule;
import com.piccjm.myeasyreader.ui.activity.base.BaseActivity;
import com.piccjm.myeasyreader.ui.fragment.HomeFragment;
import com.piccjm.myeasyreader.ui.fragment.gank.GankFragment;
import com.piccjm.myeasyreader.ui.fragment.wechat.WeChatFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    /*
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

  */

    @BindView(R.id.fl_title_menu)
    FrameLayout nvMenu;

    @BindView(R.id.dl_layout)
    DrawerLayout dlLayout;

    @BindView(R.id.toolbar)
    Toolbar tbToolbar;

    @BindView(R.id.rg_home_viewpager_contorl)
    RadioGroup rgHomeViewpagerContorl;

    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @OnClick(R.id.fl_title_menu)
    public void flTitleMenu() {
        dlLayout.openDrawer(GravityCompat.START);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolBar(tbToolbar,"");
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(false);//不显示返回键
        supportActionBar.setDisplayShowTitleEnabled(false);//去除默认标题
        getActivityComponent().inject(this);
        initView();
        //初始化首页栏目顺序
        SPUtils spUtils = new SPUtils("home_list");
        if (!spUtils.getBoolean("home_list_boolean")) {
            spUtils.putString("home_list", "知乎日报&&知乎热门&&知乎主题&&知乎专栏&&");
            spUtils.putBoolean("home_list_boolean",true);
        }


    }

    private void initView() {
        rgHomeViewpagerContorl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                switch (i) {
                    case R.id.rb_home_pager:
                        vpContent.setCurrentItem(0);// 设置当前页面
//                        vpContent.setCurrentItem(0,false);// false去掉viewpager切换页面的动画
                        break;
                    case R.id.rb_music_pager:
                        vpContent.setCurrentItem(1);
                        break;
                    case R.id.rb_friend_pager:
                        vpContent.setCurrentItem(2);
                        break;
                }
            }
        });
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new GankFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new WeChatFragment());
        vpContent.setAdapter(new HomeFragmentPageAdapter(getSupportFragmentManager(), mFragmentList));
        vpContent.setCurrentItem(1);
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgHomeViewpagerContorl.check(R.id.rb_home_pager);
                        break;
                    case 1:
                        rgHomeViewpagerContorl.check(R.id.rb_music_pager);
                        break;
                    case 2:
                        rgHomeViewpagerContorl.check(R.id.rb_friend_pager);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

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

    @Override
    public void onBackPressed() {
        if (dlLayout.isDrawerOpen(GravityCompat.START)) {
            dlLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            onUserInteraction();
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


}
