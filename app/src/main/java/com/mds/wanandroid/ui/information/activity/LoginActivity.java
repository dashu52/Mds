package com.mds.wanandroid.ui.information.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mds.wanandroid.R;
import com.mds.wanandroid.base.BaseActivity;
import com.mds.wanandroid.base.proxy.DynamicProxyHandler;
import com.mds.wanandroid.base.proxy.Interface;
import com.mds.wanandroid.base.proxy.RealObject;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.bean.Person;
import com.mds.wanandroid.common.MainActivity;
import com.mds.wanandroid.mvp.contract.LoginContract;
import com.mds.wanandroid.mvp.presenter.LoginPresenter;
import com.mds.wanandroid.utils.CommonUtil;
import com.mds.wanandroid.utils.MyLogger;
import com.mds.wanandroid.utils.SpUtils;
//import com.tinkerpatch.sdk.TinkerPatch;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoginActivity extends BaseActivity<LoginContract.IView,LoginContract.ILoginPresenter> implements LoginContract.IView, View.OnClickListener{

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private AppCompatTextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealObject real = new RealObject();
        Interface proxy = (Interface) new DynamicProxyHandler().bind(real);

        proxy.doSomething();
        proxy.somethingElse("luoxn28");
    }


    @Override
    protected LoginContract.ILoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void loginSuccess(CurrencyBean.DataBean dataBean) {
        MyLogger.dLog().d("loginSuccess="+dataBean);
        SpUtils.SetConfigString("username",etUsername.getText().toString());
        LoginActivity.startMain(this);
    }

    @Override
    public void loginFail(String msg) {
        MyLogger.dLog().d("loginFail="+msg);
    }


    @Override
    public String getUserName() {
        return etUsername.getText().toString();
    }

    @Override
    public String getPwd() {
        return etPassword.getText().toString();
    }

    @Override
    public LifecycleTransformer<Long> getTransformer() {
        return this.bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_login){
//            mPresenter.handleLoginIn();
//            TinkerPatch.with().fetchPatchUpdate(true);
        }
        else if (v.getId()==R.id.tv_register){
            LoginActivity.startRegister(this);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    public static   void   startMain(Context context){
        Intent intent=new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }

    public static   void   startRegister(Context context){
        Intent intent=new Intent(context, RegisterActivity.class);
        context.startActivity(intent);

    }
}
