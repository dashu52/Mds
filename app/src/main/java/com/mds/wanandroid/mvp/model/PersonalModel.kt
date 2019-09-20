package com.mds.wanandroid.mvp.model

import com.mds.wanandroid.base.OnLoadDatasListener
import com.mds.wanandroid.bean.CurrencyBean
import com.mds.wanandroid.http.BaseObserver
import com.mds.wanandroid.http.RetrofitFactory
import com.mds.wanandroid.utils.MyLogger
import com.trello.rxlifecycle2.LifecycleTransformer

class PersonalModel {

    fun loginOut(lifecycleTransformer:LifecycleTransformer<Long> ,loadDatasListener:OnLoadDatasListener<CurrencyBean.DataBean> ){
        val observer = object : BaseObserver<CurrencyBean.DataBean>() {
            @Throws(Exception::class)
            override fun onFailure(error: String, isNetWorkError: Boolean) {
                loadDatasListener.onFail(error)
            }

            @Throws(Exception::class)
            override fun onSuccees(dataBean: CurrencyBean.DataBean?) {
                MyLogger.dLog().e("onSuccees")
                loadDatasListener.onSuccess(dataBean)
            }
        }
        RetrofitFactory.getInstence().loginOut(lifecycleTransformer,observer)
    }
}