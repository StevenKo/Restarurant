package com.restaurant.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.restaurant.adapter.NoteGridViewAdapter;
import com.restaurant.collection.R;
import com.restaurant.collection.entity.Note;
import com.restaurant.customized.view.LoadMoreGridView;
import com.restaurant.entity.PsuedoRestaurant;

@SuppressLint("ValidFragment")
public class GridEatNoteFragment extends Fragment {

    private final ArrayList<Note>             notes           = new ArrayList<Note>();
    private LoadMoreGridView                  myGrid;
    private NoteGridViewAdapter               myGridViewAdapter;
    private LinearLayout                      progressLayout;
    private LinearLayout                      loadmoreLayout;
    private LinearLayout                      layoutReload;
    private Button                            buttonReload;

    private int                               intOrder;
    private int                               myPage          = 1;
    private Boolean                           checkLoad       = true;
    private final ArrayList<PsuedoRestaurant> moreRestaurants = new ArrayList<PsuedoRestaurant>();

    public GridEatNoteFragment() {

    }

    public static final GridEatNoteFragment newInstance(int content_order) {
        GridEatNoteFragment f = new GridEatNoteFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("contentOrder", content_order);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        intOrder = getArguments().getInt("contentOrder");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.loadmore_grid, container, false);
        progressLayout = (LinearLayout) myFragmentView.findViewById(R.id.layout_progress);
        loadmoreLayout = (LinearLayout) myFragmentView.findViewById(R.id.load_more_grid);
        layoutReload = (LinearLayout) myFragmentView.findViewById(R.id.layout_reload);
        buttonReload = (Button) myFragmentView.findViewById(R.id.button_reload);
        myGrid = (LoadMoreGridView) myFragmentView.findViewById(R.id.news_list);
        myGrid.setOnLoadMoreListener(new LoadMoreGridView.OnLoadMoreListener() {
            public void onLoadMore() {
                if (checkLoad) {
                    myPage = myPage + 1;
                    new LoadMoreTask().execute();
                } else {
                    myGrid.onLoadMoreComplete();
                }
            }
        });

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

        return myFragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
                    myGridViewAdapter = new NoteGridViewAdapter(getActivity(), notes);
                    myGrid.setAdapter(myGridViewAdapter);
                } catch (Exception e) {

                }
            } else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }

    private class LoadMoreTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadmoreLayout.setVisibility(View.VISIBLE);

        }

        @Override
        protected Object doInBackground(Object... params) {

            moreRestaurants.clear();

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            loadmoreLayout.setVisibility(View.GONE);

            if (moreRestaurants != null && moreRestaurants.size() != 0) {
                myGridViewAdapter.notifyDataSetChanged();
            } else {
                checkLoad = false;
                Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
            }
            myGrid.onLoadMoreComplete();

        }
    }

}