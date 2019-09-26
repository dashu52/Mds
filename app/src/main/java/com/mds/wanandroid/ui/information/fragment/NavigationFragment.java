package com.mds.wanandroid.ui.information.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mds.wanandroid.R;
import com.mds.wanandroid.mvp.contract.NaviContract;
import com.mds.wanandroid.mvp.presenter.NaviPresenter;
import com.mds.wanandroid.ui.information.activity.ArticleActivity;
import com.mds.wanandroid.ui.information.adapter.HeaderAdapter;
import com.mds.wanandroid.ui.information.adapter.NaviArticleAdapter;
import com.mds.wanandroid.ui.information.bean.NavigateBean;
import com.mds.wanandroid.utils.CommonUtil;
import com.mds.wanandroid.utils.Constant;
import com.mds.wanandroid.utils.MyLogger;
import com.trello.rxlifecycle2.LifecycleTransformer;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/26 14:24
 */
public class NavigationFragment extends BaseFragment<NaviContract.IView,NaviContract.IPresenter> implements NaviContract.IView,NaviArticleAdapter.OnItemClickListener{

    /**
     * 左侧List
     */
    private RecyclerView mLeftRV;
    /**
     * 右侧List
     */
    private RecyclerView mRightRV;

    private List<NavigateBean.DataBean> mCategoryList;
    private List<NavigateBean.DataBean.ArticlesBean> mArticleList;
    private HeaderAdapter mLeftHeaderAdapter;
    private NaviArticleAdapter mRightArticleAdapter;
    /**
     * 左侧header和右侧小标题位置对应关系表
     */
    private Map<Integer,Integer> mLeft2RightMap = new HashMap<>();

    @Override
    protected NaviContract.IPresenter createPresenter() {
        return new NaviPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_navigate;
    }

    @Override
    protected void initView() {
        mLeftRV = rootView.findViewById(R.id.rv_sort_left);
        mRightRV = rootView.findViewById(R.id.rv_sort_right);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter.getNavigateList();
    }

    @Override
    public LifecycleTransformer<Long> getTransformer() {
        return this.bindToLifecycle();
    }

    @Override
    public void onLoadNaviList(@Nullable List<? extends NavigateBean.DataBean> dataBean) {
        initListView((List<NavigateBean.DataBean>)dataBean);
    }

    @Override
    public void onLoadNaviListFailure(@Nullable String error) {

    }

    public void initListView(@Nullable List<NavigateBean.DataBean> dataBean){
        if(!initListData(dataBean)){
            return;
        }
        initMap(mArticleList);
        initListAdapter(dataBean);
    }
    private boolean initListData(List<NavigateBean.DataBean> dataBean){
        mCategoryList = dataBean;
        if(mCategoryList==null&&mCategoryList.size()==0){
            return false;
        }
        mCategoryList.get(0).isSelected=true;//默认选中第0个header item
        mArticleList = new ArrayList<>();
        for (int i=0,size=mCategoryList.size();i<size;i++){
            NavigateBean.DataBean CategoryBean = mCategoryList.get(i);
            NavigateBean.DataBean.ArticlesBean headerItem = new NavigateBean.DataBean.ArticlesBean(true,CategoryBean.getName());
            headerItem.viewType = Constant.ARTICLE_CATEGORY;
            headerItem.position = i;
            mArticleList.add(headerItem);
            List<NavigateBean.DataBean.ArticlesBean> articlesBeanList = CategoryBean.getArticles();
            for(int j=0,len=articlesBeanList.size();j<len;j++){
                NavigateBean.DataBean.ArticlesBean articleItem = articlesBeanList.get(j);
                articleItem.position = -1;
                articleItem.viewType = Constant.ARTICLE_ITEM;
                mArticleList.add(articleItem);
            }
        }
        return true;
    }
    private void initMap(List<NavigateBean.DataBean.ArticlesBean> articlesBeans){
        for (int i = 0,len=articlesBeans.size(); i <len ; i++) {
            int pos = articlesBeans.get(i).position;
            if(pos!=-1){
                mLeft2RightMap.put(pos,i);
            }
        }
    }
    private void initListAdapter(List<NavigateBean.DataBean> dataBean){
        mLeftHeaderAdapter = new HeaderAdapter(dataBean);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mLeftRV.setLayoutManager(lm);
        mLeftRV.setAdapter(mLeftHeaderAdapter);
        mLeftHeaderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.dLog().d("left header list click "+position);
                mLeftHeaderAdapter.selectedHeader(position);
                moveToMiddel(mLeftRV,position);
                ((GridLayoutManager)mRightRV.getLayoutManager())
                        .scrollToPositionWithOffset(mLeft2RightMap.get(position),0);
            }
        });

        mRightArticleAdapter = new NaviArticleAdapter(getContext(),mArticleList);
        mRightArticleAdapter.setmOnItemClickListener(this);
        GridLayoutManager gm = new GridLayoutManager(getContext(),3);
        gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int i) {
                if(mArticleList.get(i).viewType==Constant.ARTICLE_CATEGORY){
                    return 3;//分类名占3个单元
                }else {
                    return 1;//文章名占1个单元
                }
            }
        });
        mRightRV.setLayoutManager(gm);
        mRightRV.setAdapter(mRightArticleAdapter);
    }
    public static NavigationFragment newInstance(){
        return new NavigationFragment();
    }

    @Override
    public void onArticleItemClick(NavigateBean.DataBean.ArticlesBean articlesBean) {
        CommonUtil.startArticleDetail(getContext(),articlesBean.getTitle(),articlesBean.getLink());
    }


    private void moveToMiddel(RecyclerView rv,int selectedPos){
        int firstVisiablePos = ((LinearLayoutManager)mLeftRV.getLayoutManager()).findFirstVisibleItemPosition();
        int lastVisiablePos = ((LinearLayoutManager)mLeftRV.getLayoutManager()).findLastVisibleItemPosition();
        int middlePos = (firstVisiablePos+lastVisiablePos)/2;
        int distance = Math.abs(selectedPos-middlePos);

        if(selectedPos<middlePos){
            //在中间item上面，listview往下滑动到middlePos,距离为下标为distance的item的top值
            rv.scrollBy(0,-rv.getChildAt(distance).getTop());
        }else{
            rv.scrollBy(0,rv.getChildAt(distance).getTop());
        }

    }

}
