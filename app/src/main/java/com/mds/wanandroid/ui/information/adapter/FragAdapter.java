package com.mds.wanandroid.ui.information.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mds.wanandroid.ui.information.fragment.ProjSecondFragment;

import java.util.List;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/09/25 15:01
 */
public class FragAdapter<T extends Fragment> extends FragmentStatePagerAdapter {

    private List<T> mFragmentList;

    public FragAdapter(FragmentManager fm, List<T> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public T getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        T fragment = mFragmentList.get(position);
        if(fragment instanceof ProjSecondFragment){
            return ((ProjSecondFragment)mFragmentList.get(position)).getFragName();
        }else {
            return "tab"+position;
        }
    }
}
