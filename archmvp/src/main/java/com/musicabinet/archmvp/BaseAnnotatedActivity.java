package com.musicabinet.archmvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.musicabinet.archmvp.base.BaseContract;
import com.musicabinet.archmvp.viewable.AnnotationHelper;
import com.musicabinet.archmvp.viewable.Viewable;

/**
 * @author Kirchhoff-
 */

public class BaseAnnotatedActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends BaseActivity<V, P> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getClass().getAnnotation(Viewable.class).layout();
        if (layoutResId != Viewable.LAYOUT_NOT_DEFINED)
            setContentView(layoutResId);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected P initPresenter() {
        return (P) AnnotationHelper.createPresenter(getClass());
    }
}
