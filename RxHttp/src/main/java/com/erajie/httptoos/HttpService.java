package com.erajie.httptoos;

import com.erajie.responsebean.ToolLogin;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 统一管理所有网络请求的接口
 * @author EraJi
 * @date 2018年11月5日
 * @version V2
 */

public interface  HttpService {

    /**
     * 登陆请求
     * @return  登录请求返回的实体
     */
    @GET("reguest/")
    Observable<ToolLogin> reguest();

    @GET("login/")
    Call<ToolLogin> LOGIN_CALL();

    @GET("OfflineEmpower.asp")
    Call<String> Empower(@Query("userPsd") String userPsd, @Query("userId") String userId);
}
