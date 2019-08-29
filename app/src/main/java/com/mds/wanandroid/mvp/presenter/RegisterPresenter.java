package com.mds.wanandroid.mvp.presenter;

import com.mds.wanandroid.base.BasePresenterImpl;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.mvp.contract.LoginContract;
import com.mds.wanandroid.mvp.contract.RegisterContract;
import com.mds.wanandroid.mvp.model.RegisterModel;
import com.mds.wanandroid.utils.CommonUtil;
import com.mds.wanandroid.utils.MyLogger;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/14 19:47
 */
public class RegisterPresenter extends BasePresenterImpl<RegisterContract.IView> implements RegisterContract.IRegisterPresenter {

    private RegisterModel mRegisterModel = new RegisterModel();

    @Override
    public void handleRegisterUser() {
        final RegisterContract.IView view = getView();
        if(view==null){
            MyLogger.dLog().e("view==null ,return");
            return;
        }
        final String userName = view.getUserName();
        final String pwd1 = view.getPwd1();
        final String pwd2 = view.getPwd2();
        if(userName==null||"".equals(userName)){
            CommonUtil.showToast("用户名不能为空！");
            return;
        }
        if((pwd1==null||"".equals(pwd1))||(pwd2==null||"".equals(pwd2))){
            CommonUtil.showToast("密码不能为空！");
            return;
        }
        if(!pwd1.equals(pwd2)){
            CommonUtil.showToast("密码不一致！");
            return;
        }
        mRegisterModel.handleRegisterUser(userName,pwd1,pwd2,view.getTransformer(),new OnLoadDatasListener<CurrencyBean.DataBean>(){

            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                if(view!=null){
                    view.registerSuccess(dataBean);
                }
            }

            @Override
            public void onFail(String error) {
                if(view!=null){
                    view.registerFail(error);
                }
            }
        });
    }
}
