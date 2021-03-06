package com.mike.app;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mike.com.mike.adapters.MyPagerAdapter;
import com.mike.com.mike.adapters.ViewPagerAdapter;
import com.mike.imagedownloadutil.ImageLoader;
import com.mike.model.AppModel;
import com.mike.utils.AppUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CurrentActivity extends Activity {

    Context context;
    public static AppUtils appUtils;
    public static AppModel appModel;
    public ImageLoader imageLoader;


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

    public Calendar calendarRise,calendarSet;

    private ArrayList<AppModel> descriptionArrayData;
    private ArrayList<AppModel> timeArrayData;
    private ArrayList<AppModel> maxTempArrayData;
    private ArrayList<AppModel> minTempArrayData;
    private ArrayList<AppModel> pressureArrayData;
    private ArrayList<AppModel> humidityArrayData;
    private ArrayList<AppModel> windArrayData;
    private ArrayList<String> weatherImageArrayData;
    public static ViewPagerAdapter mViewPagerAdapter;
    public MyPagerAdapter myPagerAdapter;
    public ViewPager hourlyPager;

    TextView current_highTemp_textView;
    TextView current_lowTemp_textView;
    TextView current_pressure_textView;
    TextView current_description_textView;
    TextView current_sunrise_textView;
    TextView current_sunset_textView;
    TextView current_humidity_textView;
    TextView current_windspeed_textView;
    ImageView current_Weather_imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_activity_main);
        context = this;
        init();
        appUtils = new AppUtils(context);
        appModel = new AppModel(context);
        imageLoader = new ImageLoader(context);
        //getMyLocation();

        new BackgroundTask(context,CURRENT_WEATHER_URL_MAIN,THREE_HOUR_FORECAST_URL_MAIN).execute();

    }

    public void init(){

        hourlyPager = (ViewPager)findViewById(R.id.hourly_forecast_pager);
        current_Weather_imageview = (ImageView)findViewById(R.id.current_weather_imageView);
        current_highTemp_textView = (TextView)findViewById(R.id.current_high_tempTextView);
        current_lowTemp_textView = (TextView)findViewById(R.id.current_min_tempTextView);
        current_pressure_textView = (TextView)findViewById(R.id.current_pressureTextView);
        current_description_textView = (TextView)findViewById(R.id.current_description_TextView);
        current_sunrise_textView = (TextView)findViewById(R.id.current_sunrise_TextView);
        current_sunset_textView = (TextView)findViewById(R.id.current_sunset_TextView);
        current_humidity_textView = (TextView)findViewById(R.id.current_humidity_TextView);
        current_windspeed_textView = (TextView)findViewById(R.id.current_windspeed_TextView);

    }

    public void setValues(){

        current_highTemp_textView.setText(appModel.getCurrentMaxTemp());
        current_lowTemp_textView.setText(appModel.getCurrentMinTemp());
        current_pressure_textView.setText(appModel.getCurrentPressure());
        current_description_textView.setText(appModel.getCurrentDescription());
        current_sunrise_textView.setText(appModel.getCurrentSunRise());
        current_sunset_textView.setText(appModel.getCurrentSunSet());
        current_humidity_textView.setText(appModel.getCurrentHumidity());
        current_windspeed_textView.setText(appModel.getCurrentWindSpeed());
        imageLoader.DisplayImage(appModel.getCurrentIcon(),R.drawable.circleimg,current_Weather_imageview);

    }

    public class BackgroundTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        Context AsyncContext;
        String currentURL;
        String hourlyURL;

        public BackgroundTask(Context context1, String CURRENT_SOME_URL, String HOURLY_SOME_URL) {

            super();
            this.AsyncContext = context1;
            this.currentURL = CURRENT_SOME_URL;
            this.hourlyURL = HOURLY_SOME_URL;

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

            getCurrentWeather(currentURL);
            getHourlyForecast(hourlyURL);
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

            myPagerAdapter = new MyPagerAdapter(context,timeArrayData,weatherImageArrayData,descriptionArrayData,maxTempArrayData,minTempArrayData,pressureArrayData,humidityArrayData,windArrayData);
            hourlyPager.setAdapter(myPagerAdapter);
            setValues();
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

    public void getHourlyForecast(String hourlyURL){

        descriptionArrayData = new ArrayList<AppModel>();
        timeArrayData = new ArrayList<AppModel>();
        maxTempArrayData = new ArrayList<AppModel>();
        minTempArrayData = new ArrayList<AppModel>();
        pressureArrayData = new ArrayList<AppModel>();
        humidityArrayData = new ArrayList<AppModel>();
        windArrayData = new ArrayList<AppModel>();
        weatherImageArrayData = new ArrayList<String>();

        if(appUtils.isOnline()){

            try{

                JSONObject mainJsonObject = new JSONObject(appUtils.loadJSON(hourlyURL));
                JSONArray mainJSOJsonArray = mainJsonObject.getJSONArray("list");

                if(mainJSOJsonArray!=null){

                    for(int i =0;i<mainJSOJsonArray.length();i++){

                        JSONObject object = mainJSOJsonArray.getJSONObject(i);

                        if(object!=null){

                            String hourly_time = object.getString("dt_txt");

                            appModel.setHourlyTime(hourly_time);

                            timeArrayData.add(new AppModel(appModel.getHourlyTime()));

                            Log.d("Time Data : ",String.valueOf(appModel.getHourlyTime()));

                            JSONArray innerArray = object.getJSONArray("weather");

                            JSONObject weatherMainObject = object.getJSONObject("main");

                            if(weatherMainObject!=null){


                                String tempMax = weatherMainObject.getString("temp_max");
                                Double Double_temp_max = Double.valueOf(tempMax);
                                Double convertedTemp_MAX = Double_temp_max- 273.150;
                                long result_value_temp_max = Math.round(convertedTemp_MAX);
                                appModel.setHourlyMaxTemp(String.valueOf(result_value_temp_max));
                                maxTempArrayData.add(new AppModel(appModel.getHourlyMaxTemp()));
                                Log.d("Weather Data : ", String.valueOf(appModel.getHourlyMaxTemp()));

                                String tempMin = weatherMainObject.getString("temp_min");
                                Double Double_temp_min = Double.valueOf(tempMin);
                                Double convertedTemp_MIN = Double_temp_min -273.150;
                                long result_value_temp_min = Math.round(convertedTemp_MIN);
                                appModel.setHourlyMinTemp(String.valueOf(result_value_temp_min));
                                minTempArrayData.add(new AppModel(appModel.getHourlyMinTemp()));
                                Log.d("Weather Data : ",String.valueOf(appModel.getHourlyMinTemp()));

                                String pressurE = weatherMainObject.getString("pressure");
                                appModel.setHourlyPressure(pressurE);
                                pressureArrayData.add(new AppModel(appModel.getHourlyPressure()));
                                Log.d("Weather Data : ",String.valueOf(appModel.getHourlyPressure()));

                                String humiditY = weatherMainObject.getString("humidity");
                                appModel.setHourlyHumidity(humiditY);
                                humidityArrayData.add(new AppModel(appModel.getHourlyHumidity()));
                                Log.d("Weather Data : ",String.valueOf(appModel.getHourlyHumidity()));


                            }

                            JSONObject windJSONObject = object.getJSONObject("wind");
                            if(windJSONObject!=null){

                                String windSpeeD = windJSONObject.getString("speed");
                                appModel.setHourlyWindSpeed(windSpeeD);
                                windArrayData.add(new AppModel(appModel.getHourlyWindSpeed()));
                                Log.d("Weather Data : ",String.valueOf(appModel.getHourlyWindSpeed()));

                            }

                            for(int j = 0;j<innerArray.length();j++){

                                JSONObject innerJSONObject = innerArray.getJSONObject(j);

                                if(innerJSONObject!=null){

                                    String weatherID = innerJSONObject.getString("id");

                                    String weatherIcons = innerJSONObject.getString("icon");
                                    String finalImgIcons = COMMON_IMAGE_URL+weatherIcons;

                                    String hourlyDesc = innerJSONObject.getString("description");
                                    appModel.setHourlyWeatherDescription(hourlyDesc);
                                    descriptionArrayData.add(new AppModel(appModel.getHourlyWeatherDescription()));

                                    Log.d("Weather Icon : ", finalImgIcons);
                                    appModel.setHourlyIcon(finalImgIcons);
                                    Log.d("Weather Icon GET : ", appModel.getHourlyIcon());
                                    weatherImageArrayData.add(finalImgIcons);

                                }

                            }

                        }


                    }

                }

            }catch (Exception e){

                e.printStackTrace();
            }

        }else{

            Toast.makeText(context, "No Data Connection", Toast.LENGTH_SHORT).show();

        }

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

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                    long sun_Rise = Long.parseLong(String.valueOf(sunRise));
                    long sun_Set = Long.parseLong(String.valueOf(sunSet));

                    calendarRise = Calendar.getInstance();
                    calendarSet = Calendar.getInstance();

                    calendarRise.setTimeInMillis(sun_Rise);
                    calendarSet.setTimeInMillis(sun_Set);

                    String newSunRise = formatter.format(calendarRise.getTime());
                    String newSunSet = formatter.format(calendarSet.getTime());

                    appModel.setCurrentSunRise(newSunRise);
                    appModel.setCurrentSunSet(newSunSet);

                    Log.d("First Data : ", newSunRise + "---" + newSunSet);

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
                            String weather_icon_id = object.getString("id");
                            String weatherURL = COMMON_IMAGE_URL + weatherIcon_URL;

                            appModel.setCurrentDescription(weatherDescription);
                            appModel.setCurrent_icon_id(weather_icon_id);
                            appModel.setCurrentIcon(weatherURL);

                            Log.d("Descriptions : ", weatherDescription + "---" + weather_icon_id + "----" + weatherURL);

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
                    Double Double_temp_min = Double.valueOf(temp_min);
                    Double converted_MIN_Temp = Double_temp_min - 273.150;
                    long result_min_value = Math.round(converted_MIN_Temp);
                    appModel.setCurrentMinTemp(String.valueOf(result_min_value) + " °C ");

                    String temp_max = mainTempJSONObject.getString("temp_max");
                    Double Double_temp_max = Double.valueOf(temp_max);
                    Double converted_Max_Temp = Double_temp_max - 273.150;
                    long result_max_value = Math.round(converted_Max_Temp);
                    appModel.setCurrentMaxTemp(String.valueOf(result_max_value) + " °C ");

                    String pressure = mainTempJSONObject.getString("pressure");//in hPa
                    appModel.setCurrentPressure(pressure + " hPa ");

                    String humidity = mainTempJSONObject.getString("humidity");//In percentage
                    appModel.setCurrentHumidity(humidity + " % ");

                    /*Log.d("Main Data : " + "\n", String.valueOf(Double_temp_min) + "\n" + String.valueOf(convertedTemp) + "\n" + String.valueOf(result_value) + "\n" + temp + "\n" + temp_min + "\n" + temp_max + "\n" + pressure + "\n" + humidity);*/

                }

                /*
                * For wind Speed
                 */
                JSONObject windJSONObject = mainJsonObject.getJSONObject("wind");
                if (windJSONObject != null) {

                    String windSpeed = windJSONObject.getString("speed");//In mph
                    appModel.setCurrentWindSpeed(windSpeed + "mps");
                    Log.d("Main Data : ",windSpeed);

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        } else {

            Toast.makeText(context, "No Data Connection", Toast.LENGTH_SHORT).show();

        }
    }

}
