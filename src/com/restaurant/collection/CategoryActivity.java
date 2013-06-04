package com.restaurant.collection;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Area;
import com.restaurant.collection.entity.Category;
import com.restaurant.collection.entity.Type;
import com.restaurant.fragment.AreaCategoryListFragment;
import com.restaurant.fragment.CategoryTabFragment;
import com.restaurant.fragment.SecondCategoryListFragment;
import com.viewpagerindicator.TitlePageIndicator;
import android.content.DialogInterface.OnClickListener;

interface AlertPositiveListener {
    public void onPositiveClick(int position);
}

public class CategoryActivity extends SherlockFragmentActivity implements OnItemClickListener,AlertPositiveListener{
	

    private static final int    ID_SETTING  = 0;
    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_MAP      = 4;
    private static final int    ID_SORT     = 5;
    private AlertDialog.Builder aboutUsDialog;
    private ViewPager           pager;
	private Bundle mBundle;
	private Area area;
	private ArrayList<Category> rankCategories;
	private Category category;
	private FragmentPagerAdapter adapter;
	private Type type;
	private int areaId = 0;
	private int categoryId = 0;
	private int rankCategoryId = 0;
	private int typeId = 0;
	private int secondCategoryId;
	private int sortPosition = 1;

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
        secondCategoryId = mBundle.getInt("SecondCategoryId");
        typeId = mBundle.getInt("TypeId");
        if(areaId!=0 && secondCategoryId!=0){
        	area = Area.getArea(areaId);
        	category = Category.getSecondCategory(secondCategoryId);
        	ab.setTitle(area.getName() + ":" + category.getName());
        	adapter = new AreaSecondCategoryPagerAdapter(getSupportFragmentManager());
        	indicator.setVisibility(View.GONE);
        }else if(areaId!=0 && typeId!=0){
        	area = Area.getArea(areaId);
        	type = Type.getType(typeId);
        	ab.setTitle(area.getName() + ":" + type.getName());
        	adapter = new AreaTypePagerAdapter(getSupportFragmentManager());
        	indicator.setVisibility(View.GONE);
        }else if(areaId!=0){
        	area = Area.getArea(areaId);
        	rankCategories = Category.getRankCategories();
            ab.setTitle(area.getName());
            adapter = new AreaRankCategoryPagerAdapter(getSupportFragmentManager(), rankCategories);
        }else if (categoryId!=0){
        	category = Category.getCategory(categoryId);
            ab.setTitle(category.getName());
            adapter = new CategoryPagerAdapter(getSupportFragmentManager(), category.getSecondCategories());
        }else if(typeId!=0){
        	type = Type.getType(typeId);
        	ab.setTitle(type.getName());
        	adapter = new TypePagerAdapter(getSupportFragmentManager());
        	indicator.setVisibility(View.GONE);
        }

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        
        if(areaId!=0 && categoryId==0 && typeId==0 && secondCategoryId==0){
        	pager.setCurrentItem(1);
        	rankCategoryId = rankCategories.get(0).getId();
        	indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
        		
	        	@Override
	        	public void onPageSelected(int postion) {
	        		rankCategoryId = rankCategories.get(postion).getId();
	        	}
	        });
        }else if(categoryId!=0){
        	pager.setCurrentItem(1);
        }

        setAboutUsDialog();
        
    }

    private void showPriceRangeDialog() {
    	LayoutInflater inflater = this.getLayoutInflater();
    	LinearLayout priceRangeLayout = (LinearLayout) inflater.inflate(R.layout.price_range_dialog,null);
    	final EditText rangeStart = (EditText)priceRangeLayout.findViewById(R.id.price_range_start);
    	final EditText rangeEnd = (EditText)priceRangeLayout.findViewById(R.id.price_range_end);
    	
    	Builder a = new AlertDialog.Builder(this).setTitle("選擇價格區間").setIcon(R.drawable.icon)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	

                    }
        }).setNegativeButton("取消", null);
    	a.setView(priceRangeLayout);
    	a.show();
	}
    
    public class AlertDialogRadio  extends DialogFragment{
    	
    	AlertPositiveListener alertPositiveListener;
    	final CharSequence[] items = {"距離 ","服務 ", "菜色", "價格 $0~200", "價格 $200~500", "價格$500~1000", "自訂價格"};
    	
    	public void onAttach(android.app.Activity activity) {
            super.onAttach(activity);
            try{
                alertPositiveListener = (AlertPositiveListener) activity;
            }catch(ClassCastException e){
                // The hosting activity does not implemented the interface AlertPositiveListener
                throw new ClassCastException(activity.toString() + " must implement AlertPositiveListener");
            }
        }
    	
    	OnClickListener positiveListener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog alert = (AlertDialog)dialog;
                int position = alert.getListView().getCheckedItemPosition();
                alertPositiveListener.onPositiveClick(position);
            }
        };
        
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
     
            /** Getting the arguments passed to this fragment */
            Bundle bundle = getArguments();
            int position = bundle.getInt("position");
     
            /** Creating a builder for the alert dialog window */
            AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
     
            /** Setting a title for the window */
            b.setTitle("選擇排序方式");
     
            /** Setting items to the alert dialog */
            b.setSingleChoiceItems(items, position, null);
     
            /** Setting a positive button and its listener */
            b.setPositiveButton("確定",positiveListener);
     
            /** Setting a positive button and its listener */
            b.setNegativeButton("取消", null);
     
            /** Creating the alert dialog window using the builder class */
            AlertDialog d = b.create();
     
            /** Return the alert dialog window */
            return d;
        }
    	
    }

	class AreaRankCategoryPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Category> categories;

        public AreaRankCategoryPagerAdapter(FragmentManager fm, ArrayList<Category> categories) {
            super(fm);
            this.categories = categories;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            if(position == 0)
              kk = AreaCategoryListFragment.newInstance(areaId);
            else
              kk = CategoryTabFragment.newInstance(areaId, categories.get(position-1).getId(),0, 0,0, false, false);
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	if(position == 0)
        	  return "分類";
        	else
              return categories.get(position-1).getName();
        }

        @Override
        public int getCount() {
            return categories.size()+1;
        }
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
            if(position == 0)
               kk = SecondCategoryListFragment.newInstance(categoryId);
            else if(position == 1)
               kk = CategoryTabFragment.newInstance(0, 0,categoryId,0, 0, false, false);
            else
               kk = CategoryTabFragment.newInstance(0, 0,0,categories.get(position-2).getId(), 0, false, false);
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	if(position == 0)
          	  return "分類";
        	else if(position == 1)
        	  return "全部";
           return categories.get(position-2).getName();
        }

        @Override
        public int getCount() {
            return categories.size()+2;
        }
    }
    
    class TypePagerAdapter extends FragmentPagerAdapter {


        public TypePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = CategoryTabFragment.newInstance(0, 0,0 ,0, typeId,false, false);
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
    
    class AreaTypePagerAdapter extends FragmentPagerAdapter {


        public AreaTypePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = CategoryTabFragment.newInstance(areaId, 0,0 ,0, typeId,false, false);
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
    
    class AreaSecondCategoryPagerAdapter extends FragmentPagerAdapter {


        public AreaSecondCategoryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = CategoryTabFragment.newInstance(areaId, 0,0 ,secondCategoryId, 0,false, false);
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
        menu.add(0, ID_SORT, 5, "排序").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
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
        case ID_SORT:
        	android.app.FragmentManager manager = getFragmentManager();        	 
            AlertDialogRadio alert = new AlertDialogRadio();
            Bundle b  = new Bundle();
            b.putInt("position", sortPosition );
            alert.setArguments(b);
            alert.show(manager, "alert_dialog_radio");
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		pager.setCurrentItem(position+2);
	}

	@Override
	public void onPositiveClick(int position) {
		sortPosition = position;
        switch(position){
        case 6:
        	showPriceRangeDialog();
        	break;
        }
	}
}
