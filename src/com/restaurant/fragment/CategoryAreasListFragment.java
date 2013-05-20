package com.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.restaurant.adapter.AreaListAdapter;
import com.restaurant.collection.CategoryActivity;
import com.restaurant.collection.api.RestaurantAPI;

public class CategoryAreasListFragment extends ListFragment {

    public CategoryAreasListFragment() {

    }

    public static final CategoryAreasListFragment newInstance() {
        CategoryAreasListFragment f = new CategoryAreasListFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AreaListAdapter adapter = new AreaListAdapter(getActivity(), RestaurantAPI.getAreas());
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("AreaId", 1);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

}
