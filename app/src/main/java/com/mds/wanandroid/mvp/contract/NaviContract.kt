package com.mds.wanandroid.mvp.contract

import com.mds.wanandroid.base.IBasePresenter
import com.mds.wanandroid.base.IBaseView
import com.mds.wanandroid.ui.information.bean.NavigateBean

/**

@author duanjianlin
@description:
@date : 19/09/26 13:59
 */
interface NaviContract {

    interface IView: IBaseView{
        fun onLoadNaviList(dataBean: List<NavigateBean.DataBean>?)
        fun onLoadNaviListFailure(error:String?)
    }

    interface IPresenter:IBasePresenter<IView>{
        fun getNavigateList()
    }
}