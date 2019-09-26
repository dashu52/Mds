package com.mds.wanandroid.ui.information.fragment;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.design.widget.TabLayout;

import com.mds.wanandroid.R;
import com.mds.wanandroid.mvp.contract.ProjectContract;
import com.mds.wanandroid.mvp.presenter.ProjectPresenter;
import com.mds.wanandroid.ui.information.adapter.FragAdapter;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
import com.mds.wanandroid.ui.information.bean.ProjectTitleBean;
import com.mds.wanandroid.utils.MyLogger;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/24 20:51
 */
public class ProjectFragment extends BaseFragment<ProjectContract.IView,ProjectContract.IPresenter> implements ProjectContract.IView , View.OnClickListener{


    private List<ProjectTitleBean.DataBean> mTitleList;

    private TabLayout mTabLayout;
    private ViewPager mVP;
    private FrameLayout flTitle;
    private LinearLayout llError;
    private AppCompatTextView tvLoad;

    private FragAdapter<ProjSecondFragment> mAdapter;
    private List<ProjSecondFragment> mFragmentList;

    @Override
    protected ProjectContract.IPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        mTabLayout = rootView.findViewById(R.id.tablayout);
        mVP = rootView.findViewById(R.id.vp_project);
        llError = rootView.findViewById(R.id.ll_error);
        tvLoad = rootView.findViewById(R.id.tv_load);
        flTitle = rootView.findViewById(R.id.fl_title);

        tvLoad.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter.getProjectTitle();
    }

    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static ProjectFragment newInstance() {

        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLoadTitleList(List<ProjectTitleBean.DataBean> dataBean) {
        mTitleList = dataBean;
        flTitle.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);
        initTitleTab(mTitleList);
    }

    @Override
    public void onLoadTitleListFailure(String error) {
        flTitle.setVisibility(View.INVISIBLE);
        llError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadProjectList(ProjectListBean.DataBean dataBean) {

    }

    @Override
    public void onLoadProjectListFailure(String error) {

    }

    @Override
    public LifecycleTransformer<Long> getTransformer() {
        return this.bindToLifecycle();
    }

    private void initTitleTab(List<ProjectTitleBean.DataBean> titleList){
        if(titleList==null||titleList.size()==0){
            MyLogger.dLog().e("title list is null");
            return;
        }
        if(mFragmentList!=null){
            mFragmentList.clear();
        }
        mFragmentList = new ArrayList<>();
        for(int i=0,len=titleList.size();i<len;i++){
            ProjectTitleBean.DataBean dataBean = titleList.get(i);
            mFragmentList.add(ProjSecondFragment.newInstance(dataBean.getId()+"")
                        .setFragName(dataBean.getName())
                        .setId(dataBean.getId()+""));
        }
        mAdapter = new FragAdapter(getChildFragmentManager(),mFragmentList);
        mVP.setAdapter(mAdapter);
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        mTabLayout.setTabMode(titleList.size() <= 4 ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabIndicatorFullWidth(false);
        mTabLayout.setupWithViewPager(mVP);

    }

    @Override
    public void onClick(View v) {

    }
}
