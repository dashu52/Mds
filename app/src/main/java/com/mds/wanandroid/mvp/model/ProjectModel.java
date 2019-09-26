package com.mds.wanandroid.mvp.model;

import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
import com.mds.wanandroid.ui.information.bean.ProjectTitleBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/24 20:28
 */
public class ProjectModel {

    public void getProjectTitle(LifecycleTransformer<Long> lifecycleTransformer, OnLoadDatasListener<List<ProjectTitleBean.DataBean>> loadDatasListener) {
        RetrofitFactory.getInstence().getProjectTitle(lifecycleTransformer,new BaseObserver<List<ProjectTitleBean.DataBean>>(){

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                loadDatasListener.onFail(error);
            }

            @Override
            protected void onSuccees(List<ProjectTitleBean.DataBean> dataBean) throws Exception {
                loadDatasListener.onSuccess(dataBean);
            }
        });
    }

    public void getProjectList(int page,String id,LifecycleTransformer<Long> lifecycleTransformer, OnLoadDatasListener<ProjectListBean.DataBean> loadDatasListener) {
        BaseObserver observer = new BaseObserver<ProjectListBean.DataBean>(){

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                loadDatasListener.onFail(error);
            }

            @Override
            protected void onSuccees(ProjectListBean.DataBean dataBean) throws Exception {
                loadDatasListener.onSuccess(dataBean);
            }
        };
        RetrofitFactory.getInstence().getProjectList(page,id,lifecycleTransformer,observer);
    }

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
}
