package com.erajie.base.webview;

import android.webkit.JavascriptInterface;

import com.erajie.rxutils.view.RxToast;

/**
 * js调用安卓方法
 * @author EraJieZhang
 * @data 2020/3/7
 */
public class AndroidToJs extends Object {

    //弹出toast
    public static final String TYPE_TOAST = "toast";
    //弹出dialog
    public static final String TYPE_DIALOG = "dialog";
    //调用打电话
    public static final String TYPE_CALLPHONE = "callphone";
    //调用邮箱
    public static final String TYPE_CALLEMAIL = "callemail";
    //调用QQ
    public static final String TYPE_CALLQQ = "callQQ";

    /**
     * js调用方法 执行拨打电话
     * @param data json数据
     */
    @JavascriptInterface
    public void exePhone(String data) {

    }

    /**
     * toast提示
     * @param data
     */
    @JavascriptInterface
    public void exe(String type, String data) {

        switch (type) {
            case TYPE_TOAST:
                RxToast.showToast(data);
                break;
            case TYPE_DIALOG:
                RxToast.showToast(data);
                break;
            case TYPE_CALLPHONE:
                RxToast.showToast(data);
                break;
            case TYPE_CALLEMAIL:
                RxToast.showToast(data);
                break;
            case TYPE_CALLQQ:
                RxToast.showToast(data);
                break;
            default:
                break;
        }

    }
}
