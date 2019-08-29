package com.mds.wanandroid.base;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 14:36
 */
public interface IBasePresenter<V extends IBaseView> {

    void attachView(V v);

    void detachView();

    V getView();
}
