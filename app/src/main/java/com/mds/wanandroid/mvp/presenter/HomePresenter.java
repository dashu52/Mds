package com.mds.wanandroid.mvp.presenter;

import com.mds.wanandroid.base.BasePresenterImpl;
import com.mds.wanandroid.base.OnLoadDatasListener;
import com.mds.wanandroid.mvp.contract.HomeContract;
import com.mds.wanandroid.mvp.model.BannerModel;
import com.mds.wanandroid.mvp.model.HomeModel;
import com.mds.wanandroid.ui.information.bean.BannerBean;
import com.mds.wanandroid.ui.information.bean.MainListBean;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/29 10:05
 */
public class HomePresenter extends BasePresenterImpl<HomeContract.IView> implements HomeContract.IPresenter{
    private HomeModel homeModel;

    private BannerModel bannerModel;

    public HomePresenter(){
        homeModel = new HomeModel();
        bannerModel = new BannerModel();
    }

    @Override
    public void getHomeList(String type) {
        HomeContract.IView view = getView();
        if(view==null){
            return;
        }

        homeModel.handleHomeList(view.getPage(), new OnLoadDatasListener<MainListBean.DataBean>() {
            @Override
            public void onSuccess(MainListBean.DataBean dataBean) {
                view.cancelLoadDialog();
                view.getMainListSuccess(dataBean,type);
            }

            @Override
            public void onFail(String error) {
                if (type.equals("首次加载")){
                    view.cancelLoadDialog();
                }
                view.getMainListFail();
            }
        });
    }

    @Override
    public void getBanner() {
        if (mView == null) return;
        bannerModel.handleBanner(new OnLoadDatasListener<List<BannerBean.DataBean>>() {
            @Override
            public void onSuccess(List<BannerBean.DataBean> dataBeans) {
                mView.getBannerSuccess(dataBeans);
            }

            @Override
            public void onFail(String error) {
                mView.getBannerFail();

            }
        });
    }
}
