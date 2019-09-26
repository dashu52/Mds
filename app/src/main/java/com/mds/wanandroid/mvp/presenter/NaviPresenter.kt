package com.mds.wanandroid.mvp.presenter

import com.mds.wanandroid.base.BasePresenterImpl
import com.mds.wanandroid.base.IBasePresenter
import com.mds.wanandroid.base.OnLoadDatasListener
import com.mds.wanandroid.mvp.contract.NaviContract
import com.mds.wanandroid.mvp.model.NaviModel
import com.mds.wanandroid.ui.information.bean.NavigateBean
import com.mds.wanandroid.utils.MyLogger

/**

@author duanjianlin
@description:
@date : 19/09/26 14:03
 */
class NaviPresenter : BasePresenterImpl<NaviContract.IView>(),NaviContract.IPresenter {

    val mModel = NaviModel()

    override fun getNavigateList() {
        if(mView==null){
            MyLogger.dLog().d("mView==null,return")
            return;
        }
        val listener = object : OnLoadDatasListener<List<NavigateBean.DataBean>>{
            override fun onSuccess(t: List<NavigateBean.DataBean>?) {
                mView?.cancelLoadDialog()
                mView?.onLoadNaviList(t)
            }
            override fun onFail(error: String?) {
                mView?.cancelLoadDialog()
                mView.onLoadNaviListFailure(error)
            }
        }
        mView?.loadDialog?.show()
        mModel.getNavigateList(mView.getTransformer(),listener)

    }
}