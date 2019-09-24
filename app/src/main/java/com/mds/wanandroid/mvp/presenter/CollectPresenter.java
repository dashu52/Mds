package com.mds.wanandroid.mvp.presenter;

import com.mds.wanandroid.base.BasePresenterImpl;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.mvp.contract.CollectContract;
import com.mds.wanandroid.mvp.model.CollectModel;
import com.mds.wanandroid.ui.information.bean.MyCollectBean;
import com.mds.wanandroid.utils.MyLogger;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/23 16:23
 */
public class CollectPresenter extends BasePresenterImpl<CollectContract.IView> implements CollectContract.IPresenter {

    private CollectModel mModel = new CollectModel();


    @Override
    public void cancelCollect(final int position,String id) {
        if(mView==null){
            MyLogger.dLog().e("mView==null return");
        }
        mView.getLoadDialog().show();
        mModel.cancelCollect(id,mView.getTransformer(),new OnLoadDatasListener<CurrencyBean.DataBean>(){

            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                mView.onCancelCollectSuccess(position);
                mView.getLoadDialog().dismiss();
            }

            @Override
            public void onFail(String error) {
                mView.onCancelCollectFail(error);
                mView.getLoadDialog().dismiss();
            }
        });
    }


    @Override
    public void getCollectData(final String type) {
        if(mView==null){
            MyLogger.dLog().e("mView==null return");
            return;
        }
        mView.getLoadDialog().show();
        OnLoadDatasListener loadDatasListener = new OnLoadDatasListener<MyCollectBean.DataBean>(){
            @Override
            public void onSuccess(MyCollectBean.DataBean dataBean) {
                mView.getCollectSuccess(dataBean,type);
                mView.getLoadDialog().dismiss();
            }

            @Override
            public void onFail(String error) {
                mView.getCollectFail(error);
                mView.getLoadDialog().dismiss();
            }
        };
        mModel.getMyCollect(mView.getCurrentPage(),mView.getTransformer(),loadDatasListener);
    }
}
