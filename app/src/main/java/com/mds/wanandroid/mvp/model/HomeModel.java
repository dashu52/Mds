package com.mds.wanandroid.mvp.model;


import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.mds.wanandroid.ui.information.bean.MainListBean;

public class HomeModel {

    /***
     * 获取首页list
     * @param onLoadDatasListener
     */
    public void handleHomeList(int page, final OnLoadDatasListener<MainListBean.DataBean> onLoadDatasListener) {
        RetrofitFactory
                .getInstence()
                .getHomeList(page, new BaseObserver<MainListBean.DataBean>() {
                    @Override
                    protected void onSuccees(MainListBean.DataBean dataBean) throws Exception {

                        onLoadDatasListener.onSuccess(dataBean);

                    }

                    @Override
                    protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                        onLoadDatasListener.onFail(error);
                    }
                });
    }
}
