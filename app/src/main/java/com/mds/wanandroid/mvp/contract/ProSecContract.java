package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/25 15:06
 */
public interface ProSecContract {

    interface IView extends IBaseView{
        void onLoadProjectList(ProjectListBean.DataBean dataBean,String type);
        void onLoadProjectListFailure(String error);
        void onCollectIn(CurrencyBean.DataBean dataBean);
        void onCollectFailure(String error);
        void onCancelCollect(CurrencyBean.DataBean dataBean);
        void onCancelCollect(String error);
    }
    interface IPresenter extends IBasePresenter<IView>{
        void getProjectList(int page,String id,String type);
        void collectIn(String collectId);
        void cancelCollect(String collectId);
    }
}
