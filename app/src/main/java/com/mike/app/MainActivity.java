package com.mike.app;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mike.utils.AppUtils;

public class MainActivity extends Activity{

    Context context;
    public static AppUtils appUtils;

    public static final String APP_ID = "889673151059bed61a35b1f33f5488a6";

    public static final String CURRENT_WEATHER_URL_MAIN = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139";
    public static final String THREE_HOUR_FORECAST_URL_MAIN = "http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139";
    public static final String TEN_DAY_FORECAST_URL_MAIN = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=10&mode=json";

    public static String CURRENT_WEATHER_URL_MAIN_BREAK1 = "http://api.openweathermap.org/data/2.5/weather?";
    public static String THREE_HOUR_FORECAST_URL_MAIN_BREAK1 = "http://api.openweathermap.org/data/2.5/forecast?";
    public static String TEN_DAY_FORECAST_URL_MAIN_BREAK1 = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    public static String TEN_DAY_FORECAST_URL_MAIN_BREAK2 = "&cnt=10&mode=json";

    public static String CURRENT_LAT,CURRENT_LON;
    public static String TEMP_URL;
    public static String FINAL_URL;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        appUtils = new AppUtils(context);
        getMyLocation();
        //new BackgroundTask().execute();

    }

    public class BackgroundTask extends AsyncTask<Void,Void,Void>{

        ProgressDialog pDialog;

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
            if(appUtils.isOnline()){

                /**
                 * Don't use if network exists!!
                 */

                runOnUiThread(new Runnable() {
                    public void run() {
                        //getLocation();
                        //Toast.makeText(context, "Network Available!", Toast.LENGTH_LONG).show();
                    }
                });


            }else{

                /**
                 * Only to show toast or dialog!!Not for heavy duty!
                 */
                runOnUiThread(new Runnable() {
                    public void run() {

                        Toast.makeText(context, "Network not Available!", Toast.LENGTH_LONG).show();
                    }
                });


            }

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

    public void getMyLocation(){

            appUtils.getLocation();

    }

}
