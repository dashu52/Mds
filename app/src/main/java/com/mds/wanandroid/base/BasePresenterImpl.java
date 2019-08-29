package com.mds.wanandroid.base;

import java.lang.ref.WeakReference;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 14:40
 */
public class BasePresenterImpl<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;
    private WeakReference<V> mWeakReference ;

    @Override
    public void attachView(V v) {
        if(v!=null){
            mWeakReference = new WeakReference(v);
            mView = mWeakReference.get();
        }
    }

    @Override
    public void detachView() {
        if(mWeakReference!=null){
            mWeakReference.clear();
            mWeakReference=null;
            mView=null;
        }
    }

    @Override
    public V getView() {
            return mView;
    }
}
