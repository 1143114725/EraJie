package com.erajie;

import android.content.Context;

import com.erajie.httptoos.BaseObserver;
import com.erajie.httptoos.HttpCallBack;
import com.erajie.httptoos.HttpManager;

/**
 * 统一管理所有的网络请求
 * @author EraJieZhang
 * @data 2018/11/5
 */
public class SendHttpRequest {

    private static final String TAG = "SendHttpRequest";

    public static void sendLogin(Context context, HttpCallBack callBack) {
        /*请求body*/
        HttpManager httpManager = HttpManager.getInstance();

        httpManager.doHttp(httpManager.httpService.reguest(), new BaseObserver(context,callBack));

    }

}
