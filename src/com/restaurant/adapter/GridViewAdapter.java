package con.restaurant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurant.collection.R;
import com.restaurant.entity.PsuedoRestaurant;
import com.tool.imageload.ImageLoader;

public class GridViewAdapter extends BaseAdapter {

    private final Activity                    activity;
    private final ArrayList<PsuedoRestaurant> data;
    private static LayoutInflater             inflater = null;
    public ImageLoader                        imageLoader;

    public GridViewAdapter(Activity a, ArrayList<PsuedoRestaurant> restaurants) {
        activity = a;
        data = restaurants;
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
            vi = inflater.inflate(R.layout.item_gridview_restaurant, null);
        } else {
            vi = inflater.inflate(R.layout.item_gridview_restaurant_small, null);
        }

        vi.setClickable(true);
        vi.setFocusable(true);
        vi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });

        TextView textTitle = (TextView) vi.findViewById(R.id.grid_item_title);
        ImageView image = (ImageView) vi.findViewById(R.id.grid_item_image);
        TextView textAuthor = (TextView) vi.findViewById(R.id.grid_item_author);
        TextView textDate = (TextView) vi.findViewById(R.id.grid_item_date);

        return vi;
    }
}
