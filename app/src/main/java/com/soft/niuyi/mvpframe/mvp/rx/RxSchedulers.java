package com.soft.niuyi.mvpframe.mvp.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Rxjava线程管理类
 * 作者：${牛毅} on 2016/11/24 10:18
 * 邮箱：niuyi19900923@hotmail.com
 */
public class RxSchedulers {

    public static <T> Observable.Transformer<T, T> switchThread() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()); //回到主线程去处理请求结果
            }
        };
    }
}
