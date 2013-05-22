package com.restaurant.collection;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.restaurant.adapter.NoteGridViewAdapter;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Note;
import com.restaurant.customized.view.LoadMoreGridView;


public class RestaurantNotesActivity extends SherlockActivity{
	
    private ArrayList<Note>             notes           = new ArrayList<Note>();
    private NoteGridViewAdapter               myGridViewAdapter;
	private LinearLayout progressLayout;
	private LinearLayout loadmoreLayout;
	private LinearLayout layoutReload;
	private Button buttonReload;
	private LoadMoreGridView myGrid;
	private int restaurantId;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadmore_grid);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("餐廳食記");
        ab.setDisplayHomeAsUpEnabled(true);
        
        Bundle mBundle = this.getIntent().getExtras();
        restaurantId = mBundle.getInt("ResturantId");
        restaurantId = 1;
        findAndSetViews();
        
        
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

	private void findAndSetViews() {
		progressLayout = (LinearLayout) findViewById(R.id.layout_progress);
        loadmoreLayout = (LinearLayout) findViewById(R.id.load_more_grid);
        layoutReload = (LinearLayout) findViewById(R.id.layout_reload);
        buttonReload = (Button) findViewById(R.id.button_reload);
        myGrid = (LoadMoreGridView) findViewById(R.id.news_list);	
        
        buttonReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                progressLayout.setVisibility(View.VISIBLE);
                layoutReload.setVisibility(View.GONE);
                new DownloadNotesTask().execute();
            }
        });
        
        if (myGridViewAdapter != null) {
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);
            myGrid.setAdapter(myGridViewAdapter);
        } else {
            new DownloadNotesTask().execute();
        }
	}
	
	private class DownloadNotesTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object... params) {
            notes = RestaurantAPI.getRestaurantNotes(restaurantId,1);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);

            if (notes != null && notes.size() != 0) {
                try {
                    layoutReload.setVisibility(View.GONE);
                    myGridViewAdapter = new NoteGridViewAdapter(RestaurantNotesActivity.this, notes);
                    myGrid.setAdapter(myGridViewAdapter);
                } catch (Exception e) {

                }
            } else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }


}
