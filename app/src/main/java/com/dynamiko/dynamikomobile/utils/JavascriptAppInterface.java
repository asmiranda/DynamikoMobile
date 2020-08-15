package com.dynamiko.dynamikomobile.utils;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class JavascriptAppInterface {
    String name;
    WebView webView;
    Context mContext;

    /** Instantiate the interface and set the context */
    public JavascriptAppInterface(String name, WebView webView) {
        this.name = name;
        this.webView =  webView;
        mContext = webView.getContext();
    }

    @JavascriptInterface
    public void sendToken(String token) {
        Log.i("sendToken", token);
        Toast.makeText(mContext, token, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void loadComplete() {
        Log.i("loadComplete - "+this.name, "loadComplete - "+this.name);
    }

}
