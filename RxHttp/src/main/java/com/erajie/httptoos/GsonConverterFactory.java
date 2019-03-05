package com.erajie.httptoos;


import com.google.gson.Gson;

/**
 * Gson格式数据的拦截工厂
 * @date  2018/9/29
 * @author EraJi
 */
public class GsonConverterFactory extends retrofit2.Converter.Factory {


    private final Gson gson;

    private GsonConverterFactory(Gson gson) {

        this.gson = gson;
    }

    /**
     * 最常使用的静态工厂方法，使用默认的Gson实例
     * @return 静态实例
     */
    public static GsonConverterFactory create() {

        return create(new Gson());
    }

    /**
     * 使用这个工厂方法可以从外部传入Gson对象，我们可以对这个Gson对象做很多配置
     * @param gson 外部传入Gson对象
     * @return 返回的json
     */
    public static GsonConverterFactory create(Gson gson) {

        return new GsonConverterFactory(gson);
    }

}
