package com.mds.wanandroid.mvp.model;

import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.mds.wanandroid.ui.information.bean.MyCollectBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/23 16:05
 */
public class CollectModel {

    public void collectIn(String id, LifecycleTransformer<Long> lifecycleTransformer, OnLoadDatasListener<CurrencyBean.DataBean> loadDatasListener){
        RetrofitFactory.getInstence().collectIn(id,lifecycleTransformer,new BaseObserver<CurrencyBean.DataBean>(){

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

    public void cancelCollect(String id, LifecycleTransformer<Long> lifecycleTransformer, OnLoadDatasListener<CurrencyBean.DataBean> loadDatasListener){
        RetrofitFactory.getInstence().cancelCollect(id,lifecycleTransformer,new BaseObserver<CurrencyBean.DataBean>(){

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
    public void getMyCollect(int page, LifecycleTransformer<Long> lifecycleTransformer, OnLoadDatasListener<MyCollectBean.DataBean> loadDatasListener){
        BaseObserver observer = new BaseObserver<MyCollectBean.DataBean>(){

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                loadDatasListener.onFail(error);
            }

            @Override
            protected void onSuccees(MyCollectBean.DataBean dataBean) throws Exception {
                loadDatasListener.onSuccess(dataBean);
            }
        };
        RetrofitFactory.getInstence().getMyCollect(page,lifecycleTransformer,observer);
    }
}
