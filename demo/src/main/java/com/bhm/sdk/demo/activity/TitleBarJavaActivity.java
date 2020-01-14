package com.bhm.sdk.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.bhm.sdk.bhmlibrary.utils.DisplayUtil;
import com.bhm.sdk.bhmlibrary.utils.TitleBarBuilder;
import com.bhm.sdk.bhmlibrary.views.ShadowView;
import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bhm on 2018/5/7.
 */

public class TitleBarJavaActivity extends AppCompatActivity{

    private TitleBar titleBar;
    private View contentView;
    @BindView(R.id.btn_text)
    protected Button btn_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView(){
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_java_title_bar, null, false);
        ButterKnife.bind(this, contentView);
        titleBar = TitleBarBuilder.newBuilder(this)
                .createTitleBar()
                .setContentView(contentView)
                .build()
                .setTitleBarHeight(50f, true)
                .setTitleTextColor(R.color.white)
                .setRightTextColor(R.color.white)
                .setLeftTextColor(R.color.white)
                .setTitleText("代码生成")
                .setRightText("提交")
                .setLeftText("后退")
                .setLeftViewBackgroundResource(R.drawable.arrow_a)
                .setIsLeftViewShow(true)
                .setIsRightViewShow(true)
                .setDividerHeight(1f, false)
                .setDividerColor(R.color.black)
                .setBackGroundColor(R.color.colorPrimary);
        btn_text.setText("见识见识");
        btn_text.setVisibility(View.VISIBLE);
        btn_text.setTextColor(ContextCompat.getColor(this, R.color.white));

        int[] mColor = new int[]{Color.parseColor("#FF6176EC"), Color.parseColor("#FF46309F"), Color.parseColor("#FF593FB5")};
        ShadowView.newBuilder()
                .setTargetView(btn_text)
                .setColor(mColor[0])//View颜色
                .setShadowColor(Color.parseColor("#FFA6D9A8"))//阴影颜色
                .setGradientColorArray(mColor)//如果View是渐变色，则设置color数组
                .setRadius(DisplayUtil.dp2px(this, 25))//圆角
//                .setShadowRadius(DisplayUtil.dp2px(this, 4))//阴影圆角
//                .setOffsetX(DisplayUtil.dp2px(this, 2))//阴影横向偏移
//                .setOffsetY(DisplayUtil.dp2px(this, 2))//阴影纵向偏移
                .build();
    }

    private void initListener(){
        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
