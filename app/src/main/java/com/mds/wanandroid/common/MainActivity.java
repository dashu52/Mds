package com.mds.wanandroid.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mds.wanandroid.R;
import com.mds.wanandroid.base.BaseActivity;
import com.mds.wanandroid.mvp.contract.MainContract;
import com.mds.wanandroid.mvp.presenter.MainPresenter;
import com.mds.wanandroid.ui.information.fragment.HomeFragment;
import com.mds.wanandroid.ui.information.fragment.PersonalFragment;

public class MainActivity extends BaseActivity<MainContract.IView, MainContract.IPresenter> implements BottomNavigationBar.OnTabSelectedListener, MainContract.IView{

    private static final int HOME = 0;
    private static final int PROJECT = 1;
    private static final int NAVIGATION = 2;
    private static final int PERSONAL = 3;
    private BottomNavigationBar bottomNavigationBar;
    private LinearLayout flashView;
    private FrameLayout llContent;

    // Fragment管理器，和执行器
    private FragmentManager mManager;
    private Fragment mHomeFrg;
    private Fragment mProjectFrg;
    private Fragment mNavigationFrg;
    private Fragment mPersonalFrg;

    private FragmentTransaction mTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        flashView = findViewById(R.id.flash_view);
        llContent = findViewById(R.id.ll_content);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        //  设置模式
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //  设置背景色样式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.background_gray_color);

        flashView.setVisibility(View.VISIBLE);
        flashView.postDelayed(new Runnable() {
            @Override
            public void run() {
                flashView.setVisibility(View.GONE);
            }
        }, 1500);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mManager =getSupportFragmentManager();
        initBottomNavigationBar();
    }
    private void initBottomNavigationBar() {

        /**
         *  new BottomNavigationItem(R.mipmap.tab_home_pressed,"首页") ,选中的图标，文字
         *  setActiveColorResource：选中的颜色
         *  setInactiveIconResource：未选中的图标
         *  setInActiveColorResource：未选中的颜色
         */


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_home1, "首页").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_home2).setInActiveColorResource(R.color.txt_unselect))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_project1, "项目").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_project2).setInActiveColorResource(R.color.txt_unselect))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_navigate1, "导航").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_navigate2).setInActiveColorResource(R.color.txt_unselect))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_me1, "我的").setActiveColorResource(R.color.txt_select).setInactiveIconResource(R.mipmap.ic_main_me2).setInActiveColorResource(R.color.txt_unselect))
                .initialise();
        // mShapeBadgeItem.hide();

        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.selectTab(0);
    }

    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(mHomeFrg!=null){
            fragmentTransaction.hide(mHomeFrg);
        }
        if(mProjectFrg!=null){
            fragmentTransaction.hide(mProjectFrg);
        }
        if(mNavigationFrg!=null){
            fragmentTransaction.hide(mNavigationFrg);
        }
        if(mPersonalFrg!=null){
            fragmentTransaction.hide(mPersonalFrg);
        }
    }
    @Override
    public void onTabSelected(int position) {
        mTransaction = mManager.beginTransaction();
        hideFragment(mTransaction);
        switch (position){
            case HOME:
                if(mHomeFrg==null){
                     mHomeFrg = HomeFragment.newInstance();
                     mTransaction.add(R.id.ll_content,mHomeFrg);
                }else{
                    mTransaction.show(mHomeFrg);
                }
                break;
            case PROJECT:break;
            case NAVIGATION:break;
            case PERSONAL:
                if(mPersonalFrg==null){
                    mPersonalFrg = PersonalFragment.newInstance();
                    mTransaction.add(R.id.ll_content,mPersonalFrg);
                }else{
                    mTransaction.show(mPersonalFrg);
                }
                break;
        }
        mTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
