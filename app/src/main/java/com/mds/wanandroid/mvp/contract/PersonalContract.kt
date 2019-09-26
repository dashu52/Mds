package com.mds.wanandroid.mvp.contract

import com.mds.wanandroid.base.IBasePresenter
import com.mds.wanandroid.base.IBaseView
import com.mds.wanandroid.bean.CurrencyBean
import com.trello.rxlifecycle2.LifecycleTransformer

/**

@author duanjianlin
@description:
@date : 19/09/18 17:24
 */
interface PersonalContract {

    interface IView : IBaseView{
        /***
         * 登出成功
         */
        fun loginOutSuccess(dataBean: CurrencyBean.DataBean?)

        /***
         * 登出失败
         */
        fun LoginOutFail()

    }

    interface IPersenter : IBasePresenter<IView>{

        fun loginOut()
    }

}