package com.bhm.sdk.bhmlibrary.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bhm.sdk.bhmlibrary.views.TitleBar;

/**
 * Created by bhm on 2018/5/23.
 */

public class TitleBarBuilder {

    public static Builder newBuilder(Activity activity){
        return new Builder(activity);
    }

    public static class Builder{

        private Activity activity;
        private TitleBar titleBar;

        public Builder(Activity activity){
            this.activity = activity;
        }

        public Builder createTitleBar(){
            if(null != activity) {
                titleBar = new TitleBar(activity);
            }
            return this;
        }

        public Builder setContentView(View contentView){
            if(null != activity && null != titleBar){
                LinearLayout linearLayout = new LinearLayout(activity);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                contentView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(titleBar);
                linearLayout.addView(contentView);
                activity.setContentView(linearLayout);
            }
            return this;
        }

        public TitleBar build(){
            return titleBar;
        }
    }
}
