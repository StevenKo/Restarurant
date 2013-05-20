package com.restaurant.collection;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.collection.entity.Area;
import com.restaurant.collection.entity.Category;
import com.restaurant.collection.entity.Type;
import com.restaurant.fragment.CategoryTabFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class CategoryActivity extends SherlockFragmentActivity {

    private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_MAP      = 4;
    private AlertDialog.Builder aboutUsDialog;
    private ViewPager           pager;
	private Bundle mBundle;
	private Area area;
	private ArrayList<Category> areaCategories;
	private Category category;
	private ArrayList<Area> categoryAreas;
	private FragmentPagerAdapter adapter;
	private Type type;
	private ArrayList<Area> typeAreas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);
        
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        
        mBundle = this.getIntent().getExtras();
        int areaId = mBundle.getInt("AreaId");
        int categoryId = mBundle.getInt("CategoryId");
        int typeId = mBundle.getInt("TypeId");
        if(areaId!=0){
        	area = Area.getArea(areaId);
        	areaCategories = Category.getAreaCategories(areaId);
            ab.setTitle(area.getName());
            adapter = new CategoryPagerAdapter(getSupportFragmentManager(), areaCategories);
        }else if (categoryId!=0){
        	category = Category.getCategory(categoryId);
            categoryAreas = Area.getCategoryAreas(categoryId);
            ab.setTitle(category.getName());
            adapter = new AreaPagerAdapter(getSupportFragmentManager(), categoryAreas);
        }else{
        	type = Type.getType(typeId);
        	typeAreas = Area.getTypeAreas(typeId);
        	ab.setTitle(type.getName());
        	adapter = new AreaPagerAdapter(getSupportFragmentManager(), typeAreas);
        }

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        setAboutUsDialog();
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
            kk = CategoryTabFragment.newInstance();
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
    
    class AreaPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Area> areas;

        public AreaPagerAdapter(FragmentManager fm, ArrayList<Area> areas) {
            super(fm);
            this.areas = areas;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = CategoryTabFragment.newInstance();
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return areas.get(position).getName();
        }

        @Override
        public int getCount() {
            return areas.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, ID_SETTING, 0, getResources().getString(R.string.menu_settings)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        // menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse)
        // .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ID_MAP, 4, "地圖顯示").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
        case ID_SETTING: // setting
            break;
        case ID_RESPONSE: // response
            break;
        case ID_ABOUT_US:
            break;
        case ID_GRADE:

            break;
        case ID_MAP:
            Intent intent = new Intent(CategoryActivity.this, MapActivity.class);
            startActivity(intent);
            break;
        }
        return true;
    }

    private void setAboutUsDialog() {
        // TODO Auto-generated method stub
        aboutUsDialog = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.about_us_string)).setIcon(R.drawable.play_store_icon)
                .setMessage(getResources().getString(R.string.about_us))
                .setPositiveButton(getResources().getString(R.string.yes_string), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }
}
