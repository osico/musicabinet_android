package com.musicabinet.mobile.repository;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author Kirchhoff-
 */

public class ImprovisationService extends IntentService {

    private final static String SERVICE_NAME = "IMPROVISATION_SERVICE";
    private final static String FILE_ID_ARG = "FILE_ID_ARG";
    private final static String FILE_NAME_ARG = "FILE_NAME_ARG";

    public ImprovisationService() {
        super(SERVICE_NAME);
    }

    public static void uploadImprovisation(Context context, String id, String fileName) {
        Intent intent = new Intent(context, ImprovisationService.class);
        intent.putExtra(FILE_ID_ARG, id);
        intent.putExtra(FILE_NAME_ARG, fileName);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String fileId = intent.getStringExtra(FILE_ID_ARG);
        if (TextUtils.isEmpty(fileId)) {
            Log.d("TAG", "Should create id and then load file");
        } else {
            Log.d("TAG", "Load file immediatle");
        }
    }
}
