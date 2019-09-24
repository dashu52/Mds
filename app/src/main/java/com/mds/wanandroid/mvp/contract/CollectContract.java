package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.ui.information.bean.MyCollectBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/23 16:15
 */
public interface CollectContract {

    interface IView extends IBaseView{

        void onCancelCollectSuccess(int position);
        void onCancelCollectFail(String error);

        void getCollectSuccess(MyCollectBean.DataBean dataBean,String type);
        void getCollectFail(String error);

        int getCurrentPage();
        LifecycleTransformer<Long> getTransformer();
    }

    interface IPresenter extends IBasePresenter<IView>{
        void cancelCollect(int position,String id);
        void getCollectData(String type);
    }
}
