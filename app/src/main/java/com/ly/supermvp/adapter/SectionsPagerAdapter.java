package com.ly.supermvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ly.supermvp.ui.activity.MainActivity;
import com.ly.supermvp.ui.fragment.NewsFragment;
import com.ly.supermvp.ui.fragment.WeatherFragment;

/**
 * <Pre>
 * viewpager选项卡适配器
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 16:26
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NewsFragment.newInstance();
            case 1:
                return WeatherFragment.newInstance();
        }
        return MainActivity.PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "最新新闻";
            case 1:
                return "天气预报";
            case 2:
                return "号码归属地";
        }
        return null;
    }
}
