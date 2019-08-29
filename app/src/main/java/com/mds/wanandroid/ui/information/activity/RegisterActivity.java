package com.mds.wanandroid.ui.information.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mds.wanandroid.R;
import com.mds.wanandroid.base.BaseActivity;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.mvp.contract.LoginContract;
import com.mds.wanandroid.mvp.contract.RegisterContract;
import com.mds.wanandroid.mvp.presenter.RegisterPresenter;
import com.mds.wanandroid.utils.SpUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.ConcurrentHashMap;

public class RegisterActivity extends BaseActivity<RegisterContract.IView,RegisterContract.IRegisterPresenter> implements RegisterContract.IView, View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordAgain;
    private Button btnRegister;
    private LinearLayout llLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RegisterContract.IRegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ll_login){
            finish();
        }

        else if (v.getId()==R.id.btn_register){
            mPresenter.handleRegisterUser();

        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPasswordAgain = findViewById(R.id.et_password_again);
        btnRegister = findViewById(R.id.btn_register);
        llLogin = findViewById(R.id.ll_login);

        llLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void registerSuccess(CurrencyBean.DataBean dataBean) {
        SpUtils.SetConfigString("username",etUsername.getText().toString());
        LoginActivity.startMain(this);
        finish();
    }

    @Override
    public void registerFail(String msg) {

    }

    @Override
    public String getUserName() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPwd1() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public String getPwd2() {
        return etPasswordAgain.getText().toString().trim();
    }

    @Override
    public LifecycleTransformer<Long> getTransformer() {
        return this.bindUntilEvent(ActivityEvent.DESTROY);
    }
}
