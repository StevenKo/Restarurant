package com.restaurant.collection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.collection.db.SQLiteRestaurant;
import com.restaurant.fragment.RestaurantPhotoFragment;
import com.viewpagerindicator.CirclePageIndicator;

public class RestaurantIntroActivity extends SherlockFragmentActivity {
    private ViewPager pager;
	private RelativeLayout watch_notes;
	private ImageButton share_btn;
	private ImageButton direction_button;
	private ImageButton place_button;
	private ImageButton favorite_button;
	private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_restaurant_intro);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("餐廳介紹");
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentPagerAdapter adapter = new PhotoPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        
        findViews();
        setViews();
        
    }

    private void setViews() {
    	watch_notes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RestaurantIntroActivity.this, RestaurantNotesActivity.class);
                startActivity(intent);
            }
        });
    	share_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_SEND);
            	intent.setType("text/plain");
            	intent.putExtra(android.content.Intent.EXTRA_TEXT, "News for you!");
            	startActivity(intent); 
            }
        });
    	place_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Uri uri = Uri.parse("geo:0,0?q=22.99948365856307,72.60040283203125(Maninagar)");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
            }
        });
    	direction_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_VIEW,
            			Uri.parse("http://maps.google.com/maps?saddr="+23.0094408+","+72.5988541+"&daddr="+22.99948365856307+","+72.60040283203125));
            			startActivity(intent);
            }
        });
    	favorite_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	restaurantId = 1;
            	SQLiteRestaurant db = new SQLiteRestaurant(RestaurantIntroActivity.this);
            	if (db.isRestaurantCollected(restaurantId)){
            		favorite_button.setImageResource( R.drawable.icon_heart_grey );
            	}else{
            		favorite_button.setImageResource( R.drawable.icon_heart );
            	}
            
            }
        });
	}

	private void findViews() {
		watch_notes = (RelativeLayout)findViewById(R.id.watch_notes);
		share_btn = (ImageButton)findViewById(R.id.share_button);
		direction_button = (ImageButton)findViewById(R.id.direction_button);
		place_button = (ImageButton)findViewById(R.id.place_button);
		favorite_button = (ImageButton)findViewById(R.id.favorite_button);
	}

	class PhotoPagerAdapter extends FragmentPagerAdapter {

        public PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = RestaurantPhotoFragment.newInstance();
            return kk;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
        case android.R.id.home:
            finish();
            break;
        }
        return true;
    }

}
