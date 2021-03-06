package com.piccjm.myeasyreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.piccjm.myeasyreader.app.App;
import com.piccjm.myeasyreader.di.component.DaggerFragmentComponent;
import com.piccjm.myeasyreader.di.component.FragmentComponent;
import com.piccjm.myeasyreader.di.module.FragmentModule;
import com.piccjm.myeasyreader.http.LifeSubscription;
import com.piccjm.myeasyreader.http.Stateful;
import com.piccjm.myeasyreader.presenter.BasePresenter;
import com.piccjm.myeasyreader.view.LoadingPage;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mangowangwang on 2017/10/30.
 */

// 泛型<P extends BasePresenter> 向下通配符
public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements LifeSubscription, Stateful {

    @Inject
    protected P mPresenter;  // 参数类型化(交换机)

    public LoadingPage mLoadingPage;  // 加载页面

    private boolean mIsVisible = false;     // fragment是否显示了

    private boolean isPrepared = false;  // 是否准备好

    private boolean isFirst = true; // 只加载一次界面


    protected View contentView;

    private Unbinder bind; // 解绑工具

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 采用匿名类的方式 强行实例化mLoadingPage,因为LoadingPage是抽象类型,必须重写抽象方法
        if (mLoadingPage == null) {
            mLoadingPage = new LoadingPage(getContext()) {
                @Override
                protected void initView() {
                    if (isFirst) {
                        // this.contentView 返回的的加载状态视图(成功,失败) 根据状态
                        // BaseFragment.this.contentView 原BaseFragment中的contentView
                        BaseFragment.this.contentView = this.contentView;

                        // 进行界面的绑定
                        bind = ButterKnife.bind(BaseFragment.this, contentView);

                        // 进行界面初始化
                        BaseFragment.this.initView();

                        isFirst = false;
                    }
                }

                @Override
                protected void loadData() {
                    BaseFragment.this.loadBaseData();
                }

                @Override
                protected int getLayoutId() {
                    return BaseFragment.this.getLayoutId();
                }
            };
        }
        isPrepared = true;
        loadBaseData();
        return mLoadingPage;
    }


    /**
     * 在这里实现Fragment数据的缓加载.
     * 实现显示时加载数据,不显示时不缓存数据
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //fragment可见
            mIsVisible = true;
            onVisible();
        } else {
            //fragment不可见
            mIsVisible = false;
            onInvisible();
        }
    }

    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }

    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void onVisible() {
        if (isFirst) {
            initInject();
            mPresenter.setLifeSubscription(this);
        }
        loadBaseData();//根据获取的数据来调用showView()切换界面
    }


    public void loadBaseData() {
        if (!mIsVisible || !isPrepared || !isFirst) {
            return;
        }
        loadData();
    }

    /**
     * 1
     * 根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
     */
    protected abstract void loadData();

    /**
     * 2
     * 网络请求成功在去加载布局
     *
     * @return
     */
    protected abstract int getLayoutId();

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

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }


    private CompositeSubscription mCompositeSubscription;

    //用于添加rx的监听的在onDestroy中记得关闭不然会内存泄漏。
    @Override
    public void bindSubscription(Subscription subscription) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bind.unbind();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}