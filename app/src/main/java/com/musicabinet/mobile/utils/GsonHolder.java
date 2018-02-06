package com.musicabinet.mobile.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

/**
 * @author Kirchhoff-
 */

public class GsonHolder {

    private static final Gson GSON = new Gson();

    @NonNull
    public static Gson getGson() {
        return GSON;
    }
}
