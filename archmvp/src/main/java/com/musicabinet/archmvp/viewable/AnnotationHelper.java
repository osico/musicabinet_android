package com.musicabinet.archmvp.viewable;

import com.musicabinet.archmvp.base.BaseContract;

/**
 * @author Kirchhoff-
 */

public class AnnotationHelper {

    public static BaseContract.Presenter createPresenter(Class<?> annotatedClass) {
        try {
            return annotatedClass.getAnnotation(Viewable.class).presenter().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MvpException("Cannot create an instance of " + annotatedClass, e);
        }
    }
}
