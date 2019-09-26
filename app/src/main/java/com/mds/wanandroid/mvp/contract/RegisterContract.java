package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.bean.CurrencyBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 15:01
 */
public interface RegisterContract {

    interface IView extends IBaseView{
        void registerSuccess(CurrencyBean.DataBean dataBean);
        void registerFail(String msg);
        String getUserName();
        String getPwd1();
        String getPwd2();
    }

    interface IRegisterPresenter extends IBasePresenter<IView>{
        void handleRegisterUser();
    }
}
