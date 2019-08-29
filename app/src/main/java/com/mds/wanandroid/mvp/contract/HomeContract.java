package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.ui.information.bean.BannerBean;
import com.mds.wanandroid.ui.information.bean.MainListBean;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/29 10:01
 */
public interface HomeContract {

    interface IView extends IBaseView{
        /***
         * 获取首页list成功
         */
        void getMainListSuccess(MainListBean.DataBean dataBean, String type);

        /***
         * 获取首页list失败
         */
        void getMainListFail();


        /***
         * 获取当前加载页数
         */
        int  getPage();


        /***
         * 获取首页banner成功
         */
        void getBannerSuccess(List<BannerBean.DataBean> dataBean);

        /***
         * 获取首页banner失败
         */
        void getBannerFail();

    }

    interface IPresenter extends IBasePresenter<IView>{
        /***
         * 获取首页list
         */
         void  getHomeList(final String type);

         void  getBanner();
    }
}
