package com.erajie.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.FragmentActivity;

import com.erajie.rxutils.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description: 所有activity的基类
 * @author EEH
 * @since 2015.12.26
 * Activity被创建时回调
 **/
public abstract class BaseActivity extends FragmentActivity {
    protected Activity mActivity;
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivity = this;

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setStatusBarColor(getResources().getColor(R.color.red,null));

//        //沉浸式代码配置android:fitsSystemWindows
//        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
//        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
//        //设置状态栏透明
//        StatusBarUtil.setTranslucentStatus(this);
//        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
//        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtil.setStatusBarColor(this, 0x55000000);
//        }



//        Window window = mActivity.getWindow();
//        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        //设置状态栏颜色
//        window.setStatusBarColor(getResources().getColor(R.color.label_link_color,null));

    }

    private int getStatusBarHeight() {
        Resources resources = mActivity.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
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

