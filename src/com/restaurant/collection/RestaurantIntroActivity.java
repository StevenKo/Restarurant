package com.restaurant.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.restaurant.fragment.RestaurantPhotoFragment;
import com.viewpagerindicator.CirclePageIndicator;

public class RestaurantIntroActivity extends SherlockFragmentActivity {
    private ViewPager pager;
	private RelativeLayout watch_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_restaurant_intro);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("餐廳介紹");
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentPagerAdapter adapter = new PhotoPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        
        findViews();
        setViews();
        
    }

    private void setViews() {
    	watch_notes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RestaurantIntroActivity.this, RestaurantNotesActivity.class);
                startActivity(intent);
            }
        });
	}

	private void findViews() {
		watch_notes = (RelativeLayout)findViewById(R.id.watch_notes);
	}

	class PhotoPagerAdapter extends FragmentPagerAdapter {

        public PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment kk = new Fragment();
            kk = RestaurantPhotoFragment.newInstance();
            return kk;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

}
