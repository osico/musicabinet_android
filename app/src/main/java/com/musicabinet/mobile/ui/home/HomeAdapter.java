package com.musicabinet.mobile.ui.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Kirchhoff-
 */

public class HomeAdapter extends PagerAdapter {

    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        HomePagerEnum customPagerEnum = HomePagerEnum.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(),
                container, false);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return HomePagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        HomePagerEnum customPagerEnum = HomePagerEnum.values()[position];
        return context.getString(customPagerEnum.getTitleResId());
    }
}
