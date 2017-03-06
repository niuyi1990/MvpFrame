package com.soft.niuyi.mvpframe.mvp.model;

import com.soft.niuyi.mvpframe.entity.UserInfoEntity;
import com.soft.niuyi.mvpframe.mvp.api.ApiEngine;
import com.soft.niuyi.mvpframe.mvp.api.http.HttpResultEntity;
import com.soft.niuyi.mvpframe.mvp.contract.LoginContract.Model;
import com.soft.niuyi.mvpframe.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：20
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public class LoginModel implements Model {

    @Override
    public Observable<HttpResultEntity<UserInfoEntity>> loginIn(String username, String password) {
        return ApiEngine
                .getInstance()
                .getApiService()
                .login(username, password)
                .compose(RxSchedulers.<HttpResultEntity<UserInfoEntity>>switchThread());
    }
}
