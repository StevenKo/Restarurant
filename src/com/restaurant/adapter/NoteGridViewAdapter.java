package com.restaurant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurant.collection.R;
import com.restaurant.collection.RestaurantIntroActivity;
import com.restaurant.collection.RestaurantNoteActivity;
import com.restaurant.collection.entity.Note;
import com.tool.imageload.ImageLoader;

public class NoteGridViewAdapter extends BaseAdapter {

    private final Activity        activity;
    private final ArrayList<Note> data;
    private static LayoutInflater inflater = null;
    public ImageLoader            imageLoader;

    public NoteGridViewAdapter(Activity a, ArrayList<Note> notes) {
        activity = a;
        data = notes;
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
            vi = inflater.inflate(R.layout.item_gridview_note, null);
        } else {
            vi = inflater.inflate(R.layout.item_gridview_note, null);
        }

        vi.setClickable(true);
        vi.setFocusable(true);
        vi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(activity, RestaurantNoteActivity.class);
            	activity.startActivity(intent);
            }

        });

        ImageView noteImage = (ImageView) vi.findViewById(R.id.grid_item_image);
        TextView noteText = (TextView) vi.findViewById(R.id.grid_item_text);
        noteText.setText(data.get(position).getTitle());
        
        String picUrl = data.get(position).getPicUrl();
        if (picUrl == null || picUrl.equals("null") ) {
        	noteImage.setImageResource(R.drawable.icon);
        } else {
            imageLoader.DisplayImage(picUrl, noteImage);
        }

        return vi;
    }
}
