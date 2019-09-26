package com.mds.wanandroid.mvp.presenter;

import com.mds.wanandroid.base.BasePresenterImpl;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.mvp.contract.ProSecContract;
import com.mds.wanandroid.mvp.model.ProjectModel;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/25 15:07
 */
public class ProSecPresenter extends BasePresenterImpl<ProSecContract.IView> implements ProSecContract.IPresenter {
    private ProjectModel mModel = new ProjectModel();
    @Override
    public void getProjectList(int page, String id,String type) {
        if(mView==null){
            return;
        }
//        mView.getLoadDialog().show();
        mModel.getProjectList(page, id, mView.getTransformer(), new OnLoadDatasListener<ProjectListBean.DataBean>() {
            @Override
            public void onSuccess(ProjectListBean.DataBean dataBean) {
//                mView.getLoadDialog().dismiss();
                mView.onLoadProjectList(dataBean,type);
            }

            @Override
            public void onFail(String error) {
//                mView.getLoadDialog().dismiss();
                mView.onLoadProjectListFailure(error);
            }
        });
    }

    @Override
    public void collectIn(String id) {
        if(mView==null){
            return;
        }
        mView.getLoadDialog().show();
        mModel.collectIn(id, mView.getTransformer(), new OnLoadDatasListener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                mView.getLoadDialog().dismiss();
                mView.onCollectIn(dataBean);
            }

            @Override
            public void onFail(String error) {
                mView.getLoadDialog().dismiss();
                mView.onCollectFailure(error);
            }
        });
    }

    @Override
    public void cancelCollect(String id) {
        if(mView==null){
            return;
        }
        mView.getLoadDialog().show();
        mModel.cancelCollect(id, mView.getTransformer(), new OnLoadDatasListener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                mView.getLoadDialog().dismiss();
                mView.onCancelCollect(dataBean);
            }

            @Override
            public void onFail(String error) {
                mView.getLoadDialog().dismiss();
                mView.onCollectFailure(error);
            }
        });
    }
}
