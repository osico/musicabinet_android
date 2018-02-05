package com.musicabinet.mobile.repository;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.musicabinet.mobile.model.lesson.machine.ImprovisationResult;

import java.util.ArrayList;

/**
 * @author Kirchhoff-
 */

public class ImprovisationService extends IntentService {

    private final static String SERVICE_NAME = "IMPROVISATION_SERVICE";
    private final static String FILE_ID_ARG = "FILE_ID_ARG";
    private final static String IMPROVISATION_MAP_ARG = "IMPROVISATION_MAP_ARG";

    public ImprovisationService() {
        super(SERVICE_NAME);
    }

    public static void uploadImprovisation(Context context, String id,
                                           ArrayList<ImprovisationResult> resultImprovisation) {
        Intent intent = new Intent(context, ImprovisationService.class);
        intent.putExtra(FILE_ID_ARG, id);
        intent.putExtra(IMPROVISATION_MAP_ARG, resultImprovisation.toString());
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String fileId = intent.getStringExtra(FILE_ID_ARG);
        String improvisationMap = intent.getStringExtra(IMPROVISATION_MAP_ARG);
        if (TextUtils.isEmpty(fileId)) {
            Log.d("TAG", "Should create id and then load file");
        } else {
            Log.d("TAG", "Load file immediatle");
        }
    }
}
