package com.bhm.sdk.bhmlibrary.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bhm.sdk.bhmlibrary.R;
import com.bhm.sdk.bhmlibrary.utils.DisplayUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


/** 标题栏 view
 * @author buhuiming
 *
 */
public class TitleBar extends RelativeLayout {

	private Context context;
	private AutoScaleTextView tv_title_bar_title;//标题
	private ImageView img_title_bar_left;//左边image
	private ImageView img_title_bar_right;//右边image
	private TextView tv_title_bar_left;//左边文字
	private TextView tv_title_bar_right;//右边文字
	private RelativeLayout rl_base_title_bar;
	private LinearLayout ll_title_bar_left;
	private LinearLayout ll_title_bar_right;
	private View view_divider;

	public TitleBar(Context context) {
		super(context);
		initView(context);
	}

	public TitleBar(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		initView(context);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
		float titleBarHeight = typedArray.getDimension(R.styleable.TitleBar_titleBarHeight, 48f);
		int backGroundColor = typedArray.getColor(R.styleable.TitleBar_backGroundColor, ContextCompat.
				getColor(context, R.color.white));
		int titleTextColor = typedArray.getColor(R.styleable.TitleBar_titleTextColor, ContextCompat.
				getColor(context, R.color.black));
		int rightTextColor = typedArray.getColor(R.styleable.TitleBar_rightTextColor, ContextCompat.
				getColor(context, R.color.black));
		int leftTextColor = typedArray.getColor(R.styleable.TitleBar_leftTextColor, ContextCompat.
				getColor(context, R.color.black));
		int dividerColor = typedArray.getColor(R.styleable.TitleBar_dividerColor, ContextCompat.
				getColor(context, R.color.color_divider));
		String titleText = typedArray.getString(R.styleable.TitleBar_titleText);
		String rightText = typedArray.getString(R.styleable.TitleBar_rightText);
		String leftText = typedArray.getString(R.styleable.TitleBar_leftText);
		boolean isLeftViewShow = typedArray.getBoolean(R.styleable.TitleBar_isLeftViewShow,false);
		boolean isRightViewShow = typedArray.getBoolean(R.styleable.TitleBar_isRightViewShow,false);

		int leftViewBackgroundResource = typedArray.getResourceId(R.styleable.TitleBar_leftViewBackgroundResource,
				-100);
		int rightViewBackgroundResource = typedArray.getResourceId(R.styleable.TitleBar_rightViewBackgroundResource,
				-100);
		float divider = typedArray.getDimension(R.styleable.TitleBar_dividerHeight, 0f);

		float leftTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_leftTextSize, 42);
		float rightTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_rightTextSize, 42);
		float titleTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_titleTextSize, 51);

		float leftViewMarginLeft = typedArray.getDimension(R.styleable.TitleBar_leftViewMarginLeft, 15f);
		float rightViewMarginRight = typedArray.getDimension(R.styleable.TitleBar_rightViewMarginRight, 15f);
		float leftTextViewPaddingLeft = typedArray.getDimension(R.styleable.TitleBar_leftTextViewPaddingLeft, 4f);
		float rightTextViewPaddingRight = typedArray.getDimension(R.styleable.TitleBar_rightTextViewPaddingRight, 4f);

		int titleTextStyle = typedArray.getInt(R.styleable.TitleBar_titleBarTextStyle, 0);
		int leftTextStyle = typedArray.getInt(R.styleable.TitleBar_leftTextStyle, 0);
		int rightTextStyle = typedArray.getInt(R.styleable.TitleBar_rightTextStyle, 0);

		setTitleBarHeight(titleBarHeight, false);
		setDividerHeight(divider, false);
		rl_base_title_bar.setBackgroundColor(backGroundColor);//设置背景颜色
		view_divider.setBackgroundColor(dividerColor);//设置分割线背景颜色
		tv_title_bar_title.setTextColor(titleTextColor);//设置标题颜色
		setTitleText(titleText);//设置标题
		tv_title_bar_right.setTextColor(rightTextColor);//设置右边文字颜色
		setRightText(rightText);//设置右边文字
		tv_title_bar_left.setTextColor(leftTextColor);//设置左边文字颜色
		setLeftText(leftText);//设置左边文字
		setIsLeftViewShow(isLeftViewShow);//是否显示左边布局
		setIsRightViewShow(isRightViewShow);//是否显示右边图片
		setLeftViewBackgroundResource(leftViewBackgroundResource);//左边的Image
		setRightViewBackgroundResource(rightViewBackgroundResource);//右边的Image
		tv_title_bar_left.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
		tv_title_bar_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
		tv_title_bar_title.setTextSize(titleTextSize);

		tv_title_bar_title.setTypeface(Typeface.defaultFromStyle(titleTextStyle));
		tv_title_bar_left.setTypeface(Typeface.defaultFromStyle(leftTextStyle));
		tv_title_bar_right.setTypeface(Typeface.defaultFromStyle(rightTextStyle));

		setLeftViewMarginLeft(leftViewMarginLeft, false);
		setRightViewMarginRight(rightViewMarginRight, false);
		setLeftTextViewPaddingLeft(leftTextViewPaddingLeft, false);
		setRightTextViewPaddingRight(rightTextViewPaddingRight, false);

		typedArray.recycle();
	}

	private void initView(Context context){
		this.context = context;
		// 加载布局
		LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this);
		// 获取控件
		tv_title_bar_title = findViewById(R.id.tv_title_bar_title);
		img_title_bar_left = findViewById(R.id.img_title_bar_left);
		img_title_bar_right = findViewById(R.id.img_title_bar_right);
		tv_title_bar_left = findViewById(R.id.tv_title_bar_left);
		tv_title_bar_right = findViewById(R.id.tv_title_bar_right);
		rl_base_title_bar = findViewById(R.id.rl_base_title_bar);
		ll_title_bar_left = findViewById(R.id.ll_title_bar_left);
		ll_title_bar_right = findViewById(R.id.ll_title_bar_right);
		view_divider = findViewById(R.id.view_divider);
	}

	public TitleBar setLeftViewMarginLeft(float leftViewMarginLeft, boolean isDpValue){
		if(isDpValue) {
			ll_title_bar_left.setPadding(DisplayUtil.dp2px(getContext(),leftViewMarginLeft), 0, 0, 0);
		}else{
			ll_title_bar_left.setPadding((int) leftViewMarginLeft, 0, 0, 0);
		}
		return  this;
	}

	public TitleBar setRightViewMarginRight(float rightViewMarginRight, boolean isDpValue){
		if(isDpValue) {
			ll_title_bar_right.setPadding(0, 0, DisplayUtil.dp2px(getContext(), rightViewMarginRight), 0);
		}else{
			ll_title_bar_right.setPadding(0, 0, (int) rightViewMarginRight, 0);
		}
		return  this;
	}

	public TitleBar setLeftTextViewPaddingLeft(float leftTextViewPaddingLeft, boolean isDpValue){
		if(isDpValue) {
			tv_title_bar_left.setPadding(DisplayUtil.dp2px(getContext(),leftTextViewPaddingLeft), 0, 0, 0);
		}else{
			tv_title_bar_left.setPadding((int) leftTextViewPaddingLeft, 0, 0, 0);
		}
		return  this;
	}

	public TitleBar setRightTextViewPaddingRight(float rightTextViewPaddingRight, boolean isDpValue){
		if(isDpValue) {
			tv_title_bar_right.setPadding(0,  0, DisplayUtil.dp2px(context, rightTextViewPaddingRight),0);
		}else{
			tv_title_bar_right.setPadding(0, 0, (int) rightTextViewPaddingRight, 0);
		}
		return  this;
	}

	public TitleBar setLeftTextSize(float size, boolean px){
		if(px){
			tv_title_bar_left.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		}else {
			tv_title_bar_left.setTextSize(size);
		}
		return this;
	}

	public TitleBar setRightTextSize(float size, boolean px){
		if(px){
			tv_title_bar_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		}else {
			tv_title_bar_right.setTextSize(size);
		}
		return this;
	}

	public TitleBar setTitleTextSize(float size, boolean px){
		if(px){
			tv_title_bar_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		}else {
			tv_title_bar_title.setTextSize(size);
		}
		return this;
	}

	public TitleBar setTitleBarHeight(float height, boolean isDpValue){
		ViewGroup.LayoutParams lp = rl_base_title_bar.getLayoutParams();
		lp.height = isDpValue ? DisplayUtil.dp2px(getContext(), height) : (int) height;
		rl_base_title_bar.setLayoutParams(lp);
		return this;
	}

	public TitleBar setDividerHeight(float height, boolean isDpValue){
		ViewGroup.LayoutParams lp = view_divider.getLayoutParams();
		lp.height = isDpValue ? DisplayUtil.dp2px(getContext(), height) : (int) height;
		view_divider.setLayoutParams(lp);
		return this;
	}

	/** 设置背景颜色
	 * @param color
	 */
	public TitleBar setDividerColor(int color){
		view_divider.setBackgroundColor(ContextCompat.getColor(context, color));
		return this;
	}

	/** 设置背景颜色
	 * @param color
	 */
	public TitleBar setBackGroundColor(int color){
		rl_base_title_bar.setBackgroundColor(ContextCompat.getColor(context, color));
		return this;
	}

	/** 设置标题
	 * @param title
	 */
	public TitleBar setTitleText(String title){
		if(!TextUtils.isEmpty(title) && title.length() >= 20){
			tv_title_bar_title.setText(title.substring(0,20)+"...");
		}else {
			tv_title_bar_title.setText(title);
		}
		return this;
	}

	/** 设置标题颜色
	 * @param color
	 */
	public TitleBar setTitleTextColor(int color){
		tv_title_bar_title.setTextColor(ContextCompat.getColor(context, color));
		return this;
	}

	/** 设置右边文字
	 * @param title
	 */
	public TitleBar setRightText(String title){
		if(!TextUtils.isEmpty(title) && title.length() >= 7){
			tv_title_bar_right.setText(title.substring(0,7)+"...");
		}else {
			tv_title_bar_right.setText(title);
		}
		return this;
	}

	/** 设置右边文字颜色
	 * @param color
	 */
	public TitleBar setRightTextColor(int color){
		tv_title_bar_right.setTextColor(ContextCompat.getColor(context, color));
		return this;
	}
	/** 设置左边文字
	 * @param title
	 */
	public TitleBar setLeftText(String title){
		if(!TextUtils.isEmpty(title) && title.length() >= 7){
			tv_title_bar_left.setText(title.substring(0,7)+"...");
		}else {
			tv_title_bar_left.setText(title);
		}
		return this;
	}

	/** 设置左边文字颜色
	 * @param color
	 */
	public TitleBar setLeftTextColor(int color){
		tv_title_bar_left.setTextColor(ContextCompat.getColor(context, color));
		return this;
	}

	/** 设置点击事件
	 * @param listener
	 */
	public TitleBar setLeftOnClickListener(OnClickListener listener){
		ll_title_bar_left.setOnClickListener(listener);
		return this;
	}

	/** 设置左边布局点击关闭界面
	 * @param activity
	 */
	public TitleBar setLeftViewClickToClose(final @NonNull Activity activity){
		ll_title_bar_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.finish();
			}
		});
		return this;
	}

	/** 设置点击事件
	 * @param listener
	 */
	public TitleBar setRightOnClickListener(OnClickListener listener){
		ll_title_bar_right.setOnClickListener(listener);
		return this;
	}

	/** 设置点击事件
	 * @param listener
	 */
	public TitleBar setTitleTextOnClickListener(OnClickListener listener){
		tv_title_bar_title.setOnClickListener(listener);
		return this;
	}

	/** 设置点击事件
	 * @param listener
	 */
	public TitleBar setTitleBarOnClickListener(OnClickListener listener){
		rl_base_title_bar.setOnClickListener(listener);
		return this;
	}

	/** 设置点击事件 双击
	 * @param listener
	 */
	public TitleBar setTitleBarOnTwoClickListener(final OnTwoClickListener listener){
		rl_base_title_bar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		final GestureDetector gestureDetector = new GestureDetector(context,
				new GestureDetector.SimpleOnGestureListener() {

					@Override
					public boolean onSingleTapConfirmed(MotionEvent e) {//单击事件

						return super.onSingleTapConfirmed(e);
					}

					@Override
					public boolean onDoubleTap(MotionEvent e) {//双击事件
						listener.onTwoClick(rl_base_title_bar);
						return super.onDoubleTap(e);
					}

					@Override
					public boolean onDoubleTapEvent(MotionEvent e) {
						return super.onDoubleTapEvent(e);
					}
				});

		rl_base_title_bar.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		});
		return this;
	}

	/** 设置左边控件显示
	 */
	public TitleBar setIsLeftViewShow(boolean isLeftViewShow){
		ll_title_bar_left.setVisibility(isLeftViewShow ? View.VISIBLE : View.GONE);
		tv_title_bar_left.setVisibility(isLeftViewShow ? View.VISIBLE : View.GONE);
		img_title_bar_left.setVisibility(isLeftViewShow ? View.VISIBLE : View.GONE);
		return this;
	}

	public TitleBar setIsLeftImageViewShow(boolean isLeftImageViewShow){
		img_title_bar_left.setVisibility(isLeftImageViewShow ? View.VISIBLE : View.GONE);
		return this;
	}

	public TitleBar setIsRightImageViewShow(boolean isRightImageViewShow){
		img_title_bar_right.setVisibility(isRightImageViewShow ? View.VISIBLE : View.GONE);
		return this;
	}

	public TitleBar setIsLeftTextViewShow(boolean isLeftTextViewShow){
		tv_title_bar_left.setVisibility(isLeftTextViewShow ? View.VISIBLE : View.GONE);
		return this;
	}

	public TitleBar setIsRightTextViewShow(boolean isRightTextViewShow){
		tv_title_bar_right.setVisibility(isRightTextViewShow ? View.VISIBLE : View.GONE);
		return this;
	}

	/** 设置右边控件显示
	 */
	public TitleBar setIsRightViewShow(boolean isRightViewShow){
		ll_title_bar_right.setVisibility(isRightViewShow ? View.VISIBLE : View.GONE);
		tv_title_bar_right.setVisibility(isRightViewShow ? View.VISIBLE : View.GONE);
		img_title_bar_right.setVisibility(isRightViewShow ? View.VISIBLE : View.GONE);
		return this;
	}

	/** 设置左边控件的背景图片
	 */
	public TitleBar setLeftViewBackgroundResource(int res){
		if(res == -100){
			img_title_bar_left.setVisibility(GONE);
		}else {
			img_title_bar_left.setVisibility(View.VISIBLE);
			img_title_bar_left.setBackgroundResource(res);
		}
		return this;
	}

	public TitleBar setTitleTextMinTextSize(float minTextSize){
		tv_title_bar_title.setMinTextSize(minTextSize);
		return this;
	}

	/** 设置右边控件的背景图片
	 */
	public TitleBar setRightViewBackgroundResource(int res){
		if(res == -100){
			img_title_bar_right.setVisibility(GONE);
		}else {
			img_title_bar_right.setVisibility(View.VISIBLE);
			img_title_bar_right.setBackgroundResource(res);
		}
		return this;
	}

	public TitleBar setTitleTextStyle(int typeface){
		tv_title_bar_title.setTypeface(Typeface.defaultFromStyle(typeface));
		return this;
	}

	public TitleBar setLeftTextStyle(int typeface){
		tv_title_bar_left.setTypeface(Typeface.defaultFromStyle(typeface));
		return this;
	}

	public TitleBar setRightTextStyle(int typeface){
		tv_title_bar_right.setTypeface(Typeface.defaultFromStyle(typeface));
		return this;
	}
	
	
	/** TitleBar左边和右边布局宽度不一样导致title不居中，设置左边和右边都宽度一样
     */
    public void setTitleViewTitleInCenter(){
        ll_title_bar_left.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams paramsLeft = (RelativeLayout.LayoutParams) ll_title_bar_left.getLayoutParams();
                RelativeLayout.LayoutParams paramsRight = (RelativeLayout.LayoutParams) ll_title_bar_right.getLayoutParams();
                if(ll_title_bar_left.getWidth() > ll_title_bar_right.getWidth()){
                    paramsRight.width = ll_title_bar_left.getWidth();
                    ll_title_bar_right.setLayoutParams(paramsRight);
                }else if(ll_title_bar_left.getWidth() < ll_title_bar_right.getWidth()){
                    paramsLeft.width = ll_title_bar_right.getWidth();
                    ll_title_bar_left.setLayoutParams(paramsLeft);
                }
            }
        });
    }
	

	public LinearLayout getLeftView(){
		return ll_title_bar_left;
	}

	public LinearLayout getRightView(){
		return ll_title_bar_right;
	}

	public TextView getTitleView(){
		return tv_title_bar_title;
	}

	public ImageView getLeftImageView(){
		return img_title_bar_left;
	}

	public ImageView getRightImageView(){
		return img_title_bar_right;
	}

	public TextView getLeftTextView(){
		return tv_title_bar_left;
	}

	public TextView getRightTextView(){
		return tv_title_bar_right;
	}

	public interface OnTwoClickListener{
		void onTwoClick(View view);
	}
}
