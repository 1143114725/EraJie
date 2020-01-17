package com.eeh.schultegril.ViewUtils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 简单的工厂模式创建dialog
 * <p>
 * Created by EEH on 2018/3/9.
 * <p>
 * 宽高要是自适应就是 heightpx（-2）
 * 填满布局就是       heightpx（-1）
 */

public class CustomDialog extends Dialog {
	private Context context;
	private int height, width;
	private boolean cancelTouchout;
	private View view;
	
	private CustomDialog(Builder builder) {
		super(builder.context);
		context = builder.context;
		height = builder.height;
		width = builder.width;
		cancelTouchout = builder.cancelTouchout;
		view = builder.view;
	}
	
	private CustomDialog(Builder builder, int resStyle) {
		super(builder.context, resStyle);
		context = builder.context;
		height = builder.height;
		width = builder.width;
		cancelTouchout = builder.cancelTouchout;
		view = builder.view;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(view);
		
		setCanceledOnTouchOutside(cancelTouchout);
		
		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();
		lp.gravity = Gravity.CENTER;
		lp.height = height;
		lp.width = width;
		win.setAttributes(lp);
	}
	
	public static final class Builder {
		
		private Context context;
		private int height, width;
		private boolean cancelTouchout;
		private View view;
		private int resStyle = -1;
		
		
		public Builder(Context context) {
			this.context = context;
		}
		
		/**
		 * 吧自定义的view传进来
		 *
		 * @param resView
		 * @return
		 */
		public Builder view(int resView) {
			view = LayoutInflater.from(context).inflate(resView, null);
			return this;
		}
		
		/**
		 * 设置dialog的高 PX
		 *
		 * @param val
		 * @return
		 */
		public Builder heightpx(int val) {
			height = val;
			return this;
		}
		
		/**
		 * 设置dialog 的宽 PX
		 *
		 * @param val
		 * @return
		 */
		public Builder widthpx(int val) {
			width = val;
			return this;
		}
		
		/**
		 * 传入dp形式的高
		 *
		 * @param val
		 * @return
		 */
		public Builder heightdp(int val) {
			
			height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				val, context.getResources().getDisplayMetrics());
//            height = DensityUtil.dip2px(context, val);
			return this;
		}
		
		/**
		 * 传入dp形式的宽
		 *
		 * @param val
		 * @return
		 */
		public Builder widthdp(int val) {
			width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				val, context.getResources().getDisplayMetrics());
//            width = DensityUtil.dip2px(context, val);
			return this;
		}
		
		/**
		 * 设置dialog的高 从资源文件中获取数值
		 *
		 * @param dimenRes 资源文件的地址
		 * @return
		 */
		public Builder heightDimenRes(int dimenRes) {
			height = context.getResources().getDimensionPixelOffset(dimenRes);
			return this;
		}
		
		/**
		 * 设置dialog的宽 从资源文件中获取数值
		 *
		 * @param dimenRes 资源文件的地址
		 * @return
		 */
		public Builder widthDimenRes(int dimenRes) {
			width = context.getResources().getDimensionPixelOffset(dimenRes);
			return this;
		}
		
		/**
		 * 应该是设置dialog的样式 没侧式过
		 *
		 * @param resStyle
		 * @return
		 */
		public Builder style(int resStyle) {
			this.resStyle = resStyle;
			return this;
		}
		
		/**
		 * 点布局外边是否取消对话框
		 *
		 * @param val t 取消 f 不取消   默认不取消
		 * @return
		 */
		public Builder cancelTouchout(boolean val) {
			cancelTouchout = val;
			return this;
		}
		
		/**
		 * 自定义布局的点击事件
		 *
		 * @param viewRes  按钮的id
		 * @param listener 按钮的反应事件
		 * @return
		 */
		public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
			view.findViewById(viewRes).setOnClickListener(listener);
			return this;
		}
		
		
		/**
		 * 没什么用啊
		 * 设置seekbar的相关信息
		 *
		 * @param viewRes  seekbar的id
		 * @param Max      最大值
		 * @param Progress 设置值
		 * @param listener 回掉方法
		 * @return
		 */
		public Builder addProgress(int viewRes, int Max, int Progress, SeekBar.OnSeekBarChangeListener listener) {
			SeekBar seekBar = view.findViewById(viewRes);
			seekBar.setMax(Max);
			seekBar.setProgress(Progress);
			seekBar.setOnSeekBarChangeListener(listener);
			return this;
		}
		
		/**
		 * 设置布局里某个textview,edittext,button的文字
		 *
		 * @param viewRes 按钮的id
		 * @param text    设置的文字
		 * @return
		 */
		public Builder setViewtext(int viewRes, String text) {
			View v = view.findViewById(viewRes);
			if ( v instanceof TextView ) {
				TextView tv = (TextView) v;
				tv.setText(text);
			}
			if ( v instanceof EditText ) {
				EditText et = (EditText) v;
				et.setText(text);
			}
			if ( v instanceof Button ) {
				Button btn = (Button) v;
				btn.setText(text);
			}
			return this;
		}
		
		/**
		 * 设置布局里某个img的图片
		 *
		 * @param viewRes
		 * @return
		 */
		public Builder setImageView(int viewRes, Bitmap bitmap, int imgID, Drawable drawable) {
			ImageView img = (ImageView) view.findViewById(viewRes);
			if ( bitmap != null ) {
				img.setImageBitmap(bitmap);
			}
			if ( imgID != 0 ) {
				img.setImageResource(imgID);
			}
			if ( drawable != null ) {
				img.setImageDrawable(drawable);
			}
			return this;
		}
		
		public CustomDialog build() {
			if ( resStyle != -1 ) {
				return new CustomDialog(this, resStyle);
			} else {
				return new CustomDialog(this);
			}
		}
	}
}

/**
 * Dialog dialog;
 * dialog =new CustomDialog.Builder(this).view(R.layout.dialog_layout)
 * .heightpx(1000)
 * .widthpx(1200)
 * .cancelTouchout(true)
 * .addViewOnclick(R.id.ll_a,new View.OnClickListener(){
 *
 * @Override public void onClick(View v) {
 * BaseToast.showShortToast(mActivity,"布局A");
 * dialog.dismiss();
 * }
 * })
 * .addViewOnclick(R.id.ll_b,new View.OnClickListener(){
 * @Override public void onClick(View v) {
 * BaseToast.showShortToast(mActivity,"布局B");
 * dialog.dismiss();
 * }
 * })
 * .addViewOnclick(R.id.ll_c,new View.OnClickListener(){
 * @Override public void onClick(View v) {
 * BaseToast.showShortToast(mActivity,"布局C");
 * dialog.dismiss();
 * }
 * })
 * .addViewOnclick(R.id.ll_d,new View.OnClickListener(){
 * @Override public void onClick(View v) {
 * BaseToast.showShortToast(mActivity,"布局D");
 * dialog.dismiss();
 * }
 * })
 * .build();
 * dialog.show();
 */


