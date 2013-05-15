package com.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import con.restaurant.adapter.ListAdapter;

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

        String[] test = { "中式", "西式", "法式" };
        ListAdapter adapter = new ListAdapter(getActivity(), test);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

}
