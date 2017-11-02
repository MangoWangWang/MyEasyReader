package com.piccjm.myeasyreader.ui.activity.base;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.piccjm.myeasyreader.http.Stateful;
import com.piccjm.myeasyreader.presenter.BasePresenter;
import com.piccjm.myeasyreader.view.LoadingPage;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mangowangwang on 2017/10/27.
 */

// stateful 用于设置状态
public abstract class LoadingBaseActivity<T extends BasePresenter> extends BaseActivity implements Stateful {
    protected LoadingPage mLoadingPage;  // 显示加载页面

    // 注入完成的presenter
    @Inject
    protected T mPresenter;

    private Unbinder bind; // 解绑activity,多用于destory方法中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initInject();
        mPresenter.setLifeSubscription(this);
        // 获取子类中的frameLayoutID
        FrameLayout flBaseContent = (FrameLayout) findViewById(setFrameLayoutId());


        if (mLoadingPage == null) {

            // 创建一个实现了抽象方法的loadingPage的实例(实际是调用下级的抽象方法)
            mLoadingPage = new LoadingPage(this) {
                @Override
                protected void initView() {
                    bind = ButterKnife.bind(LoadingBaseActivity.this, contentView); // contentView继承自baseActivity
                    // 调用子类实现的抽象方法
                    LoadingBaseActivity.this.initView();
                }

                @Override
                protected void loadData() {
                    // 调用子类实现的抽象方法
                    LoadingBaseActivity.this.loadData();
                }

                @Override
                protected int getLayoutId() {
                    // 调用子类实现的抽象方法
                    return LoadingBaseActivity.this.getContentLayoutId();
                }
            };
        }
        // 添加加载页面到具体的activity上
        flBaseContent.addView(mLoadingPage);

        // 子类加载数据
        loadData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind(); // 销毁时进行解绑,防止内存泄漏
        }
    }

    // 设置加载状态
    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }


    /**
     * 1
     * 根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
     * * 如果是静态页面不需要网络请求的在子类的loadData方法中添加以下2行即可
     * mLoadingPage.state = STATE_SUCCESS;
     * mLoadingPage.showPage();
     */
    protected abstract void loadData();

    /**
     * 2
     * 网络请求成功在去加载布局
     *
     * @return
     */
    public abstract int getContentLayoutId();

    /**
     * 3
     * 子类关于View的操作(如setAdapter)都必须在这里面，会因为页面状态不为成功，而binding还没创建就引用而导致空指针。
     * loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
     */
    protected abstract void initView();

    /**
     * dagger2注入
     */
    protected abstract void initInject();


    //子类只需要实现下面两个方法 孙子类需要实现所有。子类还需要实现BaseActivity的getLayoutId();

    /**
     * 获取子类FrameLayout的Id是孙子类的容器
     *
     * @return
     */
    public abstract int setFrameLayoutId();

    /**
     * 用于子类初始化findviewbyid的。
     * 这里初始化的Id是为了子类能公用的。
     */
    protected abstract void initUI();

}
