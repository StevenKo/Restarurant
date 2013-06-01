package com.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.restaurant.adapter.SeparatedListAdapter;
import com.restaurant.adapter.TypeListAdapter;
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
        TypeListAdapter tadapter = new TypeListAdapter(getActivity(), Type.getTypes());
        adapter.addSection("中式料理", tadapter);
//        AreaListAdapter adapter = new AreaListAdapter(getActivity(), RestaurantAPI.getAreas());
        setListAdapter(adapter);

    }

}
