package com.musicabinet.archmvp.viewable;

import com.musicabinet.archmvp.base.BasePresenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kirchhoff-
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Viewable {
    int LAYOUT_NOT_DEFINED = -1;

    Class<? extends BasePresenter> presenter();

    int layout() default LAYOUT_NOT_DEFINED;
}
