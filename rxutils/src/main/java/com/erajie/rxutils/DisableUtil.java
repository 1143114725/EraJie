package com.erajie.rxutils;

import android.view.View;

/**
 * Created by EEH on 2018/9/13.
 * 有关禁用View的工具类
 */
public class DisableUtil {



    /**
     * 唯一单例模式
     * @return
     */
    private static DisableUtil mInstance;

    public synchronized static DisableUtil getInstance() {

        if (mInstance == null) {
            mInstance = new DisableUtil();
        }
        return mInstance;
    }

    /**
     * 大多数控件的禁用
     * @param v         btn控件对象
     * @param isdisable 禁用还是解禁（t:解禁，f：禁用）
     */
    public void ViewDisable(View v, boolean isdisable) {

        v.setClickable(isdisable);
    }

    /**
     * button的禁用
     * @param v
     * @param isdisable 禁用还是解禁（t:解禁，f：禁用）
     */
    public void BtnDisable(View v, boolean isdisable) {

        v.setEnabled(isdisable);
    }

    /**
     * EditText的禁用（只能不允许输入，但是点击事件还是存在的）
     * @param v
     * @param isdisable
     */
    public void TvDisable(View v, boolean isdisable) {

        v.setFocusable(isdisable);
        v.setFocusableInTouchMode(isdisable);
    }

}
