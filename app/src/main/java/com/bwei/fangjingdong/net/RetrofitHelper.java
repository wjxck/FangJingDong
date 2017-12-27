package com.bwei.fangjingdong.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：王建勋
 * 时间：2017-12-08 16:39
 * 类的用途：
 */

public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private static HttpUtils httpUtils;

    static {
        initOkHttpClient();
    }

    /**
     * 初始化OkHttpClient
     */
    private static void initOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (okHttpClient == null) {
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .build();
                }
            }
        }
    }

    /**
     * 定义一个泛型方法
     *
     * @param tClass
     * @param url
     * @param <T>
     * @return
     */
    public static <T> T createAPI(Class<T> tClass, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);
    }

    public static HttpUtils getHttpUtils() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = createAPI(HttpUtils.class, API.HOST);
                }
            }
        }
        return httpUtils;
    }
}
