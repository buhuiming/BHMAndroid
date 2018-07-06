package com.bhm.sdk.bhmlibrary.views;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.bhm.sdk.bhmlibrary.R;

/** 圆角，阴影，颜色的view
 *  拒绝再重复的写shape的xml
 * Created by bhm on 2018/7/6.
 */

public class ShadowView {

    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder {
        private View view;
        //颜色
        @ColorInt
        private int mColor = R.color.color_blue;
        //阴影颜色
        @ColorInt
        private int mShadowColor = R.color.white;
        //当需要渐变色时，可以传一个颜色数组进来，默认会从左到右渐进变色
        private int[] mGradientColorArray;
        //与上面的渐变色数组配合使用，指定每个颜色的位置（注意必须与上面的颜色数组length一样）
        @Nullable
        private float[] mGradientPositions;
        //如果从左到右渐进变色的需求无法满足你，可以自定义一个LinearGradient传进来
        private LinearGradient mLinearGradient;
        //圆角半径
        private int mRadius;
        private int mShadowRadius;
        //阴影偏移量
        private int mOffsetX = 0;
        private int mOffsetY = 0;


        public Builder() {
            //初始化默认值
            mRadius = 10;
            mShadowRadius = 0;
            mOffsetX = 0;
            mOffsetY = 0;
        }

        public Builder setColor(@ColorInt int color) {
            this.mColor = color;
            return this;
        }

        public Builder setTargetView(View view) {
            this.view = view;
            return this;
        }

        public Builder setShadowColor(@ColorInt int shadowColor) {
            this.mShadowColor = shadowColor;
            return this;
        }

        public Builder setGradientColorArray(@Nullable int[] colorArray) {
            this.mGradientColorArray = colorArray;
            return this;
        }

        public Builder setGradientPositions(@Nullable float[] positions) {
            this.mGradientPositions = positions;
            return this;
        }

        public Builder setLinearGradient(@Nullable LinearGradient linearGradient) {
            this.mLinearGradient = linearGradient;
            return this;
        }

        public Builder setRadius(int radius) {
            this.mRadius = radius;
            return this;
        }

        public Builder setShadowRadius(int shadowRadius) {
            this.mShadowRadius = shadowRadius;
            return this;
        }

        public Builder setOffsetX(int offsetX) {
            this.mOffsetX = offsetX;
            return this;
        }

        public Builder setOffsetY(int offsetY) {
            this.mOffsetY = offsetY;
            return this;
        }

        private CustomShadowBackground builder() {
            return new CustomShadowBackground(mColor, mGradientColorArray, mGradientPositions,
                    mShadowColor, mLinearGradient, mRadius, mShadowRadius, mOffsetX, mOffsetY);
        }

        public void build(){
            //关闭硬件加速
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            ViewCompat.setBackground(view, builder());
        }
    }

    /** 自定义带阴影Drawable背景
     * Created by bhm on 2018/7/6.
     */

    public static class CustomShadowBackground extends Drawable {

        @ColorInt
        private int mColor;
        @ColorInt
        private int mShadowColor;
        @Nullable
        private int[] mGradientColorArray;
        @Nullable
        private float[] mGradientPositions;
        @Nullable
        private LinearGradient mLinearGradient;

        private int mRadius;
        private int mShadowRadius;

        private int mOffsetX;
        private int mOffsetY;

        @Nullable
        private RectF mRectF;
        @Nullable
        private Paint mPaint;

        protected CustomShadowBackground(@ColorInt int color, @Nullable int[] colorArray,
                                         @Nullable float[] gradientPositions, @ColorInt int shadowColor,
                                         @Nullable LinearGradient linearGradient,
                                         int radius, int shadowRadius, int offsetX, int offsetY) {
            this.mColor = color;
            this.mGradientColorArray = colorArray;
            this.mGradientPositions = gradientPositions;
            this.mShadowColor = shadowColor;
            this.mLinearGradient = linearGradient;

            this.mRadius = radius;
            this.mShadowRadius = shadowRadius;

            this.mOffsetX = offsetX;
            this.mOffsetY = offsetY;

        }

        @Override
        public void draw(@Nullable Canvas canvas) {
            if (mRectF == null) {
                Rect bounds = getBounds();
                mRectF = new RectF(bounds.left + mShadowRadius - mOffsetX, bounds.top + mShadowRadius - mOffsetY,
                        bounds.right - mShadowRadius - mOffsetX, bounds.bottom - mShadowRadius - mOffsetY);
            }

            if (mPaint == null) {
                initPaint();
            }

            canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
        }

        @Override
        public void setAlpha(int alpha) {
            mPaint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            mPaint.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

        private void initPaint() {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setShadowLayer(mShadowRadius, mOffsetX, mOffsetY, mShadowColor);

            if (mGradientColorArray != null && mGradientColorArray.length > 1) {
                boolean isGradientPositions = mGradientPositions != null && mGradientPositions.length > 0
                        && mGradientPositions.length == mGradientColorArray.length;

                mPaint.setShader(mLinearGradient == null ? new LinearGradient(mRectF.left, 0, mRectF.right, 0, mGradientColorArray,
                        isGradientPositions ? mGradientPositions : null, Shader.TileMode.CLAMP) : mLinearGradient);
            } else {

                mPaint.setColor(mColor);
            }
        }
    }
}
