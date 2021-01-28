package com.bhm.sdk.bhmlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.bhm.sdk.bhmlibrary.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 固定宽度，根据内容自动缩小字体大小。
 */
public class AutoScaleTextView extends AppCompatTextView {

    private float preferredTextSize;
    private float minTextSize = 10f;
    private Paint textPaint;

    public AutoScaleTextView(Context context) {
        super(context);
        init(context, null);
    }

    public AutoScaleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutoScaleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        this.textPaint = new Paint();
        this.preferredTextSize = this.getTextSize();
        if(attrs == null){
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoScaleTextView);
        this.minTextSize = a.getDimension(R.styleable.AutoScaleTextView_minTextSize, 10f);
        a.recycle();
    }

    /**
     * 设置最小的size
     *
     * @param minTextSize
     */
    public void setMinTextSize(float minTextSize) {
        this.minTextSize = minTextSize;
    }

    /**
     * 根据填充内容调整textView
     *
     * @param text
     * @param textWidth
     */
    private void refitText(String text, int textWidth) {
        if (textWidth <= 0 || text == null || text.length() == 0) {
            return;
        }
        int targetWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
        final float threshold = 0.5f;
        this.textPaint.set(this.getPaint());
        this.textPaint.setTextSize(this.preferredTextSize);
        if (this.textPaint.measureText(text) <= targetWidth) {
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.preferredTextSize);
            return;
        }

        float tempMinTextSize = this.minTextSize;
        float tempPreferredTextSize = this.preferredTextSize;

        while ((tempPreferredTextSize - tempMinTextSize) > threshold) {
            float size = (tempPreferredTextSize + tempMinTextSize) / 2;
            this.textPaint.setTextSize(size);
            if (this.textPaint.measureText(text) >= targetWidth) {
                tempPreferredTextSize = size;
            } else {
                tempMinTextSize = size;
            }
        }

        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, tempMinTextSize);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        this.refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int width, int h, int oldw, int oldh) {
        if (width != oldw) {
            this.refitText(this.getText().toString(), width);
        }
    }
}