package com.dynamiko.dynamikomobile.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

public class Utils {
    public static void syncFiles() throws IOException {
        String str = getStringFromURL("update.txt", "https://raw.githubusercontent.com/asmiranda/DynamikoMobile/master/app/src/main/assets/update.txt");
        boolean change = LocalDate.parse(str).isBefore(LocalDate.now());
        if (change) {
            updateStringFromURL("explore.html", "");
            updateStringFromURL("news.html", "");
        }
    }

    public static String getStringFromURL(String fileName, String urlStr) throws IOException {
        String text = new Scanner(new URL(urlStr).openStream(), "UTF-8").next();
        return text;
    }

    public static String updateStringFromURL(String fileName, String urlStr) throws IOException {
        String text = getStringFromURL(fileName, urlStr);

        File file = getFile(fileName);
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.print(text);
        }
        return text;
    }

    public static File getFile(String fileName) {
        File SDCardRoot = Environment.getExternalStorageDirectory();
        File mainFolder = new File(SDCardRoot, "dynamiko");
        if (!mainFolder.exists()) {
            mainFolder.mkdir();
        }
        File file = new File(mainFolder, fileName);
        return file;
    }
}
