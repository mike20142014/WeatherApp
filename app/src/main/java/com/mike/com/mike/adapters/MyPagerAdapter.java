package com.mike.com.mike.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike.app.R;
import com.mike.imagedownloadutil.ImageLoader;
import com.mike.model.AppModel;

import java.util.ArrayList;

/**
 * Created by MichaelHenry on 5/15/14.
 */
public class MyPagerAdapter extends PagerAdapter {

    Context context;
    public ImageLoader imageLoader;

    private ArrayList<AppModel> descriptionArrayData;
    private ArrayList<AppModel> timeArrayData;
    private ArrayList<AppModel> maxTempArrayData;
    private ArrayList<AppModel> minTempArrayData;
    private ArrayList<AppModel> pressureArrayData;
    private ArrayList<AppModel> humidityArrayData;
    private ArrayList<AppModel> windArrayData;
    private ArrayList<String> weatherImageArrayData;

    public MyPagerAdapter(Context context, ArrayList<AppModel> timeData,ArrayList<String> imageData,ArrayList<AppModel> descriptionArrayData,ArrayList<AppModel> maxTempArrayData,ArrayList<AppModel> minTempArrayData,ArrayList<AppModel> pressureArrayData,ArrayList<AppModel> humidityArrayData,ArrayList<AppModel> windArrayData){

        super();
        this.timeArrayData = timeData;
        this.weatherImageArrayData = imageData;
        this.descriptionArrayData = descriptionArrayData;
        this.maxTempArrayData = maxTempArrayData;
        this.minTempArrayData = minTempArrayData;
        this.pressureArrayData = pressureArrayData;
        this.humidityArrayData = humidityArrayData;
        this.windArrayData = windArrayData;
        imageLoader = new ImageLoader(context.getApplicationContext());


    }


    @Override
    public int getCount() {
        return timeArrayData.size();
    }

    @Override
    public boolean isViewFromObject(View collection, Object object) {
        return collection == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

            ViewHolder holder;

            LayoutInflater inflater = (LayoutInflater) container.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = inflater.inflate(R.layout.hourly_forecast_pager_items, null);

            holder = new ViewHolder();

                holder.image = (ImageView) convertView.findViewById(R.id.hourly_item_weatherIcon_ImageView);
                holder.timeText = (TextView) convertView.findViewById(R.id.hourly_item_timeTextView);
                holder.maxText = (TextView) convertView.findViewById(R.id.hourly_item_maxtempTextView);
                holder.minText = (TextView) convertView.findViewById(R.id.hourly_item_mintempTextView);
                holder.pressureText = (TextView) convertView.findViewById(R.id.hourly_item_pressureTextView);
                holder.humidityText = (TextView) convertView.findViewById(R.id.hourly_item_humidityTextView);
                holder.descText = (TextView) convertView.findViewById(R.id.hourly_item_descTextView);
                holder.windText = (TextView) convertView.findViewById(R.id.hourly_item_windSpeedTextView);

        ImageView weatherIcon = holder.image;
        TextView timeTextView = holder.timeText;
        TextView maxTempTextView = holder.maxText;
        TextView minTempTextView = holder.minText;
        TextView pressureTextView = holder.pressureText;
        TextView humidityTextView = holder.humidityText;
        TextView descriptionTextView = holder.descText;
        TextView windSpeedTextView = holder.windText;

        try{

            imageLoader.DisplayImage(weatherImageArrayData.get(position),R.drawable.circleimg,weatherIcon);
            timeTextView.setText(timeArrayData.get(position).getHourlyTime());
            maxTempTextView.setText(maxTempArrayData.get(position).getHourlyTime() + " °C ");
            minTempTextView.setText(minTempArrayData.get(position).getHourlyTime() + " °C ");
            pressureTextView.setText(pressureArrayData.get(position).getHourlyTime() + " hPa");
            humidityTextView.setText(humidityArrayData.get(position).getHourlyTime() + "%");
            descriptionTextView.setText(descriptionArrayData.get(position).getHourlyTime());
            windSpeedTextView.setText(windArrayData.get(position).getHourlyTime() + " mps");

        }catch (Exception e){

            e.printStackTrace();
        }
        ((ViewPager) container).addView(convertView, 0);

        return convertView;
    }

    public static class ViewHolder {

        public ImageView image;
        public TextView timeText, maxText, minText, descText, windText, humidityText, pressureText;

    }

    @Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((View) view);
    }
}
