package com.piccjm.myeasyreader.ui.fragment.home.child.zhihu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.piccjm.myeasyreader.R;
import com.piccjm.myeasyreader.adapter.ZhiHuCommentAdapter;
import com.piccjm.myeasyreader.bean.CommentBean;
import com.piccjm.myeasyreader.presenter.ZhihuCommentPresenter;
import com.piccjm.myeasyreader.presenter.impl.ZhihuCommentPresenterImpl;
import com.piccjm.myeasyreader.ui.activity.zhihu.ZhiHuCommentActivity;
import com.piccjm.myeasyreader.ui.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by mangowangwang on 2017/10/30.
 */

public class ZhiHuCommentFragment extends BaseFragment<ZhihuCommentPresenterImpl> implements ZhihuCommentPresenter.View {

    @BindView(R.id.rv_zhihu_comment)
    RecyclerView rvZhihuComment;

    private static boolean isShortComment = true;
    @Override
    protected void initView() {
        rvZhihuComment.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void loadData() {
        ZhiHuCommentActivity mZhiHuCommentActivity = (ZhiHuCommentActivity) getActivity();
        int id = mZhiHuCommentActivity.getId();
        if (isShortComment) {//懒加载在可见的时候加载，会让非静态变量最终都是同一个值所以只能用静态变量。
            mPresenter.fetchShortCommentInfo(id);
            isShortComment = false;
        } else {
            mPresenter.fetchLongCommentInfo(id);
            isShortComment = true;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_comment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void showRecyclerView(List<CommentBean.CommentsBean> list) {
        ZhiHuCommentAdapter zhiHuCommentAdapter = new ZhiHuCommentAdapter(list);
        rvZhihuComment.setAdapter(zhiHuCommentAdapter);
    }
}

