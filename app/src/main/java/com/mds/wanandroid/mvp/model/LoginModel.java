package com.mds.wanandroid.mvp.model;

import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.mds.wanandroid.utils.MyLogger;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 15:15
 */
public class LoginModel {

    public void handleLoginIn(String userName, String pwd, LifecycleTransformer<Long> lifecycleTransformer, final OnLoadDatasListener<CurrencyBean.DataBean> loadDatasListener){
        RetrofitFactory.getInstence().login(userName, pwd, lifecycleTransformer,new BaseObserver<CurrencyBean.DataBean>() {
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
    public void handleLoginOut(OnLoadDatasListener loadDatasListener){
    }
}
