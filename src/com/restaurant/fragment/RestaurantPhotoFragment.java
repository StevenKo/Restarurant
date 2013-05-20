package com.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.restaurant.collection.R;

public final class RestaurantPhotoFragment extends Fragment {

    public static RestaurantPhotoFragment newInstance() {
        RestaurantPhotoFragment fragment = new RestaurantPhotoFragment();
        return fragment;
    }

    private View myFragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.item_restaurant_photo, container, false);
        return myFragmentView;
    }


}
