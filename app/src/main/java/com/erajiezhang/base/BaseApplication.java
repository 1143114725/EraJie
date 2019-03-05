package com.erajiezhang.base;

import android.app.Application;

import com.erajie.rxutils.RxTool;

import org.xutils.x;


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
        RxTool.init(this);
        x.Ext.init(this);
        // xutils init ...
    }
}
