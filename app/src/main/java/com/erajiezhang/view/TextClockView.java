package com.erajiezhang.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import java.util.Calendar;


/**
 * 自定义罗盘时钟
 * 开启方法在最下面
 *
 * @author EraJieZhang
 * @data 2019/8/25
 */
public class TextClockView extends View {
	
	/**
	 * 全局画笔
	 */
	private Paint mPaint = createPaint(Color.RED);
	private Paint mHelperPaint = createPaint(Color.RED);
	private Float mWidth = -1f;
	private Float mHeight;
	
	private Float mHourR;
	private Float mMinuteR;
	private Float mSecondR;
	
	private Float mHourDeg;
	private Float mMinuteDeg;
	private Float mSecondDeg;
	
	private ValueAnimator mAnimator;
	
	public TextClockView(Context context) {
		super(context);
	}
	
	public TextClockView(Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		//处理动画，声明全局的处理器
		mAnimator = ValueAnimator.ofFloat(6f, 0f);//由6降到1
		mAnimator.setDuration(150);
		mAnimator.setInterpolator(new LinearInterpolator()); //插值器设为线性
		
		doInvalidate();
		
	}
	
	public TextClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		
		mWidth = (float) (getScreen().widthPixels - 8 - 8);
		mHeight = (float) (getScreen().heightPixels - 8 - 8);
		
		//后文会涉及到
		//统一用View宽度*系数来处理大小，这样可以联动适配样式
		mHourR = mWidth * 0.18f;
		mMinuteR = mWidth * 0.35f;
		mSecondR = mWidth * 0.45f;
		
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if ( canvas == null ) {
			return;
		} else {
			canvas.drawColor(Color.BLACK);
		}
		
		canvas.save();
		canvas.translate(mWidth / 2, mHeight / 2);
		
		
		//绘制各元件，后文会涉及到
		drawCenterInfo(canvas);
		
		drawHour(canvas, mHourDeg);
		drawMinute(canvas, mMinuteDeg);
		drawSecond(canvas, mSecondDeg);
		
		//从原点处向右画一条辅助线，之后要处理文字与x轴的对齐问题，稍后再说
//		canvas.drawLine(0f, 0f, mWidth, 0f, mHelperPaint);
		
		canvas.restore();
	}
	
	/**
	 * 绘制圆中信息
	 */
	private void drawCenterInfo(Canvas canvas) {
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		mPaint.setTextSize(mHourR * 0.4f);
		mPaint.setAlpha(255);
		mPaint.setTextAlign(Paint.Align.CENTER);
		
		Rect rect = new Rect();
		String str = hour + ":" + minute;
		mPaint.getTextBounds(str, 0, str.length(), rect);
		int w = rect.width();
		int h = rect.height();
		canvas.drawText(str, 0f, getBottomY(), mPaint);
		String[] arr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
//		System.out.println("今天是：" + arr[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
		String dayOfWeek = arr[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		int month = calendar.get(calendar.MONTH);
		int day = calendar.get(calendar.DAY_OF_MONTH);
		String strday = month + "月" + day + "日 " + dayOfWeek;
		mPaint.setTextSize(mHourR * 0.16f);
		mPaint.setAlpha(255);
		mPaint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(strday, 0f, getTopY(), mPaint);
	}
	
	/**
	 * 绘制小时
	 */
	private void drawHour(Canvas canvas, Float degrees) {
		mPaint.setTextSize(mHourR * 0.16f);
		//处理整体旋转
		canvas.save();
		canvas.rotate(degrees);
		
		for (int i = 0, size = 12; i < size; i++) {
			canvas.save();
			//从x轴开始旋转，每30°绘制一下「几点」，12次就画完了「时圈」
			float iDeg = 30f * i;
			canvas.rotate(iDeg);
			int alpha;
			if ( (iDeg + degrees) == 0f ) {
				alpha = 255;
			} else {
				alpha = (int) (0.6f * 255);
			}
			
			mPaint.setAlpha(alpha);
			mPaint.setTextAlign(Paint.Align.LEFT);
			String hourstr;
			if ( (i + 1) < 10 ) {
				hourstr = "0" + (i + 1) + "点";
			} else {
				hourstr = (i + 1) + "点";
			}
			
			
			canvas.drawText(hourstr, mHourR, getCenteredY(), mPaint);
			canvas.restore();
			
		}
		
		canvas.restore();
	}
	
	/**
	 * 绘制分钟
	 */
	private void drawMinute(Canvas canvas, Float degrees) {
		
		mPaint.setTextSize(mHourR * 0.2f);
		//处理整体旋转
		canvas.save();
		canvas.rotate(degrees);
		
		for (int i = 0, size = 60; i < size; i++) {
			canvas.save();
			//从x轴开始旋转，每30°绘制一下「几点」，12次就画完了「时圈」
			float iDeg = 6 * i;
			canvas.rotate(iDeg);
			int alpha;
			if ( iDeg + degrees == 0f ) {
				alpha = 255;
			} else {
				alpha = (int) (0.6f * 255);
			}
			
			mPaint.setAlpha(alpha);
			mPaint.setTextAlign(Paint.Align.RIGHT);
			String minutestr;
			if ( (i + 1) < 10 ) {
				minutestr = "0" + (i + 1) + "分";
			} else {
				minutestr = (i + 1) + "分";
			}
			
			
			if ( i < 59 ) {
				canvas.drawText(minutestr, mMinuteR, getCenteredY(), mPaint);
			} else {
				canvas.drawText("00分", mMinuteR, getCenteredY(), mPaint);
			}
			canvas.restore();
		}
		
		canvas.restore();
		
	}
	
	/**
	 * 绘制秒
	 */
	private void drawSecond(Canvas canvas, Float degrees) {
		mPaint.setTextSize(mHourR * 0.25f);
		//处理整体旋转
		canvas.save();
		canvas.rotate(degrees);
		for (int i = 0, size = 60; i < size; i++) {
			canvas.save();
			//从x轴开始旋转，每30°绘制一下「几点」，12次就画完了「时圈」
			float iDeg = 6 * i;
			canvas.rotate(iDeg);
			int alpha;
			if ( iDeg + degrees == 0f ) {
				alpha = 255;
			} else {
				alpha = (int) (0.6f * 255);
			}
			mPaint.setAlpha(alpha);
			mPaint.setTextAlign(Paint.Align.RIGHT);
			String secondstr = "";
			if ( i + 1 < 10 ) {
				secondstr = "0" + (i + 1) + "秒";
			} else {
				secondstr = i + 1 + "秒";
			}
			
			
			if ( i < 59 ) {
				canvas.drawText(secondstr, mSecondR, getCenteredY(), mPaint);
			} else {
				canvas.drawText("00秒", mSecondR, getCenteredY(), mPaint);
			}
			canvas.restore();
		}
		canvas.restore();
	}
	
	
	/**
	 * 创建画笔
	 */
	private Paint createPaint(int color) {
		Paint print = new Paint();
		print.setStyle(Paint.Style.FILL);
		print.setAntiAlias(true);
		print.setColor(color);
		return print;
		
	}
	
	/**
	 * 得到屏幕宽度对象
	 *
	 * @return
	 */
	private DisplayMetrics getScreen() {
		WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics;
	}
	
	/**
	 * 中间对齐
	 *
	 * @return
	 */
	private float getCenteredY() {
		Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
		return mPaint.getFontSpacing() / 2 - fontMetrics.bottom;
	}
	
	/**
	 * 取Y轴的值，让其底部对齐
	 *
	 * @return
	 */
	private float getBottomY() {
		Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
		
		return -fontMetrics.bottom;
	}
	
	/**
	 * 取Y轴的值，让其底部对齐
	 *
	 * @return
	 */
	private float getTopY() {
		Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
		
		return -fontMetrics.top;
	}
	
	
	/**
	 * 每一秒调用一次，更新视图
	 */
	public void doInvalidate() {
		Calendar mCalendar = Calendar.getInstance();
		int hour = mCalendar.get(Calendar.HOUR);
		final int minute = mCalendar.get(Calendar.MINUTE);
		StringBuilder strMiute = new StringBuilder();
		strMiute.append(minute);
		if ( minute < 10 ) {
			strMiute.insert(0, "0");
		}
		final int second = mCalendar.get(Calendar.SECOND);
		
		mHourDeg = -360 / 12f * (hour - 1);
		mMinuteDeg = -360 / 60f * (minute - 1);
		mSecondDeg = -360 / 60f * (second - 1);
		
		//记录当前角度，然后让秒圈线性的旋转6°
		final float hd = mHourDeg;
		final float md = mMinuteDeg;
		final float sd = mSecondDeg;
		
		
		//处理动画
		mAnimator.removeAllUpdateListeners();//需要移除先前的监听
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float av = (Float) animation.getAnimatedValue();
				
				if ( minute == 0 && second == 0 ) {
					mHourDeg = hd + av * 5;//时圈旋转角度是分秒的5倍，线性的旋转30°
				}
				if ( second == 0 ) {
					mMinuteDeg = md + av;//线性的旋转6°
				}
				mSecondDeg = sd + av;//线性的旋转6°
				invalidate();
			}
		});
		
		
		mAnimator.start();
	}
	
	/**
	 * 启动控制
	 *
	 * @param isstart true : 开启 false : 关闭
	 */
	public void start(final boolean isstart) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if ( isstart ) {
					doInvalidate();
					start(isstart);
				}
				
			}
		}, 1000);
		handler = null;
		
	}
	
}
