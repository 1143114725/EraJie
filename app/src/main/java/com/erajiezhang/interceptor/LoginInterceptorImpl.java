package com.erajiezhang.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.erajie.rxutils.RxLogTool;

/**
 * @author EraJieZhang
 * @data 2020/1/16
 */
// 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
// 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
@Interceptor(name = "login", priority = 6)
public class LoginInterceptorImpl implements IInterceptor {
	@Override
	public void process(Postcard postcard, InterceptorCallback callback) {
		String path = postcard.getPath();
		String group = postcard.getGroup();
		
		RxLogTool.v("process拦截开始-------");
		RxLogTool.v("path = " + path);
		RxLogTool.v("group = " + group);
		RxLogTool.v("process拦截结束-------");
		
//		2020-01-16 18:15:42.931 31341-1000/com.erajiezhang V/EraJieZhang: path = /activity/ShowClockActivity
//		2020-01-16 18:15:42.931 31341-1000/com.erajiezhang V/EraJieZhang: group = activity
//		if ( group.equals("activity") ){
//			Postcard build = ARouter.getInstance().build(ARouterPath.GameContentActivity);
//			build.setGroup(ARouterPath.GROUP_SCHULTEGRIL);
//			build.navigation();
//		}else{
			callback.onContinue(postcard);
//		}
		
		
	}
	
	@Override
	public void init(Context context) {
		RxLogTool.v("路由登录拦截器初始化成功");
	}
}
