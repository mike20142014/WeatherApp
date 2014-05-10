package com.mike.model;

import android.content.Context;

/**
 * Created by MichaelHenry on 5/10/14.
 */
public class AppModel {

    Context context;
    private Double latitude;
    private Double longitude;
    String currentCity;
    String currentCountry;
    String currentZip;

    public AppModel(Context context){

        super();
        this.context = context;

    }

}
