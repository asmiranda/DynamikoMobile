package com.dynamiko.dynamikomobile.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dynamiko.dynamikomobile.R;
import com.dynamiko.dynamikomobile.fragment.AppsFragment;
import com.dynamiko.dynamikomobile.fragment.ExploreFragment;
import com.dynamiko.dynamikomobile.fragment.NewsFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    AppsFragment appsFragment = new AppsFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    NewsFragment newsFragment = new NewsFragment();

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0) {
            return appsFragment;
        }
        else if (position==1) {
            return exploreFragment;
        }
        else if (position==2) {
            return newsFragment;
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}