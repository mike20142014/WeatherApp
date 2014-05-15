/*
package com.mike.imagedownloadutil;

*/
/**
 * Created by MichaelHenry on 4/21/14.
 *//*


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike.app.R;

import java.util.ArrayList;

//Adapter class extends with BaseAdapter and implements with OnClickListener
public class LazyImageLoadAdapter extends BaseAdapter implements OnClickListener {

    private Context activity;
    private ArrayList<String> arrayListData;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;
    String[] data;
    int prevPosition = -1;

    public LazyImageLoadAdapter(Context context, String[] d) {
        super();
        this.activity = context;
        this.data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache
        imageLoader = new ImageLoader(activity);
    }

    public LazyImageLoadAdapter(Context context, ArrayList<String> arrayList) {

        super();
        this.activity = context;
        this.arrayListData = arrayList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity);

    }


    public int getCount() {
        //return data.length;
        return arrayListData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    */
/**
     * ****** Create a holder Class to contain inflated xml file elements ********
     *//*

    public static class ViewHolder {
        public ImageView image;
        public TextView maxTempTextView, minTempTextView, realFeelTextView;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        final Animation animAlpha = AnimationUtils.loadAnimation(activity,
                R.anim.anim_alpha);

        if (convertView == null) {

            */
/****** Inflate tabitem.xml file for each row ( Defined below ) *******//*

            vi = inflater.inflate(R.layout.grid_item, null);

            */
/****** View Holder Object to contain tabitem.xml file elements ******//*


            holder = new ViewHolder();

            if (vi != null) {

                holder.image = (ImageView) vi.findViewById(R.id.imageViewgridItem);
                holder.maxTempTextView = (TextView) vi.findViewById(R.id.maxTempTextView);
                holder.minTempTextView = (TextView) vi.findViewById(R.id.mixTempTextView);
                holder.realFeelTextView = (TextView) vi.findViewById(R.id.feelTextView);

            }
            */
/************  Set holder with LayoutInflater ************//*

            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        ImageView image = holder.image;
        TextView maxT = holder.maxTempTextView;
        TextView minT = holder.minTempTextView;
        TextView feelT = holder.realFeelTextView;

        //DisplayImage function from ImageLoader Class
        //imageLoader.DisplayImage(data[position],R.drawable.ic_launcher, image);

        imageLoader.DisplayImage(arrayListData.get(position), R.drawable.ic_launcher, image);
        */
/*maxT.setText("A");
        minT.setText("B");
        feelT.setText("C");*//*

        //image.startAnimation(animAlpha);

        */
/******** Set Item Click Listner for LayoutInflater for each row ***********//*

        vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }


    */
/**
     * ****** Called when Item click in ListView ***********
     *//*

    private class OnItemClickListener implements OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            //CurrentActivity sct = (CurrentActivity)activity;
            //sct.onItemClick(mPosition);
        }
    }
}*/
