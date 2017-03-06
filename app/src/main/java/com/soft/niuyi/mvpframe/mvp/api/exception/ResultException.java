package com.soft.niuyi.mvpframe.mvp.api.exception;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 16：00
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public class ResultException extends RuntimeException {
    private int mErrCode;
    private String mErrMessage;

    public ResultException(int code, String msg) {
        mErrCode = code;
        mErrMessage = msg;
    }

    public int getErrCode() {
        return mErrCode;
    }

    public String getErrMessage() {
        return mErrMessage;
    }
}
