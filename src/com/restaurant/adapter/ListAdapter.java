package com.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurant.collection.R;

public class ListAdapter extends BaseAdapter {

    private final Activity        activity;
    // private final ArrayList<String> data;
    private final String[]        data;
    private static LayoutInflater inflater = null;

    public ListAdapter(Activity a, String[] d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.fragment_city_list, null);
        ImageView image = (ImageView) vi.findViewById(R.id.image_list);
        TextView text = (TextView) vi.findViewById(R.id.text_list);
        text.setText(data[position]);

        // try {
        // image.setImageResource(images[position]);
        // } catch (Exception e) {
        // image.setVisibility(View.GONE);
        // }

        return vi;
    }
}