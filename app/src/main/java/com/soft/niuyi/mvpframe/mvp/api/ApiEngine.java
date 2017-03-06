package com.soft.niuyi.mvpframe.mvp.api;

import com.soft.niuyi.mvpframe.application.MyApplication;
import com.soft.niuyi.mvpframe.constant.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：03
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public class ApiEngine {

    private volatile static ApiEngine apiEngine;
    private Retrofit retrofit;

    // 设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    // 30秒内直接读缓存
    private static final long CACHE_AGE_SEC = 0;

    private ApiEngine() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(MyApplication.mLogLevel);

        //缓存
//        int size = 1024 * 1024 * 100;
//        File cacheFile = new File(Constant.CACHE_PATH, "OkHttpCache");
//        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
//                .addNetworkInterceptor(mCacheControlInterceptor)
//                .addInterceptor(mCacheControlInterceptor)
                .addInterceptor(loggingInterceptor)
//                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }

    // 配置缓存
//    private Interceptor mCacheControlInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            Logger.e("--------------------------------------------缓存----------------------------------------------------");
//            // 在这里统一配置请求头缓存策略以及响应头缓存策略
//            if (NetworkUtil.isNetworkConnected(MyApplication.getContext())) {
//                Logger.e("--------------------------------------------有网络----------------------------------------------------");
//                // 在有网的情况下CACHE_AGE_SEC秒内读缓存，大于CACHE_AGE_SEC秒后会重新请求数据
//                request = request
//                        .newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, max-age=" + CACHE_AGE_SEC)
//                        .build();
//
//                Response response = chain.proceed(request);
//
//                return response
//                        .newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, max-age=" + CACHE_AGE_SEC)
//                        .build();
//            } else {
//                Logger.e("--------------------------------------------无网络----------------------------------------------------");
//                // 无网情况下CACHE_STALE_SEC秒内读取缓存，大于CACHE_STALE_SEC秒缓存无效报504
//                request = request
//                        .newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
//                        .build();
//
//                Response response = chain.proceed(request);
//
//                return response
//                        .newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
//                        .build();
//            }
//
//        }
//    };
}