package com.erajie.arout;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erajie.rxutils.RxDataTool;

/**
 * aroute工具类
 * @author EraJieZhang
 * @data 2020/1/17
 */
public class BaseArouteUtil {
	private static final String defaultgroup = "app";
	/**
	 * 跳转页面-默认组
	 * @param arouterPath
	 */
	public static void returnActivity(String arouterPath){
		returnActivity(arouterPath,defaultgroup);
	}
	/**
	 * 跳转页面-指定组
	 * @param arouterPath
	 */
	public static void returnActivity(String arouterPath, String group){
		Postcard build = ARouter.getInstance().build(arouterPath);
		//组不为空-绑定
		if (!RxDataTool.isNullString(group)){
			build.setGroup(group);
		}
		build.navigation();
	}
	
	/**
	 * 返回Postcard 自己去拼接参数
	 * @param arouterPath
	 * @return
	 */
	public static Postcard getPostcard(String arouterPath) {
		return getPostcard(arouterPath,defaultgroup);
	}
	
	/**
	 * 返回Postcard 自己去拼接参数
	 * @param arouterPath
	 * @param group
	 * @return
	 */
	public static Postcard getPostcard(String arouterPath,String group) {
		Postcard build = ARouter.getInstance().build(arouterPath);
		//组不为空-绑定
		if (!RxDataTool.isNullString(group)){
			build.setGroup(group);
		}
		return build;
	}
}
