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
import com.restaurant.adapter.NoteGridViewAdapter;
import com.restaurant.collection.entity.Note;
import com.restaurant.customized.view.LoadMoreGridView;


public class RestaurantNotesActivity extends SherlockActivity{
	
    private final ArrayList<Note>             notes           = new ArrayList<Note>();
    private NoteGridViewAdapter               myGridViewAdapter;
	private LinearLayout progressLayout;
	private LinearLayout loadmoreLayout;
	private LinearLayout layoutReload;
	private Button buttonReload;
	private LoadMoreGridView myGrid;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadmore_grid);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("餐廳食記");
        ab.setDisplayHomeAsUpEnabled(true);
        
        findAndSetViews();
        
        
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
            notes.add(new Note());
            notes.add(new Note());
            notes.add(new Note());
            notes.add(new Note());
            notes.add(new Note());
            notes.add(new Note());
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
