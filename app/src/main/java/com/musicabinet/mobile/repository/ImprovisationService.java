package com.musicabinet.mobile.repository;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.musicabinet.mobile.Injection;
import com.musicabinet.mobile.model.lesson.machine.ImprovisationResultWrapper;
import com.musicabinet.mobile.model.lesson.machine.save.ImprovisationStaveResult;
import com.musicabinet.mobile.utils.FileUtils;
import com.musicabinet.mobile.utils.GsonHolder;

import java.io.File;
import java.util.UUID;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

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
                                           ImprovisationResultWrapper resultImprovisation) {
        Intent intent = new Intent(context, ImprovisationService.class);
        intent.putExtra(FILE_ID_ARG, id);
        intent.putExtra(IMPROVISATION_MAP_ARG, GsonHolder.getGson().toJson(resultImprovisation));
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String fileId = intent.getStringExtra(FILE_ID_ARG);
        String improvisationString = intent.getStringExtra(IMPROVISATION_MAP_ARG);
        final ImprovisationResultWrapper wrapper = GsonHolder.getGson().fromJson(improvisationString,
                ImprovisationResultWrapper.class);

        if (TextUtils.isEmpty(fileId)) {
            Injection.INSTANCE.provideRepository()
                    .saveImprovisation(UUID.randomUUID().toString())
                    .subscribe(new Consumer<ImprovisationStaveResult>() {
                        @Override
                        public void accept(ImprovisationStaveResult improvisationStaveResult) throws Exception {
                            uploadImprovisationToServer(wrapper, improvisationStaveResult.getStave().getStoredFile().getId());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d("TAG", "Error");
                        }
                    });
        } else {
            uploadImprovisationToServer(wrapper, fileId);
        }

    }


    private void uploadImprovisationToServer(ImprovisationResultWrapper resultWrapper, String fileId) {
        File resultFile = FileUtils.createImprovisationFile(resultWrapper.getMap());
        Injection.INSTANCE.provideRepository()
                .uploadImprovisation(fileId, resultFile)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("TAG", "Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TAG", "Error");
                    }
                });
    }
}
