package com.restaurant.collection;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.fragment.IndexCategoryTabFragment;
import com.restaurant.fragment.GridEatNoteFragment;
import com.restaurant.fragment.GridRestaurantsFragment;
import com.restaurant.fragment.IndexFragment;
import com.restaurant.fragment.IndexFragment.OnButtonClickedListener;
import com.viewpagerindicator.TitlePageIndicator;

@SuppressLint("NewApi")
public class MainActivity extends SherlockFragmentActivity implements OnButtonClickedListener{

    private static final int    ID_RESPONSE = 1;
    private static final int    ID_ABOUT_US = 2;
    private static final int    ID_GRADE    = 3;
    private static final int    ID_SEARCH   = 5;
    FragmentPagerAdapter adapter;


    private String[]            sectionTitles;
    private ViewPager           pager;
    private AlertDialog.Builder aboutUsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);
        
        sectionTitles = getResources().getStringArray(R.array.sections);

        adapter = new SectionPagerAdapter(getSupportFragmentManager());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        

        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        pager.setCurrentItem(1);
        setAboutUsDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
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
        case ID_SEARCH:

            break;
        }
        return true;
    }

    class SectionPagerAdapter extends FragmentPagerAdapter {
    	
    	Fragment indexCategoryTabFragment;
    	

		public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            switch (position) {
            case 0:
            	if (indexCategoryTabFragment == null){
            		indexCategoryTabFragment =  IndexCategoryTabFragment.newInstance();
            		return indexCategoryTabFragment;
            	}else{
            		return indexCategoryTabFragment;
            	}
            case 1:
                kk = IndexFragment.newInstance();
                break;
            case 2:
                kk = GridRestaurantsFragment.newInstance(0,0,0,false,true);
                break;
            case 3:
                kk = GridEatNoteFragment.newInstance(0,0,0,false,true);
                break;
            default:
            	kk = GridRestaurantsFragment.newInstance(0,0,0,false,true);
            	break;
            }
            return kk;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sectionTitles[position % sectionTitles.length];
        }

        @Override
        public int getCount() {
            return sectionTitles.length;
        }
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 1) {
            finish();
        } else {
            pager.setCurrentItem(1, true);
        }
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
	public void onCategorySearchClicked() {
		pager.setCurrentItem(0);
		((IndexCategoryTabFragment)adapter.getItem(0)).setTabHostCurrent(0);
	}

	@Override
	public void onLocationSearchClicked() {
		pager.setCurrentItem(0);
		((IndexCategoryTabFragment)adapter.getItem(0)).setTabHostCurrent(1);
		
	}

	@Override
	public void onFoodtypeSearchClicked() {
		pager.setCurrentItem(0);
		((IndexCategoryTabFragment)adapter.getItem(0)).setTabHostCurrent(2);
	}
	

}
