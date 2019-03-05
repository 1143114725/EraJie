package com.erajie.httptoos;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @date  2018/10/24.
 * @author EraJi
 */
public class BaseObserver<T> implements Observer<T> {

    /**
     * 弱引用防止内存泄露
     */
    private WeakReference<Context> mContext;
    /**
     * 回调接口
     */
    private HttpCallBack callBack;

    public BaseObserver(Context context, HttpCallBack callBack) {
        this.mContext = new WeakReference<>(context);
        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        callBack.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        callBack.onError("网络中断，请检查您的网络状态");
    }

    @Override
    public void onComplete() {

    }
}
