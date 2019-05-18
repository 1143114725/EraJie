package com.erajiezhang.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.erajie.rxutils.RxDeviceTool;

/**
 * 图片添加水印的工具类
 * @author EraJieZhang
 * @data 2019/5/11
 */
public class WatermarkUtil {
	
	private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
	                                            int paddingLeft, int paddingTop) {
		if (src == null) {
			return null;
		}
		int width = src.getWidth();
		int height = src.getHeight();
		//创建一个bitmap
		Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
		//将该图片作为画布
		Canvas canvas = new Canvas(newb);
		//在画布 0，0坐标上开始绘制原始图片
		canvas.drawBitmap(src, 0, 0, null);
		//在画布上绘制水印图片
		canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
		// 保存
//		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.save();
		// 存储
		canvas.restore();
		return newb;
	}
	
	/**
	 * 绘制文字到右下角
	 * @param context
	 * @param bitmap
	 * @param text
	 * @param size
	 * @param color
	 * @param paddingBottom
	 * @param paddingRight
	 * @return
	 */
	public static Bitmap drawTextToRightBottom(Context context, Bitmap bitmap, String text, String name, String ctx,
	                                           String text1, String name1, String ctx1,
	                                           int size, int color, int paddingRight, int paddingBottom) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(color);
		paint.setTextSize(dp2px(context, size));
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return drawTextToBitmap(context, bitmap, text,name, ctx,text1,name1, ctx1, paint, bounds,
			bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
			bitmap.getHeight() - dp2px(context, paddingBottom));
	}
	
	
	
	
	//图片上绘制文字
	private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text, String name, String ctx,
	                                       String text1, String name1, String ctx1,
	                                       Paint paint, Rect bounds, int paddingLeft, int paddingTop) {
		android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();
		
		paint.setDither(true); // 获取跟清晰的图像采样
		paint.setFilterBitmap(true);// 过滤一些
		if (bitmapConfig == null) {
			bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
		}
		bitmap = bitmap.copy(bitmapConfig, true);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawText(text, paddingLeft, paddingTop, paint);
		canvas.drawText(name, paddingLeft, paddingTop+100, paint);
		canvas.drawText(ctx, paddingLeft, paddingTop+200, paint);
		
		canvas.drawText(text1, paddingLeft, paddingTop+300, paint);
		canvas.drawText(name1, paddingLeft, paddingTop+400, paint);
		canvas.drawText(ctx1, paddingLeft, paddingTop+500, paint);
		
		return bitmap;
	}
	
	/**
	 * 缩放图片
	 * @param src
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
		if (w == 0 || h == 0 || src == null) {
			return src;
		} else {
			// 记录src的宽高
			int width = src.getWidth();
			int height = src.getHeight();
			// 创建一个matrix容器
			Matrix matrix = new Matrix();
			// 计算缩放比例
			float scaleWidth = (float) (w / width);
			float scaleHeight = (float) (h / height);
			// 开始缩放
			matrix.postScale(scaleWidth, scaleHeight);
			// 创建缩放后的图片
			return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
		}
	}
	
	/**
	 * dip转pix
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dp2px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}
	
	/**
	 * 给图片加水印，网上
	 * 右下角
	 * @param src       原图
	 * @param watermark 水印
	 * @return 加水印的原图
	 */
	public static Bitmap WaterMask(Bitmap src, Bitmap watermark,Context context) {
		int w = src.getWidth();
		int h = src.getHeight();
		Log.i("WaterMask", "原图宽: "+w);
		Log.i("WaterMask", "原图高: "+h);
		// 设置原图的大小,根据屏幕大小缩放
		float newWidth = RxDeviceTool.getScreenWidth(context);
		float newHeight = h*(newWidth/w);
		// 计算缩放比例
		float scaleWidth = ( newWidth) / w;
		float scaleHeight = (newHeight) / h;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		src = Bitmap.createBitmap(src, 0, 0, w, h, matrix, true);
		
		//根据bitmap缩放水印图片
		float w1 = w / 5;
		float h1 = w1 / 5;
		//获取原始水印图片的宽、高
		int w2 = watermark.getWidth();
		int h2 = watermark.getHeight();
		
		//计算缩放的比例
		float scalewidth = ((float) w1) / w2;
		float scaleheight = ((float) h1) / h2;
		
		Matrix matrix1 = new Matrix();
		matrix1.postScale(scalewidth, scaleheight);
//		matrix1.postScale((float) 0.4, (float) 0.4);
		
		watermark = Bitmap.createBitmap(watermark, 0, 0, w2, h2, matrix1, true);
		//获取新的水印图片的宽、高
		w2 = watermark.getWidth();
		h2 = watermark.getHeight();
		
		Bitmap result = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
		// 创建一个新的和SRC长度宽度一样的位图
		Canvas cv = new Canvas(result);
		//在canvas上绘制原图和新的水印图
		cv.drawBitmap(src, 0, 0, null);
		//水印图绘制在画布的右下角，距离右边和底部都为20
		cv.drawBitmap(watermark, src.getWidth() - w2-20, src.getHeight() - h2-20, null);
		cv.save();
		cv.restore();
		
		return result;
	}



}
