package com.navgnss.gankio.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.navgnss.gankio.fragment.AndroidFragment;

import java.util.List;

/**
 * Created by ZhuJinWei on 2017/8/9.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {

    private final String[] titles;
    private Context mContext;
    private List<Fragment> fragments;


    public TabFragmentAdapter(FragmentManager fm,String[] titles,Context context,List<Fragment> fragments) {
        super(fm);
        mContext=context;
        this.titles=titles;
        this.fragments=fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
