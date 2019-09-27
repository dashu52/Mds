package com.mds.wanandroid.mvp.presenter;

import com.mds.wanandroid.base.BasePresenterImpl;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.mvp.contract.SearchContract;
import com.mds.wanandroid.mvp.model.SearchModel;
import com.mds.wanandroid.ui.information.bean.MainListBean;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/26 20:38
 */
public class SearchPresenter extends BasePresenterImpl<SearchContract.IView> implements SearchContract.IPresenter {

    private SearchModel mModel = new SearchModel();

    @Override
    public void searchDetail(int page, String key,String type) {
        if(mView==null){
            return;
        }
        mView.getLoadDialog().show();
        mModel.searchDetail(page, key, mView.getTransformer(), new OnLoadDatasListener<MainListBean.DataBean>() {
            @Override
            public void onSuccess(MainListBean.DataBean dataBean) {
                mView.getLoadDialog().dismiss();
                mView.onLoadDetailList(dataBean,type);
            }

            @Override
            public void onFail(String error) {
                mView.getLoadDialog().dismiss();
                mView.onLoadDetailListFailure(error);
            }
        });
    }
}
