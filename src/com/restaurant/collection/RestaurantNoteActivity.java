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

public class RestaurantNoteActivity extends SherlockActivity{
	
	private static final int ID_COLLECT = 1;
	private LinearLayout layoutProgress;
	private LinearLayout layoutReload;
	private Button buttonReload;
	private TextView articleTextTitle;
	private WebView webArticle;
	private Note note;
	private ImageButton share_btn;
	private ImageButton direction_button;
	private ImageButton place_button;
	private ImageButton favorite_button;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note);
        
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar_title, null);
        ((TextView)v.findViewById(R.id.title)).setText("[食記]基隆長榮桂冠酒店18樓咖啡廳‏");
        ab.setCustomView(v);
        
        findViews();
        setViews();
        
        new DownloadArticleTask().execute();
    }
	

	
	 private void setViews() {
	   webArticle.getSettings().setSupportZoom(true);
       webArticle.getSettings().setJavaScriptEnabled(true);
       webArticle.setWebViewClient(new WebViewClient() {

    	   public void onPageFinished(WebView view, String url) {
    		   layoutProgress.setVisibility(View.GONE);
    	    }
    	});
       webArticle.setWebChromeClient(new WebChromeClient());
       
       share_btn.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
           	Intent intent = new Intent(Intent.ACTION_SEND);
           	intent.setType("text/plain");
           	intent.putExtra(android.content.Intent.EXTRA_TEXT, "News for you!");
           	startActivity(intent); 
           }
       });
   	place_button.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
           	Uri uri = Uri.parse("geo:0,0?q=22.99948365856307,72.60040283203125(Maninagar)");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
           }
       });
   	direction_button.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
           	Intent intent = new Intent(Intent.ACTION_VIEW,
           			Uri.parse("http://maps.google.com/maps?saddr="+23.0094408+","+72.5988541+"&daddr="+22.99948365856307+","+72.60040283203125));
           			startActivity(intent);
           }
       });
   	favorite_button.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
           	SQLiteRestaurant db = new SQLiteRestaurant(RestaurantNoteActivity.this);
           	if (db.isRestaurantCollected(note.getId())){
           		favorite_button.setImageResource( R.drawable.icon_heart_grey );
           	}else{
           		favorite_button.setImageResource( R.drawable.icon_heart );
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
	
    private class DownloadArticleTask extends AsyncTask {
		
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	    }
		
        @Override
        protected Object doInBackground(Object... params) {        	
        	note = new Note();
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            
            if (note.getLink()!=null){	            
            	webArticle.loadUrl(note.getLink());           	
            }else{
            	layoutReload.setVisibility(View.VISIBLE);
            }           
        }
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
