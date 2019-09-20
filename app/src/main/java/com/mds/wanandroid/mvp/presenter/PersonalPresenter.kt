package com.mds.wanandroid.mvp.presenter

import com.mds.wanandroid.base.BasePresenterImpl
import com.mds.wanandroid.base.OnLoadDatasListener
import com.mds.wanandroid.bean.CurrencyBean
import com.mds.wanandroid.mvp.contract.PersonalContract
import com.mds.wanandroid.mvp.model.PersonalModel
import com.mds.wanandroid.utils.MyLogger

/**

@author duanjianlin
@description:
@date : 19/09/18 17:33
 */
class PersonalPresenter : BasePresenterImpl<PersonalContract.IView>(),PersonalContract.IPersenter{

    val mModel:PersonalModel

    init {
        mModel = PersonalModel()
    }
    override fun loginOut() {

        val listener = object : OnLoadDatasListener<CurrencyBean.DataBean> {
            override fun onFail(error: String?) {
                mView?.cancelLoadDialog()
                mView?.LoginOutFail()
            }

            override fun onSuccess(dataBean: CurrencyBean.DataBean?) {
                MyLogger.dLog().e("onSuccees2")
                mView?.cancelLoadDialog()
                mView?.loginOutSuccess(dataBean)
            }

        }
        mView?.loadDialog?.show()

        mView?.getTransformer()?.let { mModel.loginOut(it,listener) }
    }
}