package com.soft.niuyi.mvpframe.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.soft.niuyi.mvpframe.MainActivity;

import okhttp3.logging.HttpLoggingInterceptor;


/**
 * 作者：${牛毅} on 2016/7/4 16:15
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyApplication extends Application {

    public static HttpLoggingInterceptor.Level mLogLevel;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init().logLevel(LogLevel.FULL);//初始化log
        mLogLevel = HttpLoggingInterceptor.Level.BODY;//OkHttp请求log
        Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());//全局异常捕获处理
    }

    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 未能捕获的异常处理
     */
    class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();

            Intent intent = new Intent(MyApplication.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApplication.this.startActivity(intent);

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);//非正常退出
        }
    }
}
