package com.mds.wanandroid.mvp.model;

import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.mds.wanandroid.ui.information.bean.NavigateBean;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/26 11:39
 */
public class NaviModel {


    public void getNavigateList(LifecycleTransformer<Long> lifecycleTransformer, OnLoadDatasListener<List<NavigateBean.DataBean>> loadDatasListener){
        RetrofitFactory.getInstence().getNavigateList(lifecycleTransformer, new BaseObserver<List<NavigateBean.DataBean>>() {
            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                loadDatasListener.onFail(error);
            }

            @Override
            protected void onSuccees(List<NavigateBean.DataBean> dataBean) throws Exception {
                loadDatasListener.onSuccess(dataBean);
            }
        });
    }
}
