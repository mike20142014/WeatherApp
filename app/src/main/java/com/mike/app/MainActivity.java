package com.mike.app;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mike.utils.AppUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {

    Context context;
    public static AppUtils appUtils;

    public static final String APP_ID = "889673151059bed61a35b1f33f5488a6";

    public static final String COMMON_IMAGE_URL = "http://openweathermap.org/img/w/";
    public static final String CURRENT_WEATHER_URL_MAIN = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139";
    public static final String THREE_HOUR_FORECAST_URL_MAIN = "http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139";
    public static final String TEN_DAY_FORECAST_URL_MAIN = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=10&mode=json";

    public static String CURRENT_WEATHER_URL_MAIN_BREAK1 = "http://api.openweathermap.org/data/2.5/weather?";
    public static String THREE_HOUR_FORECAST_URL_MAIN_BREAK1 = "http://api.openweathermap.org/data/2.5/forecast?";
    public static String TEN_DAY_FORECAST_URL_MAIN_BREAK1 = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    public static String TEN_DAY_FORECAST_URL_MAIN_BREAK2 = "&cnt=10&mode=json";

    public static String CURRENT_LAT, CURRENT_LON;
    public static String TEMP_URL;
    public static String FINAL_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        appUtils = new AppUtils(context);
        //getMyLocation();
        new BackgroundTask(context, CURRENT_WEATHER_URL_MAIN).execute();

    }

    public class BackgroundTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        Context AsyncContext;
        String someURL;

        public BackgroundTask(Context context1, String SOME_URL) {

            super();
            this.AsyncContext = context1;
            this.someURL = SOME_URL;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Checking Network Connection");
            pDialog.setIndeterminate(true);
            pDialog.setMax(100);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            getCurrentWeather(someURL);

            /*if (appUtils.isOnline()) {

                *//**
             * Don't use if network exists!!
             *//*

                runOnUiThread(new Runnable() {
                    public void run() {

                    }
                });


            } else {

                *//**
             * Only to show toast or dialog!!Not for heavy duty!
             *//*
                runOnUiThread(new Runnable() {
                    public void run() {

                        Toast.makeText(context, "Network not Available!", Toast.LENGTH_LONG).show();
                    }
                });


            }*/

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            getMyLocation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getMyLocation() {

        appUtils.getLocation();

    }

    public void getCurrentWeather(String currentURL) {

        if (appUtils.isOnline()) {

            try {

                JSONObject mainJsonObject = new JSONObject(appUtils.loadJSON(currentURL));

                /*
                * For Sunset and Sunrise
                 */
                JSONObject sysJsonObject = mainJsonObject.getJSONObject("sys");
                if (sysJsonObject != null) {

                    String sunRise = sysJsonObject.getString("sunrise") + "000";
                    String sunSet = sysJsonObject.getString("sunset") + "000";
                    Log.d("First Data : ", String.valueOf(sunRise) + "---" + String.valueOf(sunSet));

                }

                /*
                * For Weather Description and IconUrl
                 */
                JSONArray weatherJSONArray = mainJsonObject.getJSONArray("weather");
                if (weatherJSONArray != null) {

                    for (int i = 0; i < weatherJSONArray.length(); i++) {

                        JSONObject object = weatherJSONArray.getJSONObject(i);
                        if (object != null) {

                            String weatherDescription = object.getString("description");
                            String weatherIcon_URL = object.getString("icon");
                            String weatherURL = COMMON_IMAGE_URL + weatherIcon_URL;
                            Log.d("Descriptions : ", weatherDescription + "----" + weatherURL);

                        }
                    }

                }

                /*
                * For Main Weather values
                 */
                JSONObject mainTempJSONObject = mainJsonObject.getJSONObject("main");
                if (mainTempJSONObject != null) {
                    String temp = mainTempJSONObject.getString("temp");
                    String temp_min = mainTempJSONObject.getString("temp_min");
                    Double Double_temp_min = Double.valueOf(mainTempJSONObject.getString("temp_min"));
                    Double convertedTemp = Double_temp_min - 273.150;
                    long result_value = Math.round(convertedTemp);
                    String temp_max = mainTempJSONObject.getString("temp_max");
                    String pressure = mainTempJSONObject.getString("pressure");
                    String humidity = mainTempJSONObject.getString("humidity");

                    Log.d("Main Data : " + "\n", Double_temp_min + "\n" + String.valueOf(convertedTemp) + "\n" + String.valueOf(result_value) + "\n" + temp + "\n" + temp_min + "\n" + temp_max + "\n" + pressure + "\n" + humidity);

                }

                /*
                * For wind Speed
                 */
                JSONObject windJSONObject = mainJsonObject.getJSONObject("wind");
                if (windJSONObject != null) {
                    String windSpeed = windJSONObject.getString("speed");
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        } else {

            Toast.makeText(context, "No Data Connection", Toast.LENGTH_SHORT).show();

        }
    }

}
