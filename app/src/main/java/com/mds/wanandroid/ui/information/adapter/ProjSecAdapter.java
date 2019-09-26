package com.mds.wanandroid.ui.information.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mds.wanandroid.R;
import com.mds.wanandroid.common.APP;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
import com.mds.wanandroid.utils.GlideUtils;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/25 17:13
 */
public class ProjSecAdapter extends BaseQuickAdapter<ProjectListBean.DataBean.DatasBean, BaseViewHolder> {
    public ProjSecAdapter(@Nullable List<ProjectListBean.DataBean.DatasBean> data) {
        super(R.layout.item_project_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListBean.DataBean.DatasBean item) {
        helper.setText(R.id.item_tv_title,item.getTitle());
        helper.setText(R.id.item_tv_desc,item.getDesc());
        helper.setText(R.id.item_tv_name,item.getAuthor());
        helper.setText(R.id.item_tv_time,item.getNiceDate());
        helper.addOnClickListener(R.id.item_iv_collect);
        helper.setImageResource(R.id.item_iv_collect,item.isCollect()?R.mipmap.ic_star_select:R.mipmap.ic_star_normal);

        GlideUtils.loadImage(APP.getContext(),item.getEnvelopePic(),helper.getView(R.id.item_iv_img));

    }
}
