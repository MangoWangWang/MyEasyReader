package com.piccjm.myeasyreader.ui.activity.base;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.piccjm.myeasyreader.R;
import com.piccjm.myeasyreader.http.Stateful;
import com.piccjm.myeasyreader.presenter.BasePresenter;

/**
 * Created by mangowangwang on 2017/10/27.
 */

public abstract class ZhihuDetailBaseActivity <T extends BasePresenter> extends LoadingBaseActivity<T> implements Stateful {

    // 获取布局id
    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_base;
    }

    // 设置framelayoutId
    @Override
    public int setFrameLayoutId() {
        return R.id.fl_base_content;
    }

    protected ImageView detailBarImage;  // 上方很大的背景图片
    protected Toolbar toolbarZhihuDetail; // 上方toolbar
    protected TextView detailBarCopyright; // 上方文字信息

    @Override
    protected void initUI() {
        detailBarImage = (ImageView) findViewById(R.id.detail_bar_image);
        toolbarZhihuDetail = (Toolbar) findViewById(R.id.toolbar_zhihu_detail);
        detailBarCopyright = (TextView) findViewById(R.id.detail_bar_copyright);
        setToolBar(toolbarZhihuDetail, "");
    }
}
