package com.bhm.sdk.bhmlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by bhm on 2018/8/28.
 */

public class DeviceUtils {

    /**
     * 获得屏幕高度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        setBackgroundAlpha(activity, bgAlpha, "#000000");
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param activity
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     * @param noTransparencyColor 没有透明度的十六进制颜色值  如#00FF00
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha, String noTransparencyColor) {
        try {
            ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
            if (bgAlpha == 1) {
                View decorChild = decor.getChildAt(decor.getChildCount() - 1);
                decor.removeView(decorChild);
            } else {
                LinearLayout traView = new LinearLayout(activity);
                traView.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth
                        (activity), getScreenHeight(activity)));
                traView.setBackgroundColor(Color.parseColor(ColorUtils.colorChange(bgAlpha, noTransparencyColor)));
                decor.addView(traView);
            }
        }catch (Exception e){
            WindowManager.LayoutParams lp = activity.getWindow()
                    .getAttributes();
            //下面是为了兼容华为手机
            if (bgAlpha == 1) {
                //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            } else {
                //此行代码主要是解决在华为手机上半透明效果无效的bug
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
            lp.alpha = bgAlpha;
            activity.getWindow().setAttributes(lp);
        }
    }

    /**
     * 强制隐藏输入法键盘
     */
    public static void hideKeyBoard(EditText edittext) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        }
    }

    /**
     * 强制显示输入法键盘
     */
    public static void showKeyBoard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**     * 输入法是否显示
     */
    public static boolean isKeyBoardActive(View view) {
        boolean bool = false;
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            bool = true;
        }
        return bool;
    }


}
