package com.restaurant.collection;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.collection.db.SQLiteRestaurant;
import com.restaurant.collection.entity.Note;
import com.restaurant.gps.util.GPSTracker;

public class RestaurantNoteActivity extends SherlockActivity{
	
	private LinearLayout layoutProgress;
	private LinearLayout layoutReload;
	private Button buttonReload;
	private WebView webArticle;
	private Note note;
	private ImageButton share_btn;
	private ImageButton direction_button;
	private ImageButton place_button;
	private ImageButton favorite_button;
	private double latitude;
	private double longitude;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note);
        
        Bundle mBundle = this.getIntent().getExtras();
        int noteId = mBundle.getInt("NoteId");
        int rId = mBundle.getInt("RestaurantId");
        String noteTitle = mBundle.getString("NoteTitle");
        String noteLink = mBundle.getString("NoteLink");
        double noteX = mBundle.getDouble("NoteX");
        double noteY = mBundle.getDouble("NoteY");
        String notePic = mBundle.getString("NotePic");
         note = new Note(noteId, rId,noteTitle, "", notePic, noteLink,noteX, noteY);
         

        
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar_title, null);
        ((TextView)v.findViewById(R.id.title)).setText(note.getTitle());
        ab.setCustomView(v);
        
        findViews();
        setWebView();
        getCurrentLocation();
        setButtons();
        
    }
	
	private void getCurrentLocation() {
    	GPSTracker mGPS = new GPSTracker(this);

    	if(mGPS.canGetLocation() ){

    		latitude =mGPS.getLatitude();
    		longitude=mGPS.getLongitude();

    	}else{
    	// can't get the location
    	}
		
	}
	

	
	 private void setWebView() {
		 webArticle.getSettings().setSupportZoom(true);
         webArticle.getSettings().setJavaScriptEnabled(true);
         webArticle.setWebViewClient(new WebViewClient() {

    	 public void onPageFinished(WebView view, String url) {
    		   layoutProgress.setVisibility(View.GONE);
    	      }
    	  });
	      webArticle.setWebChromeClient(new WebChromeClient());
	      webArticle.loadUrl(note.getLink()); 
		
	}



	private void setButtons() {
		
		share_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_SEND);
            	intent.setType("text/plain");
            	intent.putExtra(android.content.Intent.EXTRA_TEXT, "推薦食記 :" + note.getTitle()+"\n" + note.getLink());
            	startActivity(intent); 
            }
        });
 
    	place_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Uri uri = Uri.parse("geo:0,0?q="+note.getX()+"," + note.getY() );
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
            }
        });
    	direction_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_VIEW,
            	        Uri.parse("http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr="+note.getX()+","+note.getY()));
            			startActivity(intent);
            }
        });
    	favorite_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	SQLiteRestaurant db = new SQLiteRestaurant(RestaurantNoteActivity.this);
            	if (db.isNoteCollected(note.getId())){
            		favorite_button.setImageResource( R.drawable.icon_heart_grey );
            		db.deleteNote(note);
            	}else{
            		favorite_button.setImageResource( R.drawable.icon_heart );
            		db.insertNote(note);
            	}
            
            }
        });
	   
       
       
	}

	private void findViews() {
		 layoutProgress = (LinearLayout) findViewById (R.id.layout_progress);
		 layoutReload = (LinearLayout) findViewById (R.id.layout_reload);
		 buttonReload = (Button) findViewById (R.id.button_reload);
		 webArticle = (WebView) findViewById (R.id.web_article);
		 share_btn = (ImageButton)findViewById(R.id.share_button);
		 direction_button = (ImageButton)findViewById(R.id.direction_button);
		 place_button = (ImageButton)findViewById(R.id.place_button);
		 favorite_button = (ImageButton)findViewById(R.id.favorite_button);
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
