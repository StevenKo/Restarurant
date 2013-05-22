package com.restaurant.collection;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
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
import com.viewpagerindicator.TitlePageIndicator;

public class MyCollectionActivity extends SherlockFragmentActivity{
	
	private ViewPager pager;
	private static final int    ID_MAP      = 4;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);

        final ActionBar ab = getSupportActionBar();
        ab.setTitle("我的收藏");
        ab.setDisplayHomeAsUpEnabled(true);

        ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(new Category());

        FragmentPagerAdapter adapter = new CategoryPagerAdapter(getSupportFragmentManager(), categories);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setVisibility(View.GONE);

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
            Intent intent = new Intent(MyCollectionActivity.this, MapActivity.class);
            startActivity(intent);
            break;
        }
        return true;
    }
	
	class CategoryPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Category> categories;

        public CategoryPagerAdapter(FragmentManager fm, ArrayList<Category> categories) {
            super(fm);
            this.categories = categories;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = CategoryTabFragment.newInstance(0,0,0,true,false);
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categories.get(position).getName();
        }

        @Override
        public int getCount() {
            return categories.size();
        }
	}

}
