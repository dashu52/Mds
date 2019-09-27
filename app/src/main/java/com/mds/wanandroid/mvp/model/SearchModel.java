package com.mds.wanandroid.mvp.model;

import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.mds.wanandroid.ui.information.bean.MainListBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/26 20:34
 */
public class SearchModel {

    public void searchDetail(int page, String K, LifecycleTransformer<Long> lifecycleTransformer, OnLoadDatasListener loadDatasListener){
        RetrofitFactory.getInstence().searchDetail(page,K,lifecycleTransformer,new BaseObserver<MainListBean.DataBean>(){

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                loadDatasListener.onFail(error);
            }

            @Override
            protected void onSuccees(MainListBean.DataBean dataBean) throws Exception {
                loadDatasListener.onSuccess(dataBean);
            }
        });
    }
}
