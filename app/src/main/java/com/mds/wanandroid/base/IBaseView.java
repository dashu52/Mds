package com.mds.wanandroid.base;

import android.app.Dialog;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 14:35
 */
public interface IBaseView {

    Dialog getLoadDialog();

    void cancelLoadDialog();

    LifecycleTransformer<Long> getTransformer();
}
