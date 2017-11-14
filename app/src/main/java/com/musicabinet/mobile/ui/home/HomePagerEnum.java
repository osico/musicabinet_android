package com.musicabinet.mobile.ui.home;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

import com.musicabinet.mobile.R;

/**
 * @author Kirchhoff-
 */

public enum HomePagerEnum {

    NEWS(R.string.news_tab, R.layout.view_home_news),
    TUTORIAL(R.string.tutorial_tab, R.layout.view_home_tutorial);

    @StringRes
    private int titleResId;
    @LayoutRes
    private int layoutResId;

    HomePagerEnum(@StringRes int titleResId, @LayoutRes int layoutResId) {
        this.titleResId = titleResId;
        this.layoutResId = layoutResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getLayoutResId() {
        return layoutResId;
    }
}
