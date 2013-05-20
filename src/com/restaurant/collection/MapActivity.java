package com.restaurant.collection;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class MapActivity extends SherlockActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("地圖顯示");
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
