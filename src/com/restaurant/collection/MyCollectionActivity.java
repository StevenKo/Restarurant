package com.restaurant.collection;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.collection.CategoryActivity.CategoryPagerAdapter;
import com.restaurant.collection.entity.Category;
import com.restaurant.fragment.CategoryTabFragment;
import com.restaurant.fragment.GridEatNoteFragment;
import com.restaurant.fragment.GridRestaurantsFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class MyCollectionActivity extends SherlockFragmentActivity{
	
	private ViewPager pager;
	private static final int    ID_MAP      = 4;
	private int fragmentPositon = 0;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);

        final ActionBar ab = getSupportActionBar();
        ab.setTitle("我的收藏");
        ab.setDisplayHomeAsUpEnabled(true);
        

        FragmentPagerAdapter adapter = new CategoryPagerAdapter(getSupportFragmentManager());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        
        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
        	
			@Override
        	public void onPageSelected(int position) {
        		fragmentPositon = position;
        		ActivityCompat.invalidateOptionsMenu(MyCollectionActivity.this);
        	}
        });

    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(fragmentPositon==0)
        	menu.add(0, ID_MAP, 4, "餐廳地圖顯示").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
        case android.R.id.home:
            finish();
            break;
        case ID_MAP:
            Intent intent = new Intent(MyCollectionActivity.this, MapActivity.class);
            Bundle bundle = new Bundle();
        	bundle.putBoolean("IsColletion", true);
        	intent.putExtras(bundle);
            startActivity(intent);
            break;
        }
        return true;
    }
	
	class CategoryPagerAdapter extends FragmentPagerAdapter {


        public CategoryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();     
            switch(position){
        	case 0:
        		kk = GridRestaurantsFragment.newInstance(0,0,0,true,false);
        		break;
        	case 1:
        		kk = GridEatNoteFragment.newInstance(0,0,0,true,false);
        		break;
        	}
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	switch(position){
        	case 0:
        		return "我的收藏餐廳";
        	case 1:
        		return "我的收藏食記";
        	}
			return "";
        }

        @Override
        public int getCount() {
            return 2;
        }
	}

}
