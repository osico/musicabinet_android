package com.musicabinet.mobile.utils;

import android.media.MediaScannerConnection;
import android.os.Environment;
import android.text.TextUtils;

import com.musicabinet.mobile.MusicabinetApp;
import com.musicabinet.mobile.model.lesson.machine.FileDataItem;
import com.musicabinet.mobile.model.lesson.machine.ImprovisationResultItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static com.musicabinet.mobile.Constants.IMPROVISATION_FILE_NAME;

/**
 * @author Kirchhoff-
 */

public class FileUtils {

    private final static String EMPTY_ITEM = "null";
    private static File improvisationFile;

    public static ArrayList<FileDataItem> getDataFromFile(String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        //Make sure you close all streams.
        ArrayList<FileDataItem> list = convertStreamToString(fin);
        fin.close();
        return list;
    }

    public static File createImprovisationFile(TreeMap<Integer,
            List<ImprovisationResultItem>> improvisationMap) {

        //Remove last row from map if needed
        boolean deleteRowResult = deleteRowIfNeeded(improvisationMap);

        if (deleteRowResult) {
            deleteRowIfNeeded(improvisationMap);
        }

        clearFile();

        for (List<ImprovisationResultItem> improvisationList : improvisationMap.values()) {

            for (ImprovisationResultItem improvisationItem : improvisationList) {

                if (improvisationItem.getFileDataItem() == null &&
                        improvisationItem.getNoteElement() == null &&
                        improvisationItem.getToneOrChordResult() == null) {
                    writeToFile(EMPTY_ITEM);
                    continue;
                }

                if (improvisationItem.getNoteElement() != null) {
                    writeToFile(improvisationItem.getNoteElement().getCode());
                    continue;
                }

                if (improvisationItem.getToneOrChordResult() != null
                        && improvisationItem.isUserReselecting()) {
                    writeToFile(improvisationItem.getToneOrChordResult().getTone().getName(),
                            improvisationItem.getToneOrChordResult().getChord().getName());
                    continue;
                }

                if (improvisationItem.getFileDataItem() != null &&
                        (!TextUtils.isEmpty(improvisationItem.getFileDataItem().getNoteInformation()) ||
                                !TextUtils.isEmpty(improvisationItem.getFileDataItem().getTone()) ||
                                !TextUtils.isEmpty(improvisationItem.getFileDataItem().getChord())))
                    writeToFile(improvisationItem.getFileDataItem());
                else
                    writeToFile(EMPTY_ITEM);

            }

            writeToFile("\n");
        }

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(path, IMPROVISATION_FILE_NAME);
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
        if (fileString.startsWith(" "))
            fileString = fileString.substring(1, fileString.length());
        String[] stringArray = fileString.split(" ");

        for (int i = 0; i < stringArray.length; i++) {

            if (!stringArray[i].equals(EMPTY_ITEM)) {

                if (stringArray[i].startsWith(":")) {
                    int splitIndex = stringArray[i].indexOf("|");
                    fileDataItems.add(new FileDataItem(stringArray[i].substring(1, splitIndex),
                            stringArray[i].substring(splitIndex + 1), null,
                            String.valueOf(line) + String.valueOf(i + 1)));
                } else {
                    fileDataItems.add(new FileDataItem(null, null, stringArray[i],
                            String.valueOf(line) + String.valueOf(i + 1)));
                }

            }
        }

        return fileDataItems;
    }

    private static void writeToFile(String data) {
        writeToFile(getImprovisationFile(), data);
    }

    private static void writeToFile(String tone, String chord) {
        String builder = ":" + tone + "|" + chord;
        writeToFile(getImprovisationFile(), builder);
    }

    private static void writeToFile(FileDataItem fileDataItem) {
        if (fileDataItem.getNoteInformation() != null)
            writeToFile(getImprovisationFile(), fileDataItem.getNoteInformation());
        else
            writeToFile(fileDataItem.getTone(), fileDataItem.getChord());
    }

    private static void writeToFile(File file, String data) {
        if (file != null) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(file, true), 1024);
                try {
                    out.write(data);
                    out.write(" ");
                } catch (IOException e) {
                } finally {
                    out.close();
                }
            } catch (Exception e) {

            }
        }
    }

    private static void clearFile() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file = new File(path, IMPROVISATION_FILE_NAME);
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static File getImprovisationFile() {
        if (isFileExist(IMPROVISATION_FILE_NAME)) {
            if (improvisationFile == null) {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                File file = new File(path, IMPROVISATION_FILE_NAME);
                PrintWriter writer;
                try {
                    writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }

                improvisationFile = file;
            }
        } else {
            try {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                File file = new File(path, IMPROVISATION_FILE_NAME);
                file.createNewFile();
                path.setExecutable(true);
                path.setReadable(true);
                path.setWritable(true);
                MediaScannerConnection.scanFile(MusicabinetApp.Companion.get(), new String[]{path.toString()}, null, null);
                improvisationFile = file;
            } catch (Exception e) {
                return null;
            }

        }

        return improvisationFile;
    }

    private static boolean isFileExist(String fileName) {
        File downloadedFile = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), fileName);
        return downloadedFile.exists();
    }

    private static boolean deleteRowIfNeeded(TreeMap<Integer,
            List<ImprovisationResultItem>> improvisationMap) {
        int lastKey = improvisationMap.lastKey();
        List<ImprovisationResultItem> lastItemList = improvisationMap.get(lastKey);

        boolean shouldLoadLastRow = false;
        for (ImprovisationResultItem improvisationItem : lastItemList) {
            if (improvisationItem.getFileDataItem() != null &&
                    improvisationItem.getNoteElement() != null &&
                    improvisationItem.getToneOrChordResult() != null &&
                    (!TextUtils.isEmpty(improvisationItem.getFileDataItem().getNoteInformation()) ||
                            !TextUtils.isEmpty(improvisationItem.getFileDataItem().getTone()) ||
                            !TextUtils.isEmpty(improvisationItem.getFileDataItem().getChord()))) {
                shouldLoadLastRow = true;
            }
        }

        if (!shouldLoadLastRow) {
            improvisationMap.remove(lastKey);
            return true;
        }

        return false;
    }


    public static String readStringFromFile(File file) {
        // File file = new File(Environment
        //         .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), fileName);

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }

        return text.toString();
    }

}
