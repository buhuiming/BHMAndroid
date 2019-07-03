package com.bhm.sdk.demo.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bhm.sdk.demo.R;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.List;

public class DatePickerUtils {

    public static OptionsPickerView getCommonPicker(Context context, String title, List<String> list, String submitText
            , String cancelText, boolean outSideCancelable, final OnWheelPickerItemClickListener listener){
        OptionsPickerView picker = getDefaultOptionsPickerView(context, title, submitText, cancelText, outSideCancelable, listener);
        if(null == list || list.size() < 1){
            return null;
        }
        picker.setSelectOptions(0);
        picker.setPicker(list);//添加数据源
        return picker;
    }

    public static OptionsPickerView getDefaultOptionsPickerView(Context context, String title, String submitText
            , String cancelText, boolean outSideCancelable, final OnWheelPickerItemClickListener listener){
        return new OptionsPickerBuilder(context,
                new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int i, int i1, int i2, View view) {
                        if (null != listener){
                            listener.onItemClick(i, i1, i2, 0, 0, 0, view);
                        }
                    }
                })
                .setSubmitText(submitText)//确定按钮文字
                .setCancelText(cancelText)//取消按钮文字
                .setTitleText(title)
                .setLineSpacingMultiplier(1.6f)
                .setSubCalSize(17)//确定和取消文字大小
                .setSubCalSize(17)
                .setTitleSize(17)//标题文字大小
                .setContentTextSize(18)//滚轮文字大小
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_blue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_blue))//取消按钮文字颜色
                .setTitleColor(ContextCompat.getColor(context, R.color.color_blue))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(context, R.color.white))//标题背景颜色
                .setBgColor(ContextCompat.getColor(context, R.color.white))//滚轮背景颜色 Night mode
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setOutSideCancelable(outSideCancelable)//点击外部dismiss default true
                .isDialog(false)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();
    }

    public interface OnWheelPickerItemClickListener {

        void onItemClick(int i, int i1, int i2, int i3, int i4, int i5, View view);
    }
}
