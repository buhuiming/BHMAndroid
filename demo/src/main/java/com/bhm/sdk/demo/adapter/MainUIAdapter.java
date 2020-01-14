package com.bhm.sdk.demo.adapter;

import androidx.annotation.Nullable;
import android.widget.TextView;

import com.bhm.sdk.demo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by bhm on 2018/5/7.
 */

public class MainUIAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public MainUIAdapter(@Nullable List<String> data) {
        super(R.layout.layout_main_ui_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_main_ui_item = helper.getView(R.id.tv_main_ui_item);
        tv_main_ui_item.setText(item);
    }
}
