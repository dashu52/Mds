package com.mds.wanandroid.mvp.model;


import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.http.BaseObserver;
import com.mds.wanandroid.http.RetrofitFactory;
import com.mds.wanandroid.ui.information.bean.BannerBean;

import java.util.List;

public class BannerModel {



    public void handleBanner(final OnLoadDatasListener<List<BannerBean.DataBean>> onLoadDatasListener) {
        RetrofitFactory.getInstence().getBanner(new BaseObserver<List<BannerBean.DataBean>>() {
            @Override
            protected void onSuccees(List<BannerBean.DataBean> dataBeans) throws Exception {
                onLoadDatasListener.onSuccess(dataBeans);

            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                onLoadDatasListener.onFail(error);

            }
        });
    }
}
