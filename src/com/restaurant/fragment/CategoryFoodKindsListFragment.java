package com.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.restaurant.adapter.CategoryListAdapter;
import com.restaurant.collection.CategoryActivity;
import com.restaurant.collection.api.RestaurantAPI;

public class CategoryFoodKindsListFragment extends ListFragment {

    public CategoryFoodKindsListFragment() {

    }

    public static final CategoryFoodKindsListFragment newInstance() {
        CategoryFoodKindsListFragment f = new CategoryFoodKindsListFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CategoryListAdapter adapter = new CategoryListAdapter(getActivity(), RestaurantAPI.getCategories());
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("CategoryId", RestaurantAPI.getCategories().get(position).getId());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

}
