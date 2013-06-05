package com.restaurant.adapter;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurant.collection.CategoryActivity;
import com.restaurant.collection.R;
import com.restaurant.collection.RestaurantIntroActivity;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Restaurant;
import com.tool.imageload.ImageLoader;


public class RestaurantGridViewAdapter extends BaseAdapter {

    private final Activity                    activity;
    private final ArrayList<Restaurant> data;
    private static LayoutInflater             inflater = null;
    public ImageLoader                        imageLoader;
    private boolean isShowDistance;

    public RestaurantGridViewAdapter(Activity a, ArrayList<Restaurant> restaurants, boolean isShowDistance) {
        activity = a;
        data = restaurants;
        this.isShowDistance = isShowDistance;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext(), 70);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth(); // deprecated
        int height = display.getHeight(); // deprecated

        if (width > 480) {
        	if(isShowDistance)
        		vi = inflater.inflate(R.layout.item_gridview_restaurant, null);
        	else
        		vi = inflater.inflate(R.layout.item_gridview_restaurant_no_distance, null);
        } else {
        	if(isShowDistance)
        		vi = inflater.inflate(R.layout.item_gridview_restaurant_small, null);
        	else
        		vi = inflater.inflate(R.layout.item_gridview_restaurant_small_no_distance, null);
        }

        vi.setClickable(true);
        vi.setFocusable(true);
        vi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	
            	Intent intent = new Intent(activity, RestaurantIntroActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("ResturantId", data.get(position).getId());
            	bundle.putString("ResturantName", data.get(position).getName());
            	intent.putExtras(bundle);
            	activity.startActivity(intent);

            }

        });

        TextView title = (TextView) vi.findViewById(R.id.grid_item_title);
        ImageView image = (ImageView) vi.findViewById(R.id.grid_item_image);
        TextView foodScore = (TextView) vi.findViewById(R.id.grid_food_score);
        TextView serviceScore = (TextView) vi.findViewById(R.id.grid_service_score);
        TextView price = (TextView) vi.findViewById(R.id.grid_item_price);
        if(isShowDistance){
        	TextView dis = (TextView) vi.findViewById(R.id.grid_item_dis);
            dis.setText(data.get(position).getDis());
        }
          
        String picUrl = data.get(position).getPicUrl();
        title.setText(data.get(position).getName());
        foodScore.setText(data.get(position).getGradeFood());
        serviceScore.setText(data.get(position).getGradeService());
        price.setText(data.get(position).getPrice());
        
        if (picUrl == null || picUrl.equals("null") || picUrl.equals("http://iphoto.ipeen.com.tw/images/search/piczone140.jpg") ) {
            image.setImageResource(R.drawable.icon);
        } else {
            imageLoader.DisplayImage(picUrl, image);
        }

        return vi;
    }
}
