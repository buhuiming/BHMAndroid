package com.bhm.sdk.bhmlibrary.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;
import java.util.Stack;


/**
 * Created by bhm on 2018/5/22.
 * 封装Activity相关工具类
 */
public class ActivityStackUtils {

    private static Stack<Activity> activityStack;

    /**
     * 添加Activity 到栈
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前的Activity（堆栈中最后一个压入的)
     */
    public static Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void removeActivity(Class<?> cls) {
        for (int i = activityStack.size()-1 ; i >= 0; i--) {
            if (null != activityStack.get(i)) {
                if(activityStack.get(i).getClass().equals(cls)){
                    removeActivity(activityStack.get(i));
                    break;
                }
            }
        }
    }

    /**
     * 结束所有activity
     */
    public static void finishAllActivity(){
        for (int i = activityStack.size()-1 ; i >= 0; i--) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
                removeActivity(activityStack.get(i));
            }
        }
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 判断是否存在指定Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isExistActivity(Context context, String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }

    /**
     * app是否在后台
     *
     * @param context
     * @return true 是 false 不是
     */
    public static boolean isAPPBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
