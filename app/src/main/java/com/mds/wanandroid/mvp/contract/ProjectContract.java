package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
import com.mds.wanandroid.ui.information.bean.ProjectTitleBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/24 20:36
 */
public interface ProjectContract {

    interface IView extends IBaseView{
        void onLoadTitleList(List<ProjectTitleBean.DataBean> dataBean);
        void onLoadTitleListFailure(String error);
        void onLoadProjectList(ProjectListBean.DataBean dataBean);
        void onLoadProjectListFailure(String error);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void getProjectTitle();
        void getProjectList(int page,String id);
    }
}
