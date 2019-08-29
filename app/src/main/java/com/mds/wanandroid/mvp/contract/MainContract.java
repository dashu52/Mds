package com.mds.wanandroid.mvp.contract;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/28 17:19
 */
public interface MainContract {

    interface IView extends IBaseView{

    }
    interface IPresenter extends IBasePresenter<IView>{

    }
}
