package com.erajie.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.erajie.rxutils.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * 设置沉浸式通知栏
 * @author EraJieZhang
 * @data 2020/2/19
 */
public class ImmerseNotificationBarUtil {
	//颜色 默认透明
	private static int color = R.color.transparent;
	
	public static void setImmerseNotificationBar(Activity activity){
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true,activity);
			SystemBarTintManager tintManager = new SystemBarTintManager(activity);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintResource(color);//状态栏所需颜色
			
		}
	}
	@TargetApi(19)
	private static void setTranslucentStatus(boolean on, Activity activity) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
	
	/**
	 * 设置沉浸颜色
	 * @param colorint
	 */
	public static void setColor(int colorint){
		color = colorint;
	}
}



