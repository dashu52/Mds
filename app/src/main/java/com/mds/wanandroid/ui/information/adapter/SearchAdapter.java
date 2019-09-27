package com.mds.wanandroid.ui.information.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mds.wanandroid.mvp.contract.SearchContract;
import com.mds.wanandroid.ui.information.bean.MainListBean;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/27 15:53
 */
public class SearchAdapter extends BaseQuickAdapter<MainListBean.DataBean.DatasBean , BaseViewHolder> {
    public SearchAdapter(int layoutResId, @Nullable List<MainListBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainListBean.DataBean.DatasBean item) {

    }
}
