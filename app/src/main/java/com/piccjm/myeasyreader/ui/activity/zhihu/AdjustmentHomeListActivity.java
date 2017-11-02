package com.piccjm.myeasyreader.ui.activity.zhihu;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.blankj.utilcode.utils.SPUtils;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.piccjm.myeasyreader.R;
import com.piccjm.myeasyreader.adapter.AdjustmentAdapter;
import com.piccjm.myeasyreader.ui.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mangowangwang on 2017/10/27.
 */

public class AdjustmentHomeListActivity extends BaseActivity {
    private List<String> mlist;

    @BindView(R.id.rv_adjustment)  // 调整布局的recyclerView
    RecyclerView rvAdjustment;

    @BindView(R.id.toolbar_adjustment_home_list)  // 上面文字toolbar
    Toolbar toolbar;
    private SPUtils spUtils; // SharedPreferences 辅助类

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adjustment_home_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 实例化控件
        ButterKnife.bind(this);
        // 利用baseActivity中的setToolBar设置标题
        setToolBar(toolbar, "调整栏目顺序");
        // 设置布局管理者
        rvAdjustment.setLayoutManager(new LinearLayoutManager(this));

        mlist = new ArrayList<>();
        // 读取SharedPreferences的本地数据
        spUtils = new SPUtils("home_list");
        String homeListString = spUtils.getString("home_list");
        final String[] split = homeListString.split("&&");
        for (int i = 0; i < split.length; i++) {
            mlist.add(split[i]);
        }
        AdjustmentAdapter adjustmentAdapter = new AdjustmentAdapter(mlist);
        rvAdjustment.setAdapter(adjustmentAdapter);

        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            // 移动过程中改写sharedPreferences的本地数据的顺序
            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                /**
                 *    String fromName = mlist.get(from);
                 *    mlist.remove(from);
                 *    mlist.add(to,fromName);
                 *    不需要去做集合超做了，adapter都帮我们做好了
                 */
                StringBuilder homeListString = new StringBuilder();
                for (String name : mlist) {
                    homeListString.append(name + "&&");
                }
                spUtils.putString("home_list", homeListString.toString());
                spUtils.putBoolean("home_list_ischange",true);
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        };

        // 实现拖拽功能和监听功能
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemDragAndSwipeCallback(adjustmentAdapter));
        mItemTouchHelper.attachToRecyclerView(rvAdjustment);
        adjustmentAdapter.enableDragItem(mItemTouchHelper);
        adjustmentAdapter.setOnItemDragListener(listener);


    }
}
