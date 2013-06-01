package com.restaurant.collection;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
	private FragmentPagerAdapter adapter;
	private Type type;
	private ArrayList<Area> areas;
	private int areaId = 0;
	private int categoryId = 0;
	private int typeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);
        
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        
        mBundle = this.getIntent().getExtras();
        areaId = mBundle.getInt("AreaId");
        categoryId = mBundle.getInt("CategoryId");
        typeId = mBundle.getInt("TypeId");
        if(areaId!=0){
        	area = Area.getArea(areaId);
        	areaCategories = Category.getCategories();
            ab.setTitle(area.getName());
            adapter = new CategoryPagerAdapter(getSupportFragmentManager(), areaCategories);
        }else if (categoryId!=0){
        	category = Category.getCategory(categoryId);
//            areas = Area.getCategoryAreas(categoryId);
            ab.setTitle(category.getName());
            adapter = new AreaPagerAdapter(getSupportFragmentManager());
            indicator.setVisibility(View.GONE);
        }else{
        	type = Type.getType(typeId);
//        	areas = Area.getTypeAreas(typeId);
        	ab.setTitle(type.getName());
        	adapter = new AreaPagerAdapter(getSupportFragmentManager());
        	indicator.setVisibility(View.GONE);
        }

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        
        if(categoryId!=0 || typeId!=0){
//        	areaId = areas.get(0).getId();
//	        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
//	
//	        	@Override
//	        	public void onPageSelected(int postion) {
//	        		areaId = areas.get(postion).getId();
//	        	}
//	        });
        }else{
        	categoryId = areaCategories.get(0).getId();
        	indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
        		
	        	@Override
	        	public void onPageSelected(int postion) {
	        		categoryId = areaCategories.get(postion).getId();
	        	}
	        });
        }

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
            kk = CategoryTabFragment.newInstance(areaId, categories.get(position).getId(), typeId, false, false);
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "中式料理";
        }

        @Override
        public int getCount() {
            return categories.size();
        }
    }
    
    class AreaPagerAdapter extends FragmentPagerAdapter {


        public AreaPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = CategoryTabFragment.newInstance(2, categoryId, typeId,false, false);
            return kk;
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
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        // menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse)
        // .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ID_MAP, 4, "地圖顯示餐廳").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
        case android.R.id.home:
            finish();
            break;
        case ID_RESPONSE: // response
        	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { getResources().getString(R.string.respond_mail_address) });
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.respond_mail_title));
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            break;
        case ID_ABOUT_US:
        	aboutUsDialog.show();
            break;
        case ID_GRADE:
        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.recommend_url)));
        	startActivity(browserIntent);
            break;
        case ID_MAP:
        	
            Intent intent = new Intent(CategoryActivity.this, MapActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("AreaId", areaId);
        	bundle.putInt("CategoryId",categoryId);
        	bundle.putInt("TypeId", typeId);
        	intent.putExtras(bundle);
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
