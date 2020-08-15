package com.dynamiko.dynamikomobile.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dynamiko.dynamikomobile.MainActivity;

public class PermissionUtil {
    static final int PERMISSION_REQUEST_CODE = 100;

    public static void requestNeededPermissions() {
        requestPermission(Manifest.permission.INTERNET);
//        requestPermission(Manifest.permission.CAMERA);
//        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private static void requestPermission(String permissionStr) {
        int result = ContextCompat.checkSelfPermission(MainActivity.main.getBaseContext(), permissionStr);
        if (result != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{ permissionStr };
            ActivityCompat.requestPermissions(MainActivity.main, permissions, PERMISSION_REQUEST_CODE);
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(MainActivity.main, "Permission granted", Toast.LENGTH_SHORT).show();
                    //store permission in shared pref
                } else {
//                    Toast.makeText(MainActivity.main, "Permission denied", Toast.LENGTH_SHORT).show();
                    //store permission in shared pref
                }
                break;
        }
    }

}
