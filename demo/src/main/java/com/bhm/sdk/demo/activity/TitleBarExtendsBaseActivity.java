package com.bhm.sdk.demo.activity;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.base.TitleBarActivity;
import com.bhm.sdk.bhmlibrary.utils.TextUtils;
import com.bhm.sdk.bhmlibrary.views.TextImageView;
import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;
import com.bhm.sdk.demo.utils.DatePickerUtils;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.ArrayList;

/**
 * Created by bhm on 2018/5/7.
 */

public class TitleBarExtendsBaseActivity extends TitleBarActivity {

    private TextImageView tv_check;

    @Override
    public void initTitleBar(final TitleBar titleBar) {
//        titleBar.setTitleBarHeight(150f, false);
        titleBar.setTitleBarHeight(50f, true);
        titleBar.setTitleTextColor(R.color.black);
        titleBar.setRightTextColor(R.color.black);
        titleBar.setLeftTextColor(R.color.black);
        titleBar.setTitleText("标题很长的标题很长的标题很长的标题很长的");
        titleBar.setRightText("提交");
        titleBar.setLeftText("后退");
        titleBar.setLeftViewBackgroundResource(R.drawable.img_close_source);
        titleBar.setIsLeftViewShow(true);
        titleBar.setIsRightViewShow(true);
        titleBar.setDividerHeight(1f, false);
        titleBar.setDividerColor(R.color.black);
        titleBar.setBackGroundColor(R.color.white);
        titleBar.setTitleTextSize(18);
        titleBar.setLeftTextSize(16);
        titleBar.setRightTextSize(16);
        titleBar.setLeftTextViewPaddingLeft(2, true);
        titleBar.setRightTextViewPaddingRight(2, true);
        titleBar.setLeftViewMarginLeft(15, true);
        titleBar.setRightViewMarginRight(15, true);
        titleBar.setTitleTextStyle(Typeface.BOLD);
//        titleBar.setLeftOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        titleBar.setLeftViewClickToClose(this);
        titleBar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleBarExtendsBaseActivity.this, titleBar.getRightTextView().
                        getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        titleBar.setTitleBarOnTwoClickListener(new TitleBar.OnTwoClickListener() {
            @Override
            public void onTwoClick(View view) {
                Toast.makeText(TitleBarExtendsBaseActivity.this, "双击标题栏...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View setContentView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_extends_title_bar, rootView, false);
    }

    @Override
    public void initView() {
        super.initView();
        tv_check = (TextImageView) rootView.findViewById(R.id.tv_check);
        tv_check.setDrawableSize(20f, true);
        tv_check.setTextSize(18, Typeface.ITALIC);
        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicker();
            }
        });
    }

    private void showPicker(){
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("抢购");
        arrayList.add("团购");
        arrayList.add("选定");
        arrayList.add("重选");
        OptionsPickerView pickerView = DatePickerUtils.getCommonPicker(this, "", arrayList,
                "确认", "取消", false, new DatePickerUtils.OnWheelPickerItemClickListener() {
                    @Override
                    public void onItemClick(int i, int i1, int i2, int i3,int i4,int i5,View view) {
                        if(i == 2) {
                            tv_check.setText(arrayList.get(i), false);
                        }else if(i == 3) {
                            tv_check.setText("");
                        }else{
                            tv_check.setText(arrayList.get(i));
                        }
                    }
                });

        if(null != pickerView){
            if (!TextUtils.isNullString(tv_check.getText().toString()) && arrayList.contains(tv_check.getText().toString())) {
                pickerView.setSelectOptions(arrayList.indexOf(tv_check.getText().toString()));
            }else {
                pickerView.setSelectOptions(0);
            }
            pickerView.show();
        }
    }
}
