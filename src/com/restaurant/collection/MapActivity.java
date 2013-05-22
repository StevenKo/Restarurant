package com.restaurant.collection;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends SherlockFragmentActivity implements OnMarkerClickListener, OnInfoWindowClickListener {
	
	private GoogleMap map;
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("地圖顯示");
        
        setMap();
    }
    
    
    
    private void setMap() {
    	if (map!=null){
    	      Marker kiel = map.addMarker(new MarkerOptions()
    	          .position(KIEL)
    	          .title("Kiel")
    	          .snippet("Kiel is cool")
    	          .icon(BitmapDescriptorFactory
    	              .fromResource(R.drawable.ic_launcher)));
    	      kiel.showInfoWindow();
    	    }
    	
    	map.setOnMarkerClickListener(this);
    	map.setOnInfoWindowClickListener(this);
    
		
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
		Intent intent = new Intent(this, RestaurantIntroActivity.class);
    	this.startActivity(intent);
		return false;
	}



	@Override
	public void onInfoWindowClick(Marker arg0) {
		Intent intent = new Intent(this, RestaurantIntroActivity.class);
    	this.startActivity(intent);
	}

}
