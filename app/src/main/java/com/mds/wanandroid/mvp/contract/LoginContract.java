package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.http.BaseResponse;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 15:01
 */
public interface LoginContract {

    interface IView extends IBaseView{
        void loginSuccess(CurrencyBean.DataBean dataBean);
        void loginFail(String msg);
        String getUserName();
        String getPwd();
    }

    interface ILoginPresenter extends IBasePresenter<IView>{
        void handleLoginIn();
    }
}
