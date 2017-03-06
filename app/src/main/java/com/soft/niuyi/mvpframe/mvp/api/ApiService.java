package com.soft.niuyi.mvpframe.mvp.api;

import com.soft.niuyi.mvpframe.constant.Constant;
import com.soft.niuyi.mvpframe.entity.UserInfoEntity;
import com.soft.niuyi.mvpframe.mvp.api.http.HttpResultEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：12
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public interface ApiService {

    @FormUrlEncoded
    @POST(Constant.LOGIN_URL)//此处可以替换baseurl
    Observable<HttpResultEntity<UserInfoEntity>> login(@Field("mobile") String phone, @Field("password") String pwd);
}
