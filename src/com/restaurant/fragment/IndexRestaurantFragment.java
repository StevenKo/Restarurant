package com.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.restaurant.collection.R;

public final class IndexRestaurantFragment extends Fragment {

    public static IndexRestaurantFragment newInstance() {
        IndexRestaurantFragment fragment = new IndexRestaurantFragment();
        return fragment;
    }

    private View         myFragmentView;
    private LinearLayout myCollection;
    private LinearLayout mySetting;
    private LinearLayout myTravelTheme;
    private LinearLayout myTravelSeason;
    private LinearLayout myTravelBeach;
    private LinearLayout myCity;

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

        // myCollection.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        //
        // }
        // });
        // mySetting.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        //
        // }
        // });
        //
        // myTravelTheme.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        //
        // }
        // });
        //
        // myTravelSeason.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        //
        // }
        // });
        //
        // myTravelBeach.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        //
        // }
        // });
        //
        // myCity.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        //
        // }
        // });

    }

    private void findViews() {
        // myCollection = (LinearLayout) myFragmentView.findViewById(R.id.my_collection);
        // mySetting = (LinearLayout) myFragmentView.findViewById(R.id.my_setting);
        // myTravelTheme = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_theme);
        // myTravelSeason = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_season);
        // myTravelBeach = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_beach);
        // myCity = (LinearLayout) myFragmentView.findViewById(R.id.my_travel_city);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
