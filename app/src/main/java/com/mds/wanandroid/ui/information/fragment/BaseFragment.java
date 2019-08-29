package com.mds.wanandroid.ui.information.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mds.wanandroid.base.IBasePresenter;
import com.mds.wanandroid.base.IBaseView;
import com.mds.wanandroid.utils.DialogUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/28 17:35
 */
public abstract class BaseFragment<V extends IBaseView,P extends IBasePresenter> extends RxFragment implements IBaseView{

    protected P presenter;
    protected View rootView;// 缓存Fragment view
    protected Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getContext()).inflate(getLayout(),null);
        ViewGroup parentView = (ViewGroup)rootView.getParent();
        if(parentView!=null){
            parentView.removeView(rootView);
        }
        presenter = createPresenter();
        presenter.attachView(this);
        dialog = DialogUtils.createLoadingDialog(getActivity(), "请稍后...");
        initView();
        initData(savedInstanceState);
        return rootView;
    }

    protected abstract P createPresenter();
    protected abstract int getLayout();
    protected abstract void initView();
    protected abstract void initData(Bundle savedInstanceState);
    @Override
    public void onDestroy() {
        if(presenter!=null){
            presenter.detachView();
        }
        super.onDestroy();
    }
}
