package com.mds.wanandroid.ui.information.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.mds.wanandroid.R;
import com.mds.wanandroid.base.BaseActivity;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.mvp.contract.CollectContract;
import com.mds.wanandroid.mvp.presenter.CollectPresenter;
import com.mds.wanandroid.ui.information.adapter.CollectAdapter;
import com.mds.wanandroid.ui.information.bean.MyCollectBean;
import com.mds.wanandroid.utils.Constant;
import com.mds.wanandroid.utils.MyLogger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/23 16:32
 */
public class CollectActivity extends BaseActivity<CollectContract.IView,CollectContract.IPresenter> implements CollectContract.IView {

    /**
     * 当前页
     */
    private int mCurrentPage=0;
    /**
     * 分页总数
     */
    private int mPageCount=0;
    /**
     * 收藏唯一标识
     */
    private String mId;

    private RecyclerView mRV;
    private ImageView mBackBtn;
    private SmartRefreshLayout mRefreshLayout;

    private List<MyCollectBean.DataBean.DatasBean> mDatasBeans;
    private CollectAdapter mAdapter;

    @Override
    protected CollectContract.IPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public void initView() {
        mRV = findViewById(R.id.collect_list);
        mRefreshLayout = findViewById(R.id.collect_refresh);
        mBackBtn = findViewById(R.id.iv_toolbar_left);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MyLogger.dLog().d("onRefresh----------");
                mCurrentPage=0;
                mRefreshLayout.finishRefresh();
                mPresenter.getCollectData(Constant.PULL_DOWN_REFRESH);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                MyLogger.dLog().d("onLoadMore----------");
                if(mCurrentPage+1>=mPageCount){
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }else{
                    mPresenter.getCollectData(Constant.PULL_UP_LOAD_MORE);
                }
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getCollectData(Constant.FIRST_TIME_LOAD_DATA);
    }


    @Override
    public void onCancelCollectSuccess(int position) {
        MyLogger.dLog().d("onCancelCollectSuccess "+position);
        mDatasBeans.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelCollectFail(String error) {
        MyLogger.dLog().d("onCancelCollectFail "+error);
    }

    @Override
    public void getCollectSuccess(MyCollectBean.DataBean dataBean,String type) {
        MyLogger.dLog().d("getCollectSuccess ="+dataBean+" type="+type);
        mCurrentPage = dataBean.getCurPage();
        mPageCount = dataBean.getPageCount();
        switch (type){
            case Constant.FIRST_TIME_LOAD_DATA:
                mDatasBeans = dataBean.getDatas();
                buildAdapter();
                LinearLayoutManager lm = new LinearLayoutManager(this);
                mRV.setLayoutManager(lm);
                mRV.setAdapter(mAdapter);
                break;
            case Constant.PULL_DOWN_REFRESH:
                mDatasBeans.clear();
                mDatasBeans.addAll(dataBean.getDatas());
                mAdapter.notifyDataSetChanged();
                break;
            case Constant.PULL_UP_LOAD_MORE:
                mDatasBeans.addAll(dataBean.getDatas());
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.finishLoadMore();
                break;
        }

    }

    private void buildAdapter(){
        mAdapter = new CollectAdapter(mDatasBeans);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.btnDelete){
                    SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) adapter.getViewByPosition(mRV,position,R.id.SwipeMenuLayout);
                    swipeMenuLayout.smoothClose();
                    mPresenter.cancelCollect(position,mDatasBeans.get(position).getOriginId()+"");
                }
            }
        });
    }
    @Override
    public void getCollectFail(String error) {

    }

    @Override
    public int getCurrentPage() {
        return mCurrentPage;
    }


    @Override
    public LifecycleTransformer<Long> getTransformer() {
        return this.bindToLifecycle();
    }
}
