package com.dynamiko.dynamikomobile.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dynamiko.dynamikomobile.MainActivity;
import com.dynamiko.dynamikomobile.R;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

public class Utils {
    private static String HOST = "https://raw.githubusercontent.com/asmiranda/DynamikoMobile/master/app/src/main/assets/";

    public static void syncFiles() throws IOException {
        String str = getStringFromURL("update.txt", HOST+"update.txt");
        boolean change = LocalDate.parse(str).isBefore(LocalDate.now());
        if (change) {
            updateFS("explore.html", HOST+"explore.html");
            updateFS("news.html", HOST+"news.html");
        }
    }

    public static String getStringFromURL(String fileName, String urlStr) throws IOException {
        String text = new Scanner(new URL(urlStr).openStream(), "UTF-8").useDelimiter("\\A").next();
        return text;
    }

    public static void updateFS(String fileName, String urlStr) throws IOException {
        String text = getStringFromURL(fileName, urlStr);

        SharedPreferences sharedPref = MainActivity.main.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(fileName, text);
        editor.commit();
    }

    public static String getFromFS(String fileName) {
        SharedPreferences sharedPref = MainActivity.main.getPreferences(Context.MODE_PRIVATE);
        String str = sharedPref.getString(fileName, null);
        return str;
    }

    public static WebView initWebView(String name, View view, int webViewInt, boolean debug) {
        WebView webView = (WebView) view.findViewById(webViewInt);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavascriptAppInterface(name, webView), "Android");
        if (debug && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        return webView;
    }
}
