package com.mds.wanandroid.mvp.model;

import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/14 19:48
 */
public class RegisterModel {

    public void handleRegisterUser(String userName, String pwd,String rePwd, LifecycleTransformer<Long> lifecycleTransformer, final OnLoadDatasListener<CurrencyBean.DataBean> loadDatasListener){
        RetrofitFactory.getInstence().register(userName,pwd,rePwd,lifecycleTransformer,new BaseObserver<CurrencyBean.DataBean>() {
            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                loadDatasListener.onFail(error);
            }

            @Override
            protected void onSuccees(CurrencyBean.DataBean dataBean) throws Exception {
                loadDatasListener.onSuccess(dataBean);
            }
        });
    }
}
