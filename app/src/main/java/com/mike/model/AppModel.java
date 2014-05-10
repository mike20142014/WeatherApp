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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public String getCurrentZip() {
        return currentZip;
    }

    public void setCurrentZip(String currentZip) {
        this.currentZip = currentZip;
    }
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

    public String getCurrentDescription() {
        return currentDescription;
    }

    public void setCurrentDescription(String currentDescription) {
        this.currentDescription = currentDescription;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getCurrentMaxTemp() {
        return currentMaxTemp;
    }

    public void setCurrentMaxTemp(String currentMaxTemp) {
        this.currentMaxTemp = currentMaxTemp;
    }

    public String getCurrentMinTemp() {
        return currentMinTemp;
    }

    public void setCurrentMinTemp(String currentMinTemp) {
        this.currentMinTemp = currentMinTemp;
    }

    public String getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(String currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public String getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCurrentWindSpeed(String currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    public String getCurrentIcon() {
        return currentIcon;
    }

    public void setCurrentIcon(String currentIcon) {
        this.currentIcon = currentIcon;
    }

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

    public String getHourlyWeatherDescription() {
        return hourlyWeatherDescription;
    }

    public void setHourlyWeatherDescription(String hourlyWeatherDescription) {
        this.hourlyWeatherDescription = hourlyWeatherDescription;
    }

    public String getHourlyTemp() {
        return hourlyTemp;
    }

    public void setHourlyTemp(String hourlyTemp) {
        this.hourlyTemp = hourlyTemp;
    }

    public String getHourlyMaxTemp() {
        return hourlyMaxTemp;
    }

    public void setHourlyMaxTemp(String hourlyMaxTemp) {
        this.hourlyMaxTemp = hourlyMaxTemp;
    }

    public String getHourlyMinTemp() {
        return hourlyMinTemp;
    }

    public void setHourlyMinTemp(String hourlyMinTemp) {
        this.hourlyMinTemp = hourlyMinTemp;
    }

    public String getHourlyHumidity() {
        return hourlyHumidity;
    }

    public void setHourlyHumidity(String hourlyHumidity) {
        this.hourlyHumidity = hourlyHumidity;
    }

    public String getHourlyWindSpeed() {
        return hourlyWindSpeed;
    }

    public void setHourlyWindSpeed(String hourlyWindSpeed) {
        this.hourlyWindSpeed = hourlyWindSpeed;
    }

    public String getHourlyTime() {
        return hourlyTime;
    }

    public void setHourlyTime(String hourlyTime) {
        this.hourlyTime = hourlyTime;
    }

    public String getHourlyIcon() {
        return hourlyIcon;
    }

    public void setHourlyIcon(String hourlyIcon) {
        this.hourlyIcon = hourlyIcon;
    }



    /*
    * For daily Weather forecast
     */

    private String dailyDayTemp;
    private String dailyMorningTemp;
    private String dailyEveningTemp;
    private String dailyNightTemp;
    private String dailyMaxTemp;
    private String dailyMinTemp;
    private String dailyIcon;
    private String dailyDescription;
    private String dailyPressure;
    private String dailyHumidity;

    public String getDailyDayTemp() {
        return dailyDayTemp;
    }

    public void setDailyDayTemp(String dailyDayTemp) {
        this.dailyDayTemp = dailyDayTemp;
    }

    public String getDailyMorningTemp() {
        return dailyMorningTemp;
    }

    public void setDailyMorningTemp(String dailyMorningTemp) {
        this.dailyMorningTemp = dailyMorningTemp;
    }

    public String getDailyEveningTemp() {
        return dailyEveningTemp;
    }

    public void setDailyEveningTemp(String dailyEveningTemp) {
        this.dailyEveningTemp = dailyEveningTemp;
    }

    public String getDailyNightTemp() {
        return dailyNightTemp;
    }

    public void setDailyNightTemp(String dailyNightTemp) {
        this.dailyNightTemp = dailyNightTemp;
    }

    public String getDailyMaxTemp() {
        return dailyMaxTemp;
    }

    public void setDailyMaxTemp(String dailyMaxTemp) {
        this.dailyMaxTemp = dailyMaxTemp;
    }

    public String getDailyMinTemp() {
        return dailyMinTemp;
    }

    public void setDailyMinTemp(String dailyMinTemp) {
        this.dailyMinTemp = dailyMinTemp;
    }

    public String getDailyIcon() {
        return dailyIcon;
    }

    public void setDailyIcon(String dailyIcon) {
        this.dailyIcon = dailyIcon;
    }

    public String getDailyDescription() {
        return dailyDescription;
    }

    public void setDailyDescription(String dailyDescription) {
        this.dailyDescription = dailyDescription;
    }

    public String getDailyPressure() {
        return dailyPressure;
    }

    public void setDailyPressure(String dailyPressure) {
        this.dailyPressure = dailyPressure;
    }

    public String getDailyHumidity() {
        return dailyHumidity;
    }

    public void setDailyHumidity(String dailyHumidity) {
        this.dailyHumidity = dailyHumidity;
    }



    public AppModel(Context context){

        super();
        this.context = context;

    }

}
