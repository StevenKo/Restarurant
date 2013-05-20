package com.restaurant.collection;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.collection.entity.Note;

public class RestaurantNoteActivity extends SherlockActivity{
	
	private static final int ID_COLLECT = 1;
	private LinearLayout layoutProgress;
	private LinearLayout layoutReload;
	private Button buttonReload;
	private TextView articleTextTitle;
	private WebView webArticle;
	private Note note;

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
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, ID_COLLECT, 1, getResources().getString(R.string.menu_search)).setIcon(R.drawable.custom_checkbox)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
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
	}

	private void findViews() {
		 layoutProgress = (LinearLayout) findViewById (R.id.layout_progress);
		 layoutReload = (LinearLayout) findViewById (R.id.layout_reload);
		 buttonReload = (Button) findViewById (R.id.button_reload);
		 webArticle = (WebView) findViewById (R.id.web_article);
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
