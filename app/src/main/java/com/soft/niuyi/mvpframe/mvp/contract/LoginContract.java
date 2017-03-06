package com.soft.niuyi.mvpframe.mvp.contract;

import com.soft.niuyi.mvpframe.base.BaseModel;
import com.soft.niuyi.mvpframe.base.BasePresenter;
import com.soft.niuyi.mvpframe.base.BaseView;
import com.soft.niuyi.mvpframe.entity.UserInfoEntity;
import com.soft.niuyi.mvpframe.mvp.api.http.HttpResultEntity;

import rx.Observable;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：16
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public interface LoginContract {

    interface View extends BaseView {
        void showLoadingDialog();

        void hideLoadingDialog();

        void onLoginSucceed(UserInfoEntity userInfoEntity);

        void onLoginFailed(String err);
    }

    interface Model extends BaseModel {
        Observable<HttpResultEntity<UserInfoEntity>> loginIn(String username, String password);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loginIn(String username, String password);
    }
}
