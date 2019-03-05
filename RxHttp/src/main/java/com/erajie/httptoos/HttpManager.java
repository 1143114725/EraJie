package com.erajie.httptoos;

import com.erajie.rxutils.RxLogTool;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by EEH on 2018/1/17.
 * @author EraJieZhang
 */
public class HttpManager {

    private static final String BASE_URL = "http://www.wanandroid.com/tools/mockapi/2388/";
    /**
     * 超时时间
     */
    private static final int CONNEC_TIMEOUT = 30;
    /**
     * 读取数据的超时时间
     */
    private static final int READ_TIMEOUT = 30;
    /**
     * 写入数据的超时时间
     */
    private static final int WRITE_TIMEOUT = 30;



    private static HttpManager mInstance;
    public HttpService httpService;

    /**
     * 私有构造方法
     */
    private HttpManager() {
        RxLogTool.e("请求地址：" + BASE_URL);
        /*
         * 设置请求超时（读写超时）时间
         */
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new NetLogInterceptor("eehNetLog", true)).
                connectTimeout(CONNEC_TIMEOUT, TimeUnit.SECONDS).
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpService = retrofit.create(HttpService.class);

    }

    /**
     * 唯一单例模式
     * @return 唯一实例
     */
    public synchronized static HttpManager getInstance() {

        if (mInstance == null) {
            mInstance = new HttpManager();
        }
        return mInstance;
    }

    /**
     * rxjava 发起网络请求
     * @param observable 请求
     * @param subscriber 回调
     */
    public void doHttp(Observable observable, BaseObserver subscriber) {

                observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
