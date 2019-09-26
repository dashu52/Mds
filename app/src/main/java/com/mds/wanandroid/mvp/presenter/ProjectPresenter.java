package com.mds.wanandroid.mvp.presenter;

import com.mds.wanandroid.base.BasePresenterImpl;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.mvp.contract.ProjectContract;
import com.mds.wanandroid.mvp.model.ProjectModel;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
import com.mds.wanandroid.ui.information.bean.ProjectTitleBean;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/24 20:43
 */
public class ProjectPresenter extends BasePresenterImpl<ProjectContract.IView> implements ProjectContract.IPresenter {

    private ProjectModel mModel = new ProjectModel();

    @Override
    public void getProjectTitle() {
        if(mView == null){
            return;
        }
        mView.getLoadDialog().show();
        mModel.getProjectTitle(mView.getTransformer(), new OnLoadDatasListener<List<ProjectTitleBean.DataBean>>() {
            @Override
            public void onSuccess(List<ProjectTitleBean.DataBean> dataBean) {
                mView.getLoadDialog().dismiss();
                mView.onLoadTitleList(dataBean);
            }

            @Override
            public void onFail(String error) {
                mView.getLoadDialog().dismiss();
                mView.onLoadProjectListFailure(error);
            }
        });
    }

    @Override
    public void getProjectList(int page,String id) {
        if(mView == null){
            return;
        }
        mView.getLoadDialog().show();
        OnLoadDatasListener loadDatasListener = new OnLoadDatasListener<ProjectListBean.DataBean>() {
            @Override
            public void onSuccess(ProjectListBean.DataBean dataBean) {
                mView.getLoadDialog().dismiss();
                mView.onLoadProjectList(dataBean);
            }

            @Override
            public void onFail(String error) {
                mView.getLoadDialog().dismiss();
                mView.onLoadProjectListFailure(error);
            }
        };
        mModel.getProjectList(page,id,mView.getTransformer(), loadDatasListener);
    }
}
