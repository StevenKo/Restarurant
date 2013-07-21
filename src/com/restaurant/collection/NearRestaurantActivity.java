package com.restaurant.collection;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Area;
import com.restaurant.collection.entity.Category;
import com.restaurant.fragment.GridRestaurantsFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class NearRestaurantActivity extends SherlockFragmentActivity{
	private ViewPager pager;
	private PagerAdapter fragmentAdapter;
	private int secondCategoryId = 0;
	private int categoryId = 0;
	private static final int    ID_MAP      = 4;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_near_restaurants);

        final ActionBar ab = getSupportActionBar();
        ab.setTitle("附近餐廳");
        ab.setDisplayHomeAsUpEnabled(true);
        
        pager = (ViewPager) findViewById(R.id.pager);
        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setVisibility(View.GONE);
        setSpinner();
	}
	
	private void setSpinner() {
		Spinner cSpinner = (Spinner)findViewById(R.id.category_spinnner);
		final LinearLayout secondCategoryLayout = (LinearLayout)findViewById(R.id.second_category_layout);
		final Spinner subCatSpinner = (Spinner)findViewById(R.id.second_category_spinnner);
		
		final ArrayList<Category> cs = Category.getCategories();
    	ArrayList<String> categoriessString = new ArrayList<String>();
    	categoriessString.add("全部");
    	for (int i = 0; i < cs.size(); i++) {
    		categoriessString.add(cs.get(i).getName());
		}
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriessString);
        cSpinner.setAdapter(adapter);
        
        cSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
				if(position == 0){
			        fragmentAdapter = new PagerAdapter(getSupportFragmentManager(),0,0);
			        pager.setAdapter(fragmentAdapter);
                    
			        categoryId = 0;
					secondCategoryLayout.setVisibility(View.GONE);
				}else{
					final Category selectCategory = Category.getCategories().get(position-1);
					categoryId = selectCategory.getId();
					fragmentAdapter = new PagerAdapter(getSupportFragmentManager(),selectCategory.getId(),0);
					pager.setAdapter(fragmentAdapter);
			        
					final ArrayList<Category> subCategories = selectCategory.getSecondCategories();
			    	ArrayList<String> subCategoriessString = new ArrayList<String>();
			    	subCategoriessString.add("全部");
			    	for (int i = 0; i < subCategories.size(); i++) {
			    		subCategoriessString.add(subCategories.get(i).getName());
					}
			        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(NearRestaurantActivity.this, android.R.layout.simple_spinner_item, subCategoriessString);
			        subCatSpinner.setAdapter(sAdapter);
			        secondCategoryLayout.setVisibility(View.VISIBLE);
			        subCatSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
						@Override
						public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
							if(position == 0){
								fragmentAdapter = new PagerAdapter(getSupportFragmentManager(),selectCategory.getId(),0);
								pager.setAdapter(fragmentAdapter);
								secondCategoryId = 0;
							}else{
								Category subCategory = subCategories.get(position-1);
								fragmentAdapter = new PagerAdapter(getSupportFragmentManager(),selectCategory.getId(),subCategory.getId());
								pager.setAdapter(fragmentAdapter);
								secondCategoryId = subCategory.getId();
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							
						}
			        	
			        });
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
	
         });
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            Intent intent = new Intent(NearRestaurantActivity.this, MapActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", categoryId);
            bundle.putInt("SecondCategoryId", secondCategoryId);
        	intent.putExtras(bundle);
            startActivity(intent);
            break;
        }
        return true;
    }
	
	class PagerAdapter extends FragmentStatePagerAdapter {
       
		int category_id;
		int second_category_id;

        public PagerAdapter(FragmentManager fm, int category_id, int second_category_id) {
        	super(fm);
        	this.category_id = category_id;
        	this.second_category_id = second_category_id;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();     
            switch(position){
        	case 0:
        		kk = GridRestaurantsFragment.newInstance(0,0,category_id,second_category_id,0,false,false,true);
        		break;
        	}
            return kk;
        }
        
        @Override
        public int getItemPosition(Object object) {
        	   return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
			return "";
        }

        @Override
        public int getCount() {
            return 1;
        }
	}
	
	@Override
    public void onStart() {
      super.onStart();
      EasyTracker.getInstance().activityStart(this);
    }

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance().activityStop(this);
    }

}
