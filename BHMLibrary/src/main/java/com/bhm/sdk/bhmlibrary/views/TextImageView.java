package com.bhm.sdk.bhmlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bhm.sdk.bhmlibrary.R;
import com.bhm.sdk.bhmlibrary.utils.DisplayUtil;

import androidx.core.content.ContextCompat;


/**
 * 实现【请选择 >】这种效果
 * 2019年7月1日 10:37:41
 *  create by bhm
 */
public class TextImageView extends LinearLayout {

    private String hintText = "请选择";
    private int hintTextColor;
    private int textColor;
    private ImageView iv_image;
    private TextView tv_text;
    private int drawableRightHintResource;
    private int drawableRightResource;

    public TextImageView(Context context) {
        super(context);
        init(context, null);
    }

    public TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if(null == attrs){
            return;
        }
        LayoutInflater.from(context).inflate(R.layout.layout_text_image, this);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        tv_text = (TextView) findViewById(R.id.tv_text);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextImageView);
        hintText = typedArray.getString(R.styleable.TextImageView_hintText);
        hintTextColor = typedArray.getColor(R.styleable.TextImageView_hintTextColor, ContextCompat.
                getColor(context, R.color.hint));
        textColor = typedArray.getColor(R.styleable.TextImageView_textColor, ContextCompat.
                getColor(context, R.color.black));
        drawableRightResource = typedArray.getResourceId(R.styleable.TextImageView_drawableRightResource,
                -100);
        drawableRightHintResource = typedArray.getResourceId(R.styleable.TextImageView_drawableRightHintResource,
                -100);
        float textSize = typedArray.getDimensionPixelSize(R.styleable.TextImageView_textSize, 42);
        float drawableRightPaddingLeft = typedArray.getDimension(R.styleable.TextImageView_drawableRightPaddingLeft, 6f);
        float drawableSize = typedArray.getDimension(R.styleable.TextImageView_drawableSize, 14f);
        int maxLines = typedArray.getInt(R.styleable.TextImageView_maxLines, 1);
        int textStyle = typedArray.getInt(R.styleable.TextImageView_textStyle, 0);
        setMaxLines(maxLines);
        setDrawableRightHintResource(drawableRightHintResource);
        setDrawableRightPaddingLeft(drawableRightPaddingLeft, false);
        setDrawableSize(drawableSize, false);
        setHintText(hintText);
        tv_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv_text.setTypeface(Typeface.defaultFromStyle(textStyle));
        typedArray.recycle();
    }

    public void setMaxLines(int lines){
        tv_text.setMaxLines(lines);
    }

    public void setDrawableRightResource(int drawableRightResource){
        this.drawableRightResource = drawableRightResource;
        iv_image.setImageResource(drawableRightResource);
    }

    public void setDrawableRightHintResource(int drawableRightHintResource){
        this.drawableRightHintResource = drawableRightHintResource;
        iv_image.setImageResource(drawableRightHintResource);
    }

    public void setDrawableRightPaddingLeft(float paddingLeft, boolean isDip){
        LayoutParams params = (LayoutParams) iv_image.getLayoutParams();
        if(isDip) {
            params.leftMargin = DisplayUtil.dp2px(getContext(), paddingLeft);
        }else{
            params.leftMargin = (int) paddingLeft;
        }
        iv_image.setLayoutParams(params);
    }

    public void setDrawableSize(float drawableSize, boolean isDip){
        ViewGroup.LayoutParams layoutParams = iv_image.getLayoutParams();
        if(isDip) {
            layoutParams.width = DisplayUtil.dp2px(getContext(), drawableSize);
            layoutParams.height = DisplayUtil.dp2px(getContext(), drawableSize);
        }else{
            layoutParams.width = (int) drawableSize;
            layoutParams.height = (int) drawableSize;
        }
        iv_image.setLayoutParams(layoutParams);
    }

    public void setHintText(){
        setHintText(null);
    }

    public void setHintText(String text){
        if(TextUtils.isEmpty(text)) {
            tv_text.setText(hintText);
        }else{
            tv_text.setText(text);
        }
        tv_text.setTextColor(hintTextColor);
    }

    public void setTextSize(float textSize){
        setTextSize(textSize, Typeface.NORMAL);
    }

    public void setTextSize(float textSize, int typeface){
        tv_text.setTextSize(textSize);
        tv_text.setTypeface(Typeface.defaultFromStyle(typeface));
    }

    public CharSequence getText(){
        if(hintText.equals(tv_text.getText().toString())) {
            return tv_text.getText().toString().replace(hintText, "");
        }
        return tv_text.getText();
    }

    public void setText(String text){
        setText(text, true);
    }

    public void setText(String text, boolean enable){
        setEnabled(enable);
        if(enable){
            if(TextUtils.isEmpty(text)){
                //可以点击，但是text为空，则显示hintText，并且字体设置hint字体颜色
                tv_text.setText(hintText);
                tv_text.setTextColor(hintTextColor);
                iv_image.setImageResource(drawableRightHintResource);
            }else{
                tv_text.setText(text);
                tv_text.setTextColor(textColor);
                iv_image.setImageResource(drawableRightResource);
            }
            //可以点击，显示">"，
            iv_image.setVisibility(VISIBLE);
        }else{
            //不能点击，就隐藏掉">"，并且字体设置hint字体颜色
            tv_text.setText(com.bhm.sdk.bhmlibrary.utils.TextUtils.stringIfNull(text));
            tv_text.setTextColor(hintTextColor);
            iv_image.setVisibility(GONE);
        }
    }
}