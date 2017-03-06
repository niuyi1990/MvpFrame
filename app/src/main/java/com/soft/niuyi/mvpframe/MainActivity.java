package com.soft.niuyi.mvpframe;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.soft.niuyi.mvpframe.base.BaseActivity;
import com.soft.niuyi.mvpframe.entity.UserInfoEntity;
import com.soft.niuyi.mvpframe.mvp.contract.LoginContract;
import com.soft.niuyi.mvpframe.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    private ProgressDialog mDialog;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View view) {
        mDialog = new ProgressDialog(this);
        mDialog.setIndeterminate(true);
        mDialog.setMessage("登录中...");
    }

    @Override
    protected void setListener(Context mContext) {

    }

    @Override
    protected void toDo(Context mContext) {

    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        mPresenter.loginIn("niuyi", "123456");
    }

    @Override
    public void showLoadingDialog() {
        mDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        mDialog.hide();
    }

    @Override
    public void onLoginSucceed(UserInfoEntity userInfoEntity) {

    }

    @Override
    public void onLoginFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
