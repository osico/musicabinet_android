package com.musicabinet.mobile.utils;

import com.musicabinet.mobile.model.lesson.machine.FileDataItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Kirchhoff-
 */

public class FileUtils {

    private final static String EMPTY_ITEM = "null";

    public static ArrayList<FileDataItem> getDataFromFile(String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        //Make sure you close all streams.
        ArrayList<FileDataItem> list = convertStreamToString(fin);
        fin.close();
        return list;
    }

    private static ArrayList<FileDataItem> convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        ArrayList<FileDataItem> fileDataItems = new ArrayList<>();
        int lineCount = 0;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                fileDataItems.addAll(splitString(line, lineCount));
                lineCount++;
            }
        }
        reader.close();
        return fileDataItems;
    }

    private static ArrayList<FileDataItem> splitString(String fileString, int line) {
        ArrayList<FileDataItem> fileDataItems = new ArrayList<>();
        String[] stringArray = fileString.split(" ");
        for (int i = 0; i < stringArray.length; i++) {

            if (!stringArray[i].equals(EMPTY_ITEM)) {

                if (stringArray[i].startsWith(":")) {
                    int splitIndex = stringArray[i].indexOf("|");
                    fileDataItems.add(new FileDataItem(stringArray[i].substring(1, splitIndex),
                            stringArray[i].substring(splitIndex + 1), null,
                            String.valueOf(line) + String.valueOf(i)));
                } else {
                    fileDataItems.add(new FileDataItem(null, null, stringArray[i],
                            String.valueOf(line) + String.valueOf(i)));
                }

            }
        }

        return fileDataItems;
    }
}
