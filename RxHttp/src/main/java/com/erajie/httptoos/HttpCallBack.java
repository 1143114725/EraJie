package com.erajie.httptoos;


/**
 *  网络请求返回接口
 * @date  2018/1/17
 * @author EraJi
 */

public interface HttpCallBack <T>  {

    /**
     * 请求成功的回调方法
     * @param t 返回数据格式化后的实体
     */
    void onNext(T t);

    /**
     * 请求失败后的回调方法
     * @param msg   失败后返回的信息
     */
    void onError(String msg);


}
