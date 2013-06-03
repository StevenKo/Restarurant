package com.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.restaurant.adapter.AreaCategoryListAdapter;
import com.restaurant.adapter.AreaTypeListAdapter;
import com.restaurant.adapter.SeparatedListAdapter;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Type;

public class AreaCategoryListFragment extends ListFragment {

    public AreaCategoryListFragment() {

    }

    public static final AreaCategoryListFragment newInstance() {
        AreaCategoryListFragment f = new AreaCategoryListFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
        AreaTypeListAdapter tadapter = new AreaTypeListAdapter(getActivity(), Type.getTypes());
        adapter.addSection("中式料理", tadapter);
        adapter.addSection("日式料理", tadapter);
        AreaCategoryListAdapter cadapter = new AreaCategoryListAdapter(getActivity(), RestaurantAPI.getCategories());
        adapter.addSection("類型分類", cadapter);
//        AreaListAdapter adapter = new AreaListAdapter(getActivity(), RestaurantAPI.getAreas());
        setListAdapter(adapter);
        
    }

}
