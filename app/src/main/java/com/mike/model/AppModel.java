package com.mike.model;

import android.content.Context;

/**
 * Created by MichaelHenry on 5/10/14.
 */
public class AppModel {

    Context context;
    /**
     * For Location stuff
     */
    private Double latitude;
    private Double longitude;
    private String currentCity;
    private String currentCountry;
    private String currentZip;

    /*
    * For Current Weather condition
     */

    private String currentDescription;
    private String currentTemp;
    private String currentMaxTemp;
    private String currentMinTemp;
    private String currentHumidity;
    private String currentWindSpeed;
    private String currentIcon;

    /*
    * For 3 hour Weather forecast
     */

    private String hourlyWeatherDescription;
    private String hourlyTemp;
    private String hourlyMaxTemp;
    private String hourlyMinTemp;
    private String hourlyHumidity;
    private String hourlyWindSpeed;
    private String hourlyTime;
    private String hourlyIcon;


    public AppModel(Context context){

        super();
        this.context = context;

    }

}
