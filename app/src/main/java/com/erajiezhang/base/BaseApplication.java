package com.erajiezhang.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erajie.rxutils.RxTool;
import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

import androidx.multidex.MultiDex;


/**
 * Appliaction基类
 * @author EraJieZhang
 */
public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";


    /**
     * 唯一单例模式
     * @return
     */
    private static BaseApplication mInstance;

    public synchronized static BaseApplication getInstance() {

        if (mInstance == null) {
            mInstance = new BaseApplication();
        }
        return mInstance;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        //工具类初始化
        RxTool.init(this);
        //xutils初始化
        x.Ext.init(this);
        //ARouter初始化
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(BaseApplication.this);
        // 初始化MultiDex
        MultiDex.install(this);
    
        //测试阶段建议设置成true，发布时设置为false。
        CrashReport.initCrashReport(getApplicationContext(), "6f8a3fec40", true);
    }
}
