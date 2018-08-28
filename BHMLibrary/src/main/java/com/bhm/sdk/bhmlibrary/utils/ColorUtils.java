package com.bhm.sdk.bhmlibrary.utils;

/**
 * Created by bhm on 2018/8/28.
 */

public class ColorUtils {

    /** 指定颜色透明度
     * @param bgAlpha 0-1
     * @param oldColor #FFFFFF
     * @return 如 #80000000
     */
    public static String colorChange(float bgAlpha, String oldColor){
        StringBuilder builder = new StringBuilder();
        builder.append("#");
        if(bgAlpha == 0f){
            builder.append("00");
        }else if(bgAlpha == 0.05f){
            builder.append("0D");
        }else if(bgAlpha == 0.1f){
            builder.append("1A");
        }else if(bgAlpha == 0.15f){
            builder.append("26");
        }else if(bgAlpha == 0.2f){
            builder.append("33");
        }else if(bgAlpha == 0.25f){
            builder.append("40");
        }else if(bgAlpha == 0.3f){
            builder.append("4D");
        }else if(bgAlpha == 0.35f){
            builder.append("59");
        }else if(bgAlpha == 0.4f){
            builder.append("66");
        }else if(bgAlpha == 0.45f){
            builder.append("73");
        }else if(bgAlpha == 0.5f){
            builder.append("80");
        }else if(bgAlpha == 0.55f){
            builder.append("8C");
        }else if(bgAlpha == 0.6f){
            builder.append("99");
        }else if(bgAlpha == 0.65f){
            builder.append("A6");
        }else if(bgAlpha == 0.7f){
            builder.append("B3");
        }else if(bgAlpha == 0.75f){
            builder.append("BF");
        }else if(bgAlpha == 0.8f){
            builder.append("CC");
        }else if(bgAlpha == 0.85f){
            builder.append("D9");
        }else if(bgAlpha == 0.9f){
            builder.append("E6");
        }else if(bgAlpha == 0.95f){
            builder.append("F2");
        }else if(bgAlpha == 1f){
            builder.append("FF");
        }

        builder.append(oldColor.replace("#", ""));

        return builder.toString();
    }
}
