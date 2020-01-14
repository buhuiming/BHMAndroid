package com.bhm.sdk.bhmlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
        float drawableRightPaddingLeft = typedArray.getDimension(R.styleable.TextImageView_drawableRightPaddingLeft, 6f);
        int maxLines = typedArray.getInt(R.styleable.TextImageView_maxLines, 1);
        setMaxLines(maxLines);
        setDrawableRightHintResource(drawableRightHintResource);
        setDrawableRightPaddingLeft(drawableRightPaddingLeft, false);
        setHintText(hintText);
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
        if(isDip) {
            iv_image.setPadding(DisplayUtil.dp2px(getContext(), paddingLeft), 0, 0, 0);
        }else{
            iv_image.setPadding((int) paddingLeft, 0, 0, 0);
        }
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