package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.ui.information.bean.MainListBean;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/26 20:33
 */
public interface SearchContract {

    interface IView extends IBaseView{
        void onLoadDetailList(MainListBean.DataBean dataBean,String type);
        void onLoadDetailListFailure(String error);
    }
    interface IPresenter extends IBasePresenter<IView>{
        void searchDetail(int page,String key,String type);
    }
}
