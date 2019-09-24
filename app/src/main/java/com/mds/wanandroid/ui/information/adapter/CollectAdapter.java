package com.mds.wanandroid.ui.information.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mds.wanandroid.R;
import com.mds.wanandroid.ui.information.bean.MyCollectBean;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/23 20:36
 */
public class CollectAdapter extends BaseQuickAdapter<MyCollectBean.DataBean.DatasBean, BaseViewHolder> {

    private List<MyCollectBean.DataBean.DatasBean> mDatasBeans;

    public CollectAdapter( @Nullable List<MyCollectBean.DataBean.DatasBean> data) {
        super(R.layout.item_my_collect, data);
        mDatasBeans = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCollectBean.DataBean.DatasBean item) {
        helper.setText(R.id.item_tv_name,item.getAuthor())
                .setText(R.id.item_tv_title,item.getTitle())
                .setText(R.id.item_tv_time,item.getNiceDate())
                .setText(R.id.item_tv_label,item.getChapterName())
                .addOnClickListener(R.id.btnDelete);
    }
}
