package com.mds.wanandroid.mvp.presenter;

import com.mds.wanandroid.base.BasePresenterImpl;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.mvp.contract.LoginContract;
import com.mds.wanandroid.mvp.model.LoginModel;
import com.mds.wanandroid.utils.MyLogger;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 15:09
 */
public class LoginPresenter extends BasePresenterImpl<LoginContract.IView> implements LoginContract.ILoginPresenter {

    private LoginModel mModel = new LoginModel();
    @Override
    public void handleLoginIn() {
        final LoginContract.IView view = getView();
        if(view==null){
            MyLogger.dLog().e(view+" is detach ,so return");
            return;
        }
        view.getLoadDialog().show();
        mModel.handleLoginIn(view.getUserName(), view.getPwd(),view.getTransformer(), new OnLoadDatasListener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                if(view==null){
                    MyLogger.dLog().e(view+" is detach ,so return");
                    return;
                }
                view.loginSuccess(dataBean);
                view.cancelLoadDialog();
            }

            @Override
            public void onFail(String error) {
                if(view==null){
                    MyLogger.dLog().e(view+" is detach ,so return");
                    return;
                }
                view.loginFail(error);
                view.cancelLoadDialog();
            }
        });
    }

}
