package com.soft.niuyi.mvpframe.mvp.api.converter;

import com.google.gson.Gson;
import com.soft.niuyi.mvpframe.mvp.api.exception.ResultException;
import com.soft.niuyi.mvpframe.mvp.api.http.HttpResultEntity;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：50
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();
        //先将返回的json数据解析到HttpResult中，如果code==200，则解析到我们的实体基类中，否则抛异常
        HttpResultEntity httpResultEntity = gson.fromJson(response, HttpResultEntity.class);
        if (httpResultEntity.getCode() == 200) {
            //200的时候就直接解析，不可能出现解析异常。因为我们实体基类中传入的泛型，就是数据成功时候的格式
            return gson.fromJson(response, type);
        } else {
//            ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
            //抛一个自定义ResultException 传入失败时候的状态码，和信息
            throw new ResultException(httpResultEntity.getCode(), httpResultEntity.getMsg());
        }
    }
}
