package com.dynamiko.dynamikomobile;

import android.os.AsyncTask;
import android.os.Bundle;

import com.dynamiko.dynamikomobile.fragment.AppsFragment;
import com.dynamiko.dynamikomobile.fragment.ExploreFragment;
import com.dynamiko.dynamikomobile.fragment.NewsFragment;
import com.dynamiko.dynamikomobile.utils.PermissionUtil;
import com.dynamiko.dynamikomobile.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dynamiko.dynamikomobile.ui.main.SectionsPagerAdapter;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static MainActivity main;
    public static AppsFragment appsFragment = new AppsFragment();
    public static ExploreFragment exploreFragment = new ExploreFragment();
    public static NewsFragment newsFragment = new NewsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        PermissionUtil.requestNeededPermissions();

        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        new MyTask().execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        PermissionUtil.onRequestPermissionsResult(requestCode,  permissions, grantResults);
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        String result;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Utils.syncFiles();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }
}