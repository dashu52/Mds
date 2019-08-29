package com.mds.wanandroid.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.mds.wanandroid.utils.DialogUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 14:50
 */
public abstract class BaseActivity<V extends IBaseView,P extends IBasePresenter<V>> extends RxAppCompatActivity implements IBaseView,IActivity{

    protected P mPresenter;

    protected Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ImmersionBar.with(this).init();
        if(mPresenter==null){
            mPresenter = createPresenter();
        }
        mPresenter.attachView((V) this);
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        initView();
        initData( savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }

    protected abstract P createPresenter();

    @Override
    public Dialog getLoadDialog() {
        return dialog;
    }

    @Override
    public void cancelLoadDialog() {
        dialog.cancel();
    }
}
