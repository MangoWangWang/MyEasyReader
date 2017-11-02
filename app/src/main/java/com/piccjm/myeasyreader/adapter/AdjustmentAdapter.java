package com.piccjm.myeasyreader.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.piccjm.myeasyreader.R;

import java.util.List;

/**
 * Created by mangowangwang on 2017/10/27.
 */

public class AdjustmentAdapter extends BaseItemDraggableAdapter<String,BaseViewHolder> {

    public AdjustmentAdapter(List<String> data) {
        super(R.layout.item_adjustment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adjustment_name, item);
    }

}
