package com.mds.wanandroid.mvp.contract

import com.mds.wanandroid.base.IBasePresenter
import com.mds.wanandroid.base.IBaseView

/**

@author duanjianlin
@description:
@date : 19/09/23 15:03
 */
interface AboutContract {

    interface IView : IBaseView{

    }

    interface IPresenter : IBasePresenter<IView>{

    }
}