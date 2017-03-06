package com.soft.niuyi.mvpframe.mvp.presenter;

import com.soft.niuyi.mvpframe.entity.UserInfoEntity;
import com.soft.niuyi.mvpframe.mvp.api.http.HttpResultSubscriber;
import com.soft.niuyi.mvpframe.mvp.contract.LoginContract;
import com.soft.niuyi.mvpframe.mvp.model.LoginModel;

import rx.Subscription;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：20
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public class LoginPresenter extends LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void loginIn(String username, String password) {
        Subscription subscription = mModel.loginIn(username, password)
                .subscribe(new HttpResultSubscriber<UserInfoEntity>() {
                    @Override
                    public void onBegin() {
                        mView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(UserInfoEntity userInfoEntity) {
                        //这是网络请求成功的回调
                        mView.onLoginSucceed(userInfoEntity);
                    }

                    @Override
                    public void onError(int status, String errorMsg) {
                        //这是失败的回调 根据status做具体的操作
                        mView.onLoginFailed(errorMsg);
                    }

                    @Override
                    public void onFinish() {
                        mView.hideLoadingDialog();
                    }

                });

        addSubscribe(subscription);

    }
}
