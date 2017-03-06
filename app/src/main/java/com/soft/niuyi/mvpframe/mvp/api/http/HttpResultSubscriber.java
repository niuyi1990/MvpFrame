package com.soft.niuyi.mvpframe.mvp.api.http;

import com.soft.niuyi.mvpframe.mvp.api.exception.ResultException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：10
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResultEntity<T>> {

    @Override
    public void onStart() {
        super.onStart();
        onBegin();
    }

    @Override
    public void onNext(HttpResultEntity<T> t) {
        if (t.isSuccess()) {
            onSuccess(t.getResult());
        } else {
            onError(t.getCode(), t.getMsg());
        }
    }

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //在这里做全局的错误处理
        if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof TimeoutException) {//网络错误
            onError(-9999, "网络异常");
        } else if (e instanceof ResultException) {
            //自定义的ResultException
            //由于返回200,300返回格式不统一的问题，自定义GsonResponseBodyConverter凡是300的直接抛异常
            onError(((ResultException) e).getErrCode(), ((ResultException) e).getErrMessage());
            System.out.println("---------errorCode------->" + ((ResultException) e).getErrCode());
            System.out.println("---------errorCode------->" + ((ResultException) e).getErrMessage());
        }
    }


    public abstract void onBegin();

    public abstract void onFinish();

    public abstract void onSuccess(T t);

    public abstract void onError(int status, String errorMsg);
}
