package com.mike.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

/**
 * Created by MichaelHenry on 5/8/14.
 */
public class AppUtils {

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    public Process p1;
    Context mContext;
    double[] gps;


    public AppUtils() {
    }

    public AppUtils(Context context) {
        super();
        this.mContext = context;

    }

    /**
     *Download and load the json.
     */

    public String loadJSON(String someURL) {

        String json = null;
        HttpClient mHttpClient = new DefaultHttpClient();
        HttpGet mHttpGet = new HttpGet(someURL);

        try {

            HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);
            StatusLine statusline = mHttpResponse.getStatusLine();
            int statusCode = statusline.getStatusCode();
            if (statusCode != 200) {

                return null;

            }
            InputStream jsonStream = mHttpResponse.getEntity().getContent();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(jsonStream));

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {

                builder.append(line);

            }

            json = builder.toString();

        } catch (IOException ex) {

            ex.printStackTrace();

            return null;
        }
        mHttpClient.getConnectionManager().shutdown();
        return json;


    }

    public int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    /**
     *Check internet connectivity by ping
     */
    public Boolean isOnline() {
        try {
            p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            if (reachable) {

                System.out.println("Internet access");
                return reachable;
            } else {

                System.out.println("No Internet access");
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            p1.destroy();
        }
        return false;
    }

    /**
     *Getting the current location by checking all the available network possibilities.
     */
    public double[] getLocation() {

        if (isOnline()) {

            LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            List<String> providers = lm.getProviders(true);

    /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
            Location l = null;
            Location locationGPS = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            for (int i = providers.size() - 1; i >= 0; i--) {
                l = lm.getLastKnownLocation(providers.get(i));
                if (l != null) break;
            }

            gps = new double[2];
            List<Address> list = null;
            Geocoder geocoder = new Geocoder(mContext.getApplicationContext(), Locale.getDefault());
            try {
                list = geocoder.getFromLocation(l.getLatitude(), l.getLongitude(), 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (l != null && list.size() > 0 && list != null) {
                gps[0] = l.getLatitude();
                gps[1] = l.getLongitude();
                String zipCode = list.get(0).getPostalCode();
                String city = list.get(0).getAddressLine(0);
                String city1 = list.get(0).getLocality();
                String result = "\n" + String.valueOf(gps[0]) + "\n" + String.valueOf(gps[1]) + "\n" + "ZIP : " + zipCode + "\n" + "ADDRESS : " + city + "\n" + "LOCALITY : " + city1;
                Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
                Log.d("Data : ", result);

            }else if(locationGPS!=null){

                try {
                    list = geocoder.getFromLocation(locationGPS.getLatitude(),locationGPS.getLongitude(),3);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(list.size()>0&&list !=null){

                    String zipCode = list.get(0).getPostalCode();
                    String city = list.get(0).getAddressLine(0);
                    String city1 = list.get(0).getLocality();
                    String result = "\n" + String.valueOf(gps[0]) + "\n" + String.valueOf(gps[1]) + "\n" + "ZIP : " + zipCode + "\n" + "ADDRESS : " + city + "\n" + "LOCALITY : " + city1;
                    Toast.makeText(mContext, "GPS Data: " + result, Toast.LENGTH_LONG).show();
                    Log.d("Gps Data : ", result);

                }

            }else{

                Toast.makeText(mContext, "No available connections", Toast.LENGTH_LONG).show();

            }

        }else{

            Toast.makeText(mContext, "No Data connection", Toast.LENGTH_LONG).show();
        }
        return gps;
    }
}
