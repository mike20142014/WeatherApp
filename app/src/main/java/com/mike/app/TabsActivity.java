package com.mike.app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

/**
 * Created by MichaelHenry on 5/14/14.
 */
public class TabsActivity extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabHost mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("first").setIndicator("Current Weather").setContent(new Intent(this  ,CurrentActivity.class )));
        mTabHost.addTab(mTabHost.newTabSpec("second").setIndicator("Weather Forecast").setContent(new Intent(this,WeatherActivity.class)));
        mTabHost.setCurrentTab(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
