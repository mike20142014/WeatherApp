package com.mike.com.mike.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike.app.R;
import com.mike.imagedownloadutil.ImageLoader;
import com.mike.model.AppModel;

import java.util.ArrayList;

/**
 * Created by MichaelHenry on 5/14/14.
 */
public class ViewPagerAdapter extends BaseAdapter {

    private int lastPosition = -1;
    private Context context;
    public ImageLoader imageLoader;

    private ArrayList<AppModel> descriptionArrayData;
    private ArrayList<AppModel> timeArrayData;
    private ArrayList<AppModel> maxTempArrayData;
    private ArrayList<AppModel> minTempArrayData;
    private ArrayList<AppModel> pressureArrayData;
    private ArrayList<AppModel> humidityArrayData;
    private ArrayList<AppModel> windArrayData;
    private ArrayList<AppModel> weatherImageArrayData;

    public ViewPagerAdapter(Context context, ArrayList<AppModel> timeData, ArrayList<AppModel> descData, ArrayList<AppModel> maxTempData, ArrayList<AppModel> minTempData, ArrayList<AppModel> pressureData, ArrayList<AppModel> humidityData, ArrayList<AppModel> windData, ArrayList<AppModel> weatherImageData) {
        super();
        this.context = context;
        this.timeArrayData = timeData;
        this.descriptionArrayData = descData;
        this.maxTempArrayData = maxTempData;
        this.minTempArrayData = minTempData;
        this.windArrayData = windData;
        this.pressureArrayData = pressureData;
        this.humidityArrayData = humidityData;
        this.weatherImageArrayData = weatherImageData;

        imageLoader = new ImageLoader(context);

    }

    public ViewPagerAdapter(Context context, ArrayList<AppModel> timeData){
        super();
        this.timeArrayData = timeData;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {

        return timeArrayData.size();
    }

    @Override
    public Object getItem(int position) {

        return timeArrayData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hourly_forecast_pager_items, null);

            holder = new ViewHolder();

            if (convertView != null) {

                holder.image = (ImageView) convertView.findViewById(R.id.hourly_item_weatherIcon_ImageView);
                holder.timeText = (TextView) convertView.findViewById(R.id.hourly_item_timeTextView);
                holder.maxText = (TextView) convertView.findViewById(R.id.hourly_item_maxtempTextView);
                holder.minText = (TextView) convertView.findViewById(R.id.hourly_item_mintempTextView);
                holder.pressureText = (TextView) convertView.findViewById(R.id.hourly_item_pressureTextView);
                holder.humidityText = (TextView) convertView.findViewById(R.id.hourly_item_humidityTextView);
                holder.descText = (TextView) convertView.findViewById(R.id.hourly_item_descTextView);
                holder.windText = (TextView) convertView.findViewById(R.id.hourly_item_windSpeedTextView);

            }
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageView weatherIcon = holder.image;
        TextView timeTextView = holder.timeText;
        TextView maxTempTextView = holder.maxText;
        TextView minTempTextView = holder.minText;
        TextView pressureTextView = holder.pressureText;
        TextView humidityTextView = holder.humidityText;
        TextView descriptionTextView = holder.descText;
        TextView windSpeedTextView = holder.windText;

        imageLoader.DisplayImage(weatherImageArrayData.get(position).getHourlyIcon(), R.drawable.circleimg, weatherIcon);
        timeTextView.setText(timeArrayData.get(position).getHourlyTime());
        maxTempTextView.setText(maxTempArrayData.get(position).getHourlyMaxTemp());
        minTempTextView.setText(minTempArrayData.get(position).getHourlyMinTemp());
        pressureTextView.setText(pressureArrayData.get(position).getHourlyPressure());
        humidityTextView.setText(humidityArrayData.get(position).getHourlyHumidity());
        descriptionTextView.setText(descriptionArrayData.get(position).getHourlyWeatherDescription());
        windSpeedTextView.setText(windArrayData.get(position).getHourlyWindSpeed());

        return convertView;
    }

    public static class ViewHolder {

        public ImageView image;
        public TextView timeText, maxText, minText, descText, windText, humidityText, pressureText;

    }

}
