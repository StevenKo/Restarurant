package com.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.restaurant.collection.MyCollectionActivity;
import com.restaurant.collection.R;
import com.restaurant.collection.RestaurantIntroActivity;
import com.restaurant.collection.RestaurantNotesActivity;

public final class IndexFragment extends Fragment {

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    private View myFragmentView;
	private LinearLayout myCollectionLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.layout_index_restaurant, container, false);
        findViews();
        setViews();
        return myFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setViews() {
    	myCollectionLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyCollectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
    	myCollectionLayout = (LinearLayout)myFragmentView.findViewById(R.id.my_collection);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
