package com.piccjm.myeasyreader.presenter;


import com.piccjm.myeasyreader.bean.CommentBean;
import com.piccjm.myeasyreader.http.LifeSubscription;

import java.util.List;


/**
 * Created by quantan.liu on 2017/3/25.
 */

public interface ZhihuCommentPresenter {

    interface View extends LifeSubscription {
        void showRecyclerView(List<CommentBean.CommentsBean> list);
    }

    interface Presenter{
        void fetchLongCommentInfo(int id);
        void fetchShortCommentInfo(int id);
    }
}
