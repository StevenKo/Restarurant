package com.restaurant.collection;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.adapter.RestaurantGridViewAdapter;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Restaurant;
import com.restaurant.customized.view.LoadMoreGridView;
import com.restaurant.gps.util.GPSTracker;
import com.viewpagerindicator.TitlePageIndicator;

public class SearchActivity extends SherlockFragmentActivity{
	
	private ArrayList<Restaurant> restaurants     = new ArrayList<Restaurant>();
    private ArrayList<Restaurant> moreRestaurants = new ArrayList<Restaurant>();

    private RestaurantGridViewAdapter                   myGridViewAdapter;
	private ViewPager pager;
	private LinearLayout progressLayout;
	private LinearLayout loadmoreLayout;
	private LinearLayout layoutReload;
	private Button buttonReload;
	private LinearLayout noDataLayout;
	private LoadMoreGridView myGrid;
	private int                               myPage          = 1;
    private Boolean                           checkLoad       = true;
	private double latitude;
	private double longitude;
	private Bundle mBundle;
	private String keyword;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadmore_grid);

        final ActionBar ab = getSupportActionBar();
        mBundle = this.getIntent().getExtras();
        keyword = mBundle.getString("SearchKeyword");
        ab.setTitle("搜尋餐廳:"+keyword);
        ab.setDisplayHomeAsUpEnabled(true);
        
        getCurrentXY();
        setViews();
        new DownloadRestaurantsTask().execute();
        
	}
	
	private void getCurrentXY(){
		GPSTracker mGPS = new GPSTracker(SearchActivity.this);
    	if(mGPS.canGetLocation() ){
    		latitude =mGPS.getLatitude();
    		longitude=mGPS.getLongitude();
    	}else{
    	// can't get the location
    	}
	}
	
	private void setViews() {
        progressLayout = (LinearLayout) findViewById(R.id.layout_progress);
        loadmoreLayout = (LinearLayout) findViewById(R.id.load_more_grid);
        layoutReload = (LinearLayout) findViewById(R.id.layout_reload);
        buttonReload = (Button) findViewById(R.id.button_reload);
        noDataLayout = (LinearLayout) findViewById(R.id.layout_no_data);

        myGrid = (LoadMoreGridView) findViewById(R.id.news_list);
        myGrid.setOnLoadMoreListener(new LoadMoreGridView.OnLoadMoreListener() {

			public void onLoadMore() {
                if (checkLoad) {
                    myPage = myPage + 1;
                    new LoadMoreTask().execute();
                } else {
                    myGrid.onLoadMoreComplete();
                }
            }
        });

        buttonReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                progressLayout.setVisibility(View.VISIBLE);
                layoutReload.setVisibility(View.GONE);
                new DownloadRestaurantsTask().execute();
            }
        });

        if (myGridViewAdapter != null) {
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);
            myGrid.setAdapter(myGridViewAdapter);
        } else {
            new DownloadRestaurantsTask().execute();
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
	
	private class DownloadRestaurantsTask extends AsyncTask {

		@Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object... params) {
        	restaurants = RestaurantAPI.searchRestaurants(keyword, 1);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);

            if (restaurants != null && restaurants.size() != 0) {
                try {
                    layoutReload.setVisibility(View.GONE);
                    myGridViewAdapter = new RestaurantGridViewAdapter(SearchActivity.this, restaurants,true);
                    myGrid.setAdapter(myGridViewAdapter);
                    new DownloadRestaurantsDistanceTask().execute();
                } catch (Exception e) {

                }
            } else if(restaurants != null && restaurants.size() == 0){
            	noDataLayout.setVisibility(View.VISIBLE);
            	layoutReload.setVisibility(View.GONE);
            }else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }

    private class LoadMoreTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadmoreLayout.setVisibility(View.VISIBLE);

        }

        @Override
        protected Object doInBackground(Object... params) {

        	moreRestaurants = RestaurantAPI.searchRestaurants(keyword, myPage);
            for (int i = 0; i < moreRestaurants.size(); i++) {
            	restaurants.add(moreRestaurants.get(i));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            loadmoreLayout.setVisibility(View.GONE);

            if (moreRestaurants != null && moreRestaurants.size() != 0) {
                myGridViewAdapter.notifyDataSetChanged();
                new DownloadRestaurantsDistanceTask().execute();
            } else {
                checkLoad = false;
                Toast.makeText(SearchActivity.this, "no more data", Toast.LENGTH_SHORT).show();
            }
            myGrid.onLoadMoreComplete();

        }
    }
    
    private class DownloadRestaurantsDistanceTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object... arg0) {
			restaurants = RestaurantAPI.getRestaurantsDistance(latitude, longitude, restaurants);
			return null;
		}
		
		@Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            loadmoreLayout.setVisibility(View.GONE);
            myGridViewAdapter.notifyDataSetChanged();
            
        }
    }

}
