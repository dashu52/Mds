package com.mds.wanandroid.base;

import android.app.Dialog;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 14:35
 */
public interface IBaseView {

    Dialog getLoadDialog();

    void cancelLoadDialog();
}
