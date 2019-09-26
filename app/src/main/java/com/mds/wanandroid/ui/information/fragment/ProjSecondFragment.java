package com.mds.wanandroid.ui.information.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mds.wanandroid.R;
import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.mvp.contract.ProSecContract;
import com.mds.wanandroid.mvp.presenter.ProSecPresenter;
import com.mds.wanandroid.ui.information.activity.ArticleActivity;
import com.mds.wanandroid.ui.information.adapter.ProjSecAdapter;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
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
 * @date : 19/09/25 15:00
 */
public class ProjSecondFragment extends BaseFragment<ProSecContract.IView,ProSecContract.IPresenter> implements ProSecContract.IView{

    private RecyclerView mRV;
    private SmartRefreshLayout mRefreshLayout;
    private ProjSecAdapter mAdapter;

    private String mId;
    private String mName;
    private List<ProjectListBean.DataBean.DatasBean> mProjectList;
    private int mCurPage;
    private int mPageCount;
    private int mSelectPosition;
    @Override
    protected ProSecContract.IPresenter createPresenter() {
        return new ProSecPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project_sec;
    }

    @Override
    protected void initView() {
        mRV = rootView.findViewById(R.id.rv_project);
        mRefreshLayout = rootView.findViewById(R.id.srf_project_refresh);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getProjectList(mCurPage,mId,Constant.PULL_DOWN_REFRESH);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getProjectList(mCurPage,mId,Constant.PULL_UP_LOAD_MORE);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter.getProjectList(mCurPage,mId, Constant.FIRST_TIME_LOAD_DATA);
    }
    @Override
    public void onLoadProjectList(ProjectListBean.DataBean dataBean,String type){
        mCurPage = dataBean.getCurPage();
        mPageCount = dataBean.getPageCount();
        switch (type){
            case Constant.FIRST_TIME_LOAD_DATA:
                initProjectListView(dataBean);
                break;
            case Constant.PULL_DOWN_REFRESH:
                mCurPage = 0;
                mProjectList.clear();
                mProjectList.addAll(dataBean.getDatas());
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.finishRefresh();
                break;
            case Constant.PULL_UP_LOAD_MORE:
                if(mCurPage>=mPageCount){
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                mProjectList.addAll(dataBean.getDatas());
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.finishLoadMore();
                break;
        }
    }
    @Override
    public void onLoadProjectListFailure(String error){

    }

    @Override
    public void onCollectIn(CurrencyBean.DataBean dataBean) {
        mProjectList.get(mSelectPosition).setCollect(true);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCollectFailure(String error) {

    }

    @Override
    public void onCancelCollect(CurrencyBean.DataBean dataBean) {
        mProjectList.get(mSelectPosition).setCollect(false);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelCollect(String error) {

    }

    /**
     * 初始化project 视图列表
     * @param dataBean
     */
    private void initProjectListView(ProjectListBean.DataBean dataBean){
        if(mProjectList!=null){
            mProjectList.clear();
        }
        mProjectList = dataBean.getDatas();
        mAdapter = new ProjSecAdapter(mProjectList);
        mAdapter.setHasStableIds(true);//避免notifyDataSetChanged导致图片闪烁，原来是对比图片URL有没变化
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.isFirstOnly(true);
        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.dLog().d("onItemclick");
                startArticleDetail(getActivity(),mProjectList.get(position).getTitle(),mProjectList.get(position).getLink()+"");
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.item_iv_collect){
                    mSelectPosition = position;
                    toggleCollect(dataBean.getDatas().get(position));
                }
            }
        });
    }

    /**
     * 根据现有状态勾选收藏
     * @param dataBean
     */
    private void toggleCollect(ProjectListBean.DataBean.DatasBean dataBean){
        if(dataBean.isCollect()){
            presenter.cancelCollect(dataBean.getId()+"");
        }else{
            presenter.collectIn(dataBean.getId()+"");
        }
    }
    public String getFragName(){
        return mName;
    }
    public ProjSecondFragment setFragName(String name){
        mName = name;
        return this;
    }
    public ProjSecondFragment setId(String id){
        mId = id;
        return this;
    }

    public static ProjSecondFragment newInstance(String id) {
        ProjSecondFragment fragment = new ProjSecondFragment();
        Bundle args = new Bundle();
        args.putString("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public LifecycleTransformer<Long> getTransformer() {
        return this.bindToLifecycle();
    }

    public  static  void  startArticleDetail(Context context, String title, String url){

        Intent intent =new Intent(context, ArticleActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        context.startActivity(intent);

    }
}
