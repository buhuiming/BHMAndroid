package com.bhm.sdk.bhmlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**SharedPreferences 工具类
 * Created by bhm on 2018/7/03.
 */

public class SPUtils {

    public static void put(Context context, String key, Object object){
        put(context, context.getPackageName(), key, object);
    }

    public static void put(Context context, String fileName, String key, Object object){
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    public static Object get(Context context, String key, Object defaultObject) {
        return get(context, context.getPackageName(), key, defaultObject);
    }

    public static Object get(Context context, String fileName, String key, Object defaultObject){
        if (contains(context, fileName, key)) {
            SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            if (defaultObject instanceof String) {
                return sp.getString(key, (String) defaultObject);
            } else if (defaultObject instanceof Integer) {
                return sp.getInt(key, (Integer) defaultObject);
            } else if (defaultObject instanceof Boolean) {
                return sp.getBoolean(key, (Boolean) defaultObject);
            } else if (defaultObject instanceof Float) {
                return sp.getFloat(key, (Float) defaultObject);
            } else if (defaultObject instanceof Long) {
                return sp.getLong(key, (Long) defaultObject);
            }
            return defaultObject;
        } else {
            return defaultObject;
        }
    }

    /**
     * commit方法是同步的,apply方法是异步的，所以尽量使用apply方法.
     * 但是apply是new api，为了兼容低版本客户端,使用以下兼容方案
     */
    private static class SharedPreferencesCompat {

        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        public static void apply(SharedPreferences.Editor editor) {
            try {
                Method sApplyMethod = findApplyMethod();
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ignored) {
                ignored.printStackTrace();
            }
            editor.commit();
        }
    }

    public static boolean contains(Context context, String fileName, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static boolean contains(Context context, String key) {
        return contains(context, context.getPackageName(), key);
    }
}
