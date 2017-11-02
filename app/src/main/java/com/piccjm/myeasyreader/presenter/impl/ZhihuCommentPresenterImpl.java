package com.piccjm.myeasyreader.presenter.impl;



import com.piccjm.myeasyreader.bean.CommentBean;
import com.piccjm.myeasyreader.http.utils.Callback;
import com.piccjm.myeasyreader.http.utils.RetrofitZhiHuUtils;
import com.piccjm.myeasyreader.presenter.BasePresenter;
import com.piccjm.myeasyreader.presenter.ZhihuCommentPresenter;

import javax.inject.Inject;

/**
 * Created by quantan.liu on 2017/3/25.
 */

public class ZhihuCommentPresenterImpl extends BasePresenter<ZhihuCommentPresenter.View> implements ZhihuCommentPresenter.Presenter {
    private RetrofitZhiHuUtils mRetrofitZhiHuUtils;

    @Inject
    public ZhihuCommentPresenterImpl(RetrofitZhiHuUtils mRetrofitZhiHuUtils) {
        this.mRetrofitZhiHuUtils = mRetrofitZhiHuUtils;
    }


    public void fetchLongCommentInfo(int id){
        invoke(mRetrofitZhiHuUtils.fetchLongCommentInfo(id),new Callback<CommentBean>(){
            @Override
            public void onResponse(CommentBean data) {
                checkState(data.getComments());
                mLifeSubscription.showRecyclerView(data.getComments());
            }
        });
    }
    public void fetchShortCommentInfo(int id){
        invoke(mRetrofitZhiHuUtils.fetchShortCommentInfo(id),new Callback<CommentBean>(){
            @Override
            public void onResponse(CommentBean data) {
                checkState(data.getComments());
                mLifeSubscription.showRecyclerView(data.getComments());
            }
        });
    }

}
