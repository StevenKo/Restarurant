package com.restaurant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurant.collection.R;
import com.restaurant.collection.entity.Area;
import com.restaurant.collection.entity.Category;

public class CategoryListAdapter extends BaseAdapter {

    private final Activity        activity;
    private final  ArrayList<Category>       data;
    private static LayoutInflater inflater = null;

    public CategoryListAdapter(Activity a, ArrayList<Category> cs) {
        activity = a;
        data = cs;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item_listview_category, null);
        TextView text = (TextView) vi.findViewById(R.id.text_list);
        text.setText(data.get(position).getName());

        return vi;
    }
}