package com.restaurant.collection;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.restaurant.collection.api.RestaurantAPI;
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
	private MenuItem itemSearch;
	
	
	//gcm
    public static final String EXTRA_MESSAGE = "message";
    private static final String PROPERTY_ON_SERVER_EXPIRATION_TIME = "onServerExpirationTimeMs";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    /**
     * Default lifespan (7 days) of a reservation until it is considered expired.
     */
    public static final long REGISTRATION_EXPIRY_TIME_MS = 1000 * 3600 * 24 * 7;
    
    /**
     * Substitute you own sender ID here.
     */
    String SENDER_ID = "1037018589447";
	private Context context;
	String regid;
	GoogleCloudMessaging gcm;
	static final String TAG = "GCMDemo";
	public final static String              keyPref         = "pref";

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
        
        context = getApplicationContext();
        regid = getRegistrationId(context);

        if (regid.length() == 0) {
            registerBackground();
        }
        gcm = GoogleCloudMessaging.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, ID_RESPONSE, 1, getResources().getString(R.string.menu_respond)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_ABOUT_US, 2, getResources().getString(R.string.menu_aboutus)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(0, ID_GRADE, 3, getResources().getString(R.string.menu_recommend)).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        
        itemSearch = menu.add(0, ID_SEARCH, 4, getResources().getString(R.string.menu_search)).setIcon(R.drawable.ic_search_inverse)
                .setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                    private EditText search;

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        search = (EditText) item.getActionView();
                        search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                        search.setInputType(InputType.TYPE_CLASS_TEXT);
                        search.requestFocus();
                        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("SearchKeyword", v.getText().toString());
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this, SearchActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    itemSearch.collapseActionView();
                                    return true;
                                }
                                return false;
                            }
                        });
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(null, InputMethodManager.SHOW_IMPLICIT);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // TODO Auto-generated method stub
                        search.setText("");
                        return true;
                    }
                }).setActionView(R.layout.collapsible_edittext);
        itemSearch.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        
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
                kk = GridRestaurantsFragment.newInstance(0,0,0,0,0,false,true,false,false);
                break;
            case 3:
                kk = GridEatNoteFragment.newInstance(0,0,0,0,0,false,true);
                break;
            case 4:
            	kk = GridRestaurantsFragment.newInstance(1,1,0,0,0,false,false,false,false);
            	break;
            case 5:
            	kk = GridRestaurantsFragment.newInstance(1,2,0,0,0,false,false,false,false);
            	break;
            case 6:
            	kk = GridRestaurantsFragment.newInstance(1,3,0,0,0,false,false,false,false);
            	break;
            case 7:
            	kk = GridRestaurantsFragment.newInstance(1,4,0,0,0,false,false,false,false);
            	break;
            case 8:
            	kk = GridRestaurantsFragment.newInstance(1,5,0,0,0,false,false,false,false);
            	break;
            case 9:
            	kk = GridRestaurantsFragment.newInstance(1,6,0,0,0,false,false,false,false);
            	break;
            case 10:
            	kk = GridRestaurantsFragment.newInstance(1,7,0,0,0,false,false,false,false);
            	break;
            case 11:
            	kk = GridRestaurantsFragment.newInstance(1,8,0,0,0,false,false,false,false);
            	break;
            case 12:
            	kk = GridRestaurantsFragment.newInstance(1,9,0,0,0,false,false,false,false);
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
		((IndexCategoryTabFragment)adapter.getItem(0)).setTabHostCurrent(2);
	}

	@Override
	public void onLocationSearchClicked() {
		pager.setCurrentItem(0);
		((IndexCategoryTabFragment)adapter.getItem(0)).setTabHostCurrent(0);
		
	}

	@Override
	public void onFoodtypeSearchClicked() {
		pager.setCurrentItem(0);
		((IndexCategoryTabFragment)adapter.getItem(0)).setTabHostCurrent(1);
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
    
    
// gcm use start from here
    
    public static String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.length() == 0) {
            Log.v(TAG, "Registration not found.");
            return "";
        }
        // check if app was updated; if so, it must clear registration id to
        // avoid a race condition if GCM sends a message
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion || isRegistrationExpired(context)) {
            Log.v(TAG, "App version changed or registration expired.");
            return "";
        }
        return registrationId;
    }
    
    private static SharedPreferences getGCMPreferences(Context context) {
        return context.getSharedPreferences(keyPref, 0);
    }
    
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
    
    private static boolean isRegistrationExpired(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        // checks if the information is not stale
        long expirationTime =
                prefs.getLong(PROPERTY_ON_SERVER_EXPIRATION_TIME, -1);
        return System.currentTimeMillis() > expirationTime;
    }
    
    private void registerBackground() {
        new AsyncTask() {

			@Override
			protected String doInBackground(Object... params) {
				String msg = "";
	            try {
	                if (gcm == null) {
	                    gcm = GoogleCloudMessaging.getInstance(context);
	                }
	                regid = gcm.register(SENDER_ID);
	                msg = "Device registered, registration id=" + regid;
	                RestaurantAPI.sendRegistrationId(regid);
	                
	                setRegistrationId(context, regid);
	            } catch (IOException ex) {
	                msg = "Error :" + ex.getMessage();
	            }
	            return msg;
			}

			private void setRegistrationId(Context context, String regid) {
				final SharedPreferences prefs = getGCMPreferences(context);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString(PROPERTY_REG_ID, regid);
				editor.putInt(PROPERTY_APP_VERSION, getAppVersion(context));
				editor.putLong(PROPERTY_ON_SERVER_EXPIRATION_TIME, REGISTRATION_EXPIRY_TIME_MS + System.currentTimeMillis());
				editor.commit();
				
			}
            
        }.execute(null, null, null);
    }
	

}
