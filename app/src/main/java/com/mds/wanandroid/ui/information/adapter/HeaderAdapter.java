package com.mds.wanandroid.ui.information.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mds.wanandroid.R;
import com.mds.wanandroid.ui.information.bean.NavigateBean;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/26 15:11
 */
public class HeaderAdapter extends BaseQuickAdapter<NavigateBean.DataBean, BaseViewHolder> {

    private List<NavigateBean.DataBean> mData;
    private int mCurPos=0;//默认是0
    public HeaderAdapter( @Nullable List<NavigateBean.DataBean> data) {
        super(R.layout.recyclerview_item_search_sort_left, data);
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigateBean.DataBean item) {
        helper.setText(R.id.tv_left,item.getName());
        helper.getView(R.id.view).setVisibility(item.isSelected? View.VISIBLE:View.INVISIBLE);
    }

    public void selectedHeader(int position){
        mData.get(mCurPos).isSelected = false;
        notifyDataSetChanged();
        mCurPos = position;
        mData.get(mCurPos).isSelected = true;
    }
}
