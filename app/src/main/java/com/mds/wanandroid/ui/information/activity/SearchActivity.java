package com.mds.wanandroid.ui.information.activity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mds.wanandroid.R;
import com.mds.wanandroid.base.BaseActivity;
import com.mds.wanandroid.mvp.contract.SearchContract;
import com.mds.wanandroid.mvp.presenter.SearchPresenter;
import com.mds.wanandroid.ui.information.adapter.HomeAdapter;
import com.mds.wanandroid.ui.information.bean.MainListBean;
import com.mds.wanandroid.utils.Constant;
import com.mds.wanandroid.utils.MyLogger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/26 20:30
 */
public class SearchActivity extends BaseActivity<SearchContract.IView,SearchContract.IPresenter> implements SearchContract.IView {

    private EditText mEdtInput;
    private TextView mTVCancel;
    private View mEmptyView;
    private RecyclerView mRV;
    private SmartRefreshLayout mRefreshLayout;

    private InputMethodManager mInputManager;
    private List<MainListBean.DataBean.DatasBean> mDataBeans;
    private HomeAdapter mAdapter;


    private int mCurrPage;
    private int mPageCount;


    @Override
    protected SearchContract.IPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_detail;
    }

    @Override
    public void initView() {
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mEdtInput = findViewById(R.id.edt_search);
        mTVCancel = findViewById(R.id.tv_close);
        mEmptyView = findViewById(R.id.ll_empty);
        mRV = findViewById(R.id.rv_search);
        mRefreshLayout = findViewById(R.id.srf_search_refresh);
        mEdtInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    mPresenter.searchDetail(0,mEdtInput.getText().toString().trim(), Constant.FIRST_TIME_LOAD_DATA);
                    mInputManager.hideSoftInputFromWindow(mEdtInput.getWindowToken(),0);
                    return true;
                }
                return false;
            }
        });
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.searchDetail(mCurrPage,mEdtInput.getText().toString().trim(),Constant.PULL_UP_LOAD_MORE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.searchDetail(0,mEdtInput.getText().toString().trim(), Constant.PULL_DOWN_REFRESH);
            }
        });
        mTVCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void onLoadDetailList(MainListBean.DataBean dataBean,String type) {
        MyLogger.dLog().d("onLoadDetail list");
        mEmptyView.setVisibility(View.GONE);
        mCurrPage = dataBean.getCurPage();
        mPageCount = dataBean.getPageCount();
        switch (type){
            case Constant.FIRST_TIME_LOAD_DATA:
                mDataBeans = dataBean.getDatas();
                mAdapter = new HomeAdapter(this,R.layout.item_home_list,mDataBeans);
                mRV.setAdapter(mAdapter);
                mRV.setLayoutManager(new LinearLayoutManager(this));
                break;
            case Constant.PULL_DOWN_REFRESH:
                mAdapter.refresh(dataBean);
                mRefreshLayout.finishRefresh();
                break;
            case Constant.PULL_UP_LOAD_MORE:
                if(mPageCount>=mPageCount){
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                mAdapter.Load(dataBean.getDatas());
                mRefreshLayout.finishLoadMore();
                break;
        }

    }

    @Override
    public void onLoadDetailListFailure(String error) {
        mEmptyView.setVisibility(View.VISIBLE);
    }

}
