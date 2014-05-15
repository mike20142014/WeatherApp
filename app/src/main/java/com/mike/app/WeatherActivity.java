package com.mike.app;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by MichaelHenry on 5/14/14.
 */
public class WeatherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_activity_main);
    }

}
