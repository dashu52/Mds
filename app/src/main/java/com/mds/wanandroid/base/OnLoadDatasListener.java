package com.mds.wanandroid.base;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 15:07
 */
public interface OnLoadDatasListener<T> {

    void onSuccess(T t);

    void onFail(String error);
}
