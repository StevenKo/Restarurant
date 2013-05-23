package com.restaurant.collection;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.restaurant.adapter.RestaurantGridViewAdapter;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.db.SQLiteRestaurant;
import com.restaurant.collection.entity.Restaurant;
import com.restaurant.gps.util.GPSTracker;

public class MapActivity extends SherlockFragmentActivity implements OnMarkerClickListener, OnInfoWindowClickListener {
	
	private GoogleMap map;
	private double latitude;
	private double longitude;
//	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
//	static final LatLng KIEL = new LatLng(53.551, 9.993);
	ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	private Bundle mBundle;
	private int areaId;
	private int categoryId;
	private int typeId;
	private boolean isColletion;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("地圖顯示");
        
        mBundle = this.getIntent().getExtras();
        areaId = mBundle.getInt("AreaId");
        categoryId = mBundle.getInt("CategoryId");
        typeId = mBundle.getInt("TypeId");
        isColletion = mBundle.getBoolean("IsColletion");
        
        new DownloadRestaurantsTask().execute();
        
    }
    
    private class DownloadRestaurantsTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object... params) {
        	if(categoryId != 0){
        		restaurants = RestaurantAPI.getAreaCategoryRestaurants(areaId, categoryId, 1);
        	}else if(typeId != 0){
        		restaurants = RestaurantAPI.getAreaTypeRestaurants(areaId, typeId, 1);
        	}else if(areaId != 0){
        		restaurants = RestaurantAPI.getAreaRestaurants(areaId);
        	}else if(isColletion){
        		SQLiteRestaurant db = new SQLiteRestaurant(MapActivity.this);
        		restaurants = db.getAllRestaurants();
        	}else{
        	   restaurants = RestaurantAPI.getAllRestaurant();  
        	}
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            getCameraMoveLocation();
            setMap();
        }
    }
    
    private void getCameraMoveLocation() {
    	if(areaId !=0){
    		double sumX = 0.0;
    		double sumY = 0.0;
    		for(int i=0; i < restaurants.size(); i++){
    			sumX += restaurants.get(i).getX();
    			sumY += restaurants.get(i).getY();
    		}
    		latitude = sumX/(double)restaurants.size();
    		longitude = sumY/(double)restaurants.size();
    	}else{
    		GPSTracker mGPS = new GPSTracker(this);
        	if(mGPS.canGetLocation() ){
        		latitude =mGPS.getLatitude();
        		longitude=mGPS.getLongitude();
        	}else{
        	// can't get the location
        	}
    	}
    	
		
	}
    
    
    
    private void setMap() {
    	if (map!=null){
    		for(int i=0;i<restaurants.size();i++){
    			LatLng location = new LatLng(restaurants.get(i).getX(), restaurants.get(i).getY());
    			
    			Marker kiel = map.addMarker(new MarkerOptions()
		  	          .position(location)
		  	          .title(restaurants.get(i).getName()));
    		}
    	      
        }
    	
    	map.setOnMarkerClickListener(this);
    	map.setOnInfoWindowClickListener(this);
    	if(isColletion)
    	  map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude) , 8.0f) );
    	else
    	  map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude) , 12.0f) );
		
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



	@Override
	public boolean onMarkerClick(Marker m) {
		
		return false;
	}



	@Override
	public void onInfoWindowClick(Marker m) {
		Intent intent = new Intent(this, RestaurantIntroActivity.class);
		Bundle bundle = new Bundle();
		for(int i =0 ; i < restaurants.size(); i++){
			if(restaurants.get(i).getName().equals( m.getTitle())){
				bundle.putInt("ResturantId", restaurants.get(i).getId());
		    	bundle.putString("ResturantName", restaurants.get(i).getName());
			}
		}
    	intent.putExtras(bundle);
    	this.startActivity(intent);
	}

}
