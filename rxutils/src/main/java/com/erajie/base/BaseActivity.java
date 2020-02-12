package com.erajie.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

import androidx.fragment.app.FragmentActivity;

/**
 * @Description: 所有activity的基类
 * @author EEH
 * @since 2015.12.26
 * Activity被创建时回调
 **/
public abstract class BaseActivity extends Activity {
    protected Activity mActivity;
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivity = this;
        AppManager.getAppManager().addActivity(mActivity);
    
//        ImmerseNotificationBarUtil.setColor();
//        ImmerseNotificationBarUtil.setImmerseNotificationBar(mActivity);

    }
    
    /**
     * 表示Activity正在启动，此时Activity已处于可见状态
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 则说明Activity已在前台可见，可与用户交互了
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 则表示Activity正在停止（Paused形态），一般情况下onStop方法会紧接着被回调。
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 一般在onPause方法执行完成直接执行，表示Activity即将停止或者完全被覆盖
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 此时Activity正在被销毁，也是生命周期最后一个执行的方法
     */
    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(mActivity);
        super.onDestroy();
    }

    /**
     * 表示Activity正在重新启动，当Activity由不可见变为可见状态时
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }


    /**
     * 延迟去往新的Activity
     *
     * @param context
     *            上下文
     * @param cls
     *            目标类
     * @param delay
     *            延迟时间
     * @param bundle
     *            数据
     */
    public static void delayToActivity(final Context context,
                                       final Class<?> cls, long delay, final Bundle bundle) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(context, cls);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        }, delay);
        
    }

    /**
     * 跳转到另一个Activity，携带数据
     *
     * @param context
     *            上下文
     * @param cls
     *            目标类
     */
    public static void goToActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 启动一个activity
     *
     * @param context
     *            上下文
     * @param cls
     *            目标类
     */
    public static void goToActivity(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    /**
     * 启动一个startActivityForResult
     *
     * @param context
     *            上下文
     * @param requestCode
     *            返回标识
     * @param cls
     *            目标类
     * @param bundle
     *            数据
     */
    public static void goToActivityFor(Context context, int requestCode,
                                       Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtras(bundle);
        ((FragmentActivity) context).startActivityForResult(intent, requestCode);
    }
    
    /**
     * 启动一个startActivityForResult
     *
     * @param context
     *            上下文
     * @param requestCode
     *            返回标识
     * @param cls
     *            目标类
     */
    public static void goToActivityFor(Context context, int requestCode,
                                       Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        ((FragmentActivity) context).startActivityForResult(intent, requestCode);
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        /** 隐藏软键盘 **/
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        super.finish();
    }

 
}

