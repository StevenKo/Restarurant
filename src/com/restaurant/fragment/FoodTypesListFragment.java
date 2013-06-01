package com.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.restaurant.adapter.CategoryListAdapter;
import com.restaurant.adapter.TypeListAdapter;
import com.restaurant.collection.CategoryActivity;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Type;

public class FoodTypesListFragment extends ListFragment {

    public FoodTypesListFragment() {

    }

    public static final FoodTypesListFragment newInstance() {
        FoodTypesListFragment f = new FoodTypesListFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TypeListAdapter adapter = new TypeListAdapter(getActivity(), Type.getTypes());
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("TypeId", Type.getTypes().get(position).getId());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

}
