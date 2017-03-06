package com.soft.niuyi.mvpframe.mvp.api.http;

import com.soft.niuyi.mvpframe.base.BaseEntity;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：09
 * 邮箱：niuyi19900923@gmail.com
 * 描述：请求返回的数据最外层封装
 */
public class HttpResultEntity<T> extends BaseEntity {

    public int code;
    private boolean isSuccess;
    private T result;
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpResultEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public HttpResultEntity setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public T getResult() {
        return result;
    }


    public void setResult(T result) {
        this.result = result;

    }

    public boolean isSuccess() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }
}
