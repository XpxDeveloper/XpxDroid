/** * @author  作者 E-mail:
 * @date 创建时间：2017年7月10日 下午5:15:18 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
package com.xpxcoder.xpxDroid.map.bmap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xpxcoder.xpxDroid.R;


/**
 * @author xpsoft
 * 
 */
public class BMapLayerTest extends LinearLayout implements View.OnClickListener {

	private OnClickListener mOnClickListener;
	private Context mContext;
	private View mLayoutView;
	private RelativeLayout mBtnClose;
	private TextView mTVTitle;
	private TextView mTVDesc;
	private Button mNo;

	/**
	 * @param context
	 */
	public BMapLayerTest(Context context) {
		super(context);
		mContext = context;
		initView();
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public BMapLayerTest(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public BMapLayerTest(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView();
		// TODO 自动生成的构造函数存根
	}


	private void initView() {
		if (isInEditMode())
			return;
		// anim = AnimationUtils.loadAnimation(mContext, R.anim.welcome_rotate);
		mLayoutView = View.inflate(mContext, R.layout.dialog_wait, null);
		/*mBtnClose = (RelativeLayout) mLayoutView.findViewById(R.id.btn_close);
		mTVTitle=(TextView)mLayoutView.findViewById(R.id.bmaplayer_name);
		mTVDesc=(TextView)mLayoutView.findViewById(R.id.bmaplayer_desc);
		mLayoutView.setMinimumHeight((int) AbViewUtil.dip2px(
				mContext, 200));
		LayoutParams params = new LayoutParams((int) AbViewUtil.dip2px(
				mContext, 300), LayoutParams.WRAP_CONTENT);
		mLayoutView.setLayoutParams(params);*/
		
		addView(mLayoutView);

//		mBtnClose.setOnClickListener(this);

		if (isInEditMode())
			return;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		/*case R.id.btn_close:
		case R.id.dialogyesno_no:
			mOnClickListener.onNoClick();
			break;
		case R.id.dialogyesno_yes:
			mOnClickListener.onYesClick();
			break;*/
		default:
			break;

		}
	}

	public void setOnClickListener(OnClickListener aListener) {
		this.mOnClickListener = aListener;
	}

	public interface OnClickListener {
		public void onYesClick();
		public void onNoClick();
	}
}